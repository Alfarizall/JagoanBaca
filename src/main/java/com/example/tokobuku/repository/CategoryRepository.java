package com.example.tokobuku.repository;

import com.example.tokobuku.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Cari kategori berdasarkan nama
    Category findByName(String name);

}