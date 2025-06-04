package com.example.tokobuku.controller;

import com.example.tokobuku.model.Book;
import com.example.tokobuku.service.BookService;
import com.example.tokobuku.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookWebController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "edit";
    }
}

