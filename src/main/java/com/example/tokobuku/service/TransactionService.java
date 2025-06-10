package com.example.tokobuku.service;

import com.example.tokobuku.model.*;
import com.example.tokobuku.repository.BookRepository;
import com.example.tokobuku.repository.TransactionRepository;
import com.example.tokobuku.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;
    
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getUserTransactions(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return transactionRepository.findByUserOrderByTransactionDateDesc(user);
    }

    @Transactional
    public Transaction createTransaction(String username, String customerName, String customerAddress, 
            String customerPhone, PaymentMethod paymentMethod, Map<Long, Integer> cartItems) {
        // Get user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setCustomerName(customerName);
        transaction.setCustomerAddress(customerAddress);
        transaction.setCustomerPhone(customerPhone);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Create transaction items
        for (Map.Entry<Long, Integer> entry : cartItems.entrySet()) {
            Book book = bookRepository.findById(entry.getKey())
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            
            if (book.getStock() < entry.getValue()) {
                throw new IllegalArgumentException("Stock tidak mencukupi untuk buku: " + book.getTitle());
            }

            TransactionItem item = new TransactionItem();
            item.setTransaction(transaction);
            item.setBook(book);
            item.setQuantity(entry.getValue());
            item.setPrice(book.getPrice());
            
            // Update book stock
            book.setStock(book.getStock() - entry.getValue());
            bookRepository.save(book);
            
            // Calculate total
            totalAmount = totalAmount.add(book.getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
            
            transaction.addItem(item);
        }

        transaction.setTotalAmount(totalAmount);
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
    }

    public void updateTransactionStatus(Long id, TransactionStatus status) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaksi tidak ditemukan"));
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction updateTransactionStatus(Long id, String statusStr) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaksi tidak ditemukan"));
        
        try {
            TransactionStatus status = TransactionStatus.valueOf(statusStr.toUpperCase());
            transaction.setStatus(status);
            return transactionRepository.save(transaction);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status transaksi tidak valid: " + statusStr);
        }
    }
}