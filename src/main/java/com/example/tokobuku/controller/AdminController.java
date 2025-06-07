package com.example.tokobuku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.tokobuku.service.BookService;
import com.example.tokobuku.service.TransactionService;

@Controller
public class AdminController {

    private final BookService bookService;
    private final TransactionService transactionService;

    @Autowired
    public AdminController(BookService bookService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.bookService = bookService;
    }

    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("transactions", transactionService.getAllTransaction());
        model.addAttribute("message", "Selamat datang di halaman Admin!");
        return "admin";
    }
}

