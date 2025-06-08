package com.example.tokobuku.controller;

import com.example.tokobuku.model.User;
import com.example.tokobuku.service.BookService;
import com.example.tokobuku.service.TransactionService;
import com.example.tokobuku.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;
    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public AdminController(BookService bookService, TransactionService transactionService, UserService userService) {
        this.bookService = bookService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("transactions", transactionService.getAllTransaction());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("message", "Selamat datang di halaman Admin!");
        return "admin";
    }
}
