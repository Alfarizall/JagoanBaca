package com.example.tokobuku.controller;

import com.example.tokobuku.model.*;
import com.example.tokobuku.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transactions")
public class TransactionWebController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/update-status/{id}")
    public String updateTransactionStatus(@PathVariable Long id,
                                @RequestParam("status") TransactionStatus status,
                                RedirectAttributes redirectAttributes) {
        transactionService.updateTransactionStatus(id, status);
        redirectAttributes.addFlashAttribute("success", "Status transaksi berhasil diubah.");
        return "redirect:/admin";
    }
}
