package com.example.tokobuku.repository;

import com.example.tokobuku.model.Favorite;
import com.example.tokobuku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    boolean existsByUserAndBookId(User user, Long bookId);
    void deleteByUserAndBookId(User user, Long bookId);
}
