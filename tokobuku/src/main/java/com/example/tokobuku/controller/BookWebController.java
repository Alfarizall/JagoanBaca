package com.example.tokobuku.controller;

import com.example.tokobuku.model.Book;
import com.example.tokobuku.model.Category;
import com.example.tokobuku.repository.BookRepository;
import com.example.tokobuku.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookWebController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        model.addAttribute("bookForm", new Book());

        return "books";  // nama file Thymeleaf: books.html
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("bookForm") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(new Book());
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("bookForm", book);
        model.addAttribute("categories", categories);

        return "books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}

