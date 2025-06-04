package com.example.tokobuku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.tokobuku.service.BookService;

@Controller
public class AdminController {

    private final BookService bookService;

    @Autowired
    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("message", "Selamat datang di halaman Admin!");
        return "admin";
    }
}

