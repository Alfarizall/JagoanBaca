package com.example.tokobuku.controller;

import com.example.tokobuku.model.Book;
import com.example.tokobuku.model.Category;
import com.example.tokobuku.model.User;
import com.example.tokobuku.service.BookService;
import com.example.tokobuku.service.CategoryService;
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
    private final CategoryService categoryService;

    @Autowired
    public AdminController(BookService bookService, TransactionService transactionService, 
                         UserService userService, CategoryService categoryService) {
        this.bookService = bookService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("transactions", transactionService.getAllTransaction());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("message", "Selamat datang di halaman Admin!");
        return "admin";
    }
}
