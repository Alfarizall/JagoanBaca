package com.example.tokobuku.controller;

import com.example.tokobuku.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private BookService bookService;    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "home";
    }
}
