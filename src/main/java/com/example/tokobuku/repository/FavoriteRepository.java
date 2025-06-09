package com.example.tokobuku.repository;

import com.example.tokobuku.model.Favorite;
import com.example.tokobuku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    boolean existsByUserAndBookId(User user, Long bookId);
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.user = :user AND f.book.id = :bookId")
    void deleteByUserAndBookId(@Param("user") User user, @Param("bookId") Long bookId);
}
