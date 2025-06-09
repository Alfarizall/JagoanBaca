package com.example.tokobuku.controller;

import com.example.tokobuku.service.BookService;
import com.example.tokobuku.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping("/home")
    public String homePage(Model model, @RequestParam(required = false) Long categoryId) {
        model.addAttribute("categories", categoryRepository.findAll());
        if (categoryId != null) {
            model.addAttribute("books", bookService.getBooksByCategory(categoryId));
            model.addAttribute("selectedCategory", categoryId);
        } else {
            model.addAttribute("books", bookService.getAllBooks());
        }
        return "home";
    }
}
