package com.example.tokobuku.repository;

import com.example.tokobuku.model.Book;
import com.example.tokobuku.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Cari berdasarkan kategori
    List<Book> findByCategory(Category category);

    // Cari berdasarkan judul mengandung teks tertentu (like query)
    List<Book> findByTitleContainingIgnoreCase(String keyword);

}
