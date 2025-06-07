package com.example.tokobuku.controller;

import com.example.tokobuku.model.*;
import com.example.tokobuku.security.CustomUserDetails;
import com.example.tokobuku.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody TransactionRequest request) {
        try {
            // Validate request
            if (request.getCustomerName() == null || request.getCustomerName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Nama pelanggan harus diisi"));
            }
            if (request.getCustomerAddress() == null || request.getCustomerAddress().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Alamat harus diisi"));
            }
            if (request.getCustomerPhone() == null || request.getCustomerPhone().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Nomor telepon harus diisi"));
            }
            if (request.getPaymentMethod() == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Metode pembayaran harus dipilih"));
            }
            if (request.getItems() == null || request.getItems().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Keranjang belanja kosong"));
            }

            Map<Long, Integer> cartItems = new HashMap<>();
            for (TransactionItemRequest item : request.getItems()) {
                if (item.getBookId() == null || item.getQuantity() == null || item.getQuantity() < 1) {
                    return ResponseEntity.badRequest().body(Map.of("message", "Data item tidak valid"));
                }
                cartItems.put(item.getBookId(), item.getQuantity());
            }
            
            Transaction transaction = transactionService.createTransaction(
                    userDetails.getUsername(),
                    request.getCustomerName(),
                    request.getCustomerAddress(),
                    request.getCustomerPhone(),
                    request.getPaymentMethod(),
                    cartItems
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", transaction.getId());
            response.put("status", "success");
            response.put("message", "Transaksi berhasil dibuat");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace(); // Log the full error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Terjadi kesalahan saat memproses transaksi"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserTransactions(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            List<Transaction> transactions = transactionService.getUserTransactions(userDetails.getUsername());
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal mengambil data transaksi");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long id) {
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            if (!transaction.getUser().getUsername().equals(userDetails.getUsername())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Akses ditolak");
            }
            return ResponseEntity.ok(transaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal mengambil data transaksi");
        }
    }

    @PostMapping("/update-status/{id}")
    public String updateTransactionStatus(@PathVariable Long id,
                                @RequestParam("status") TransactionStatus status,
                                RedirectAttributes redirectAttributes) {
        transactionService.updateTransactionStatus(id, status);
        redirectAttributes.addFlashAttribute("message", "Status transaksi berhasil diubah.");
        return "redirect:/admin";
    }
}
