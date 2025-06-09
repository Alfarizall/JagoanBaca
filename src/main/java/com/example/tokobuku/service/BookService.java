package com.example.tokobuku.service;

import com.example.tokobuku.model.Book;
import com.example.tokobuku.model.Category;
import com.example.tokobuku.repository.BookRepository;
import com.example.tokobuku.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        return bookRepository.findByCategory(category);
    }
}
