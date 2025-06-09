package com.example.tokobuku.service;

import com.example.tokobuku.model.Favorite;
import com.example.tokobuku.model.User;
import com.example.tokobuku.model.Book;
import com.example.tokobuku.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;    public List<Favorite> getUserFavorites(User user) {
        return favoriteRepository.findByUser(user);
    }

    public boolean existsByUserAndBookId(User user, Long bookId) {
        return favoriteRepository.existsByUserAndBookId(user, bookId);
    }

    public boolean toggleFavorite(User user, Book book) {
        if (favoriteRepository.existsByUserAndBookId(user, book.getId())) {
            favoriteRepository.deleteByUserAndBookId(user, book.getId());
            return false; // Removed from favorites
        } else {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setBook(book);
            favoriteRepository.save(favorite);
            return true; // Added to favorites
        }
    }    @Transactional
    public void removeFavorite(User user, Long bookId) {
        // First find the favorite entry
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        favorites.stream()
                .filter(f -> f.getBook().getId().equals(bookId))
                .findFirst()
                .ifPresent(favorite -> favoriteRepository.delete(favorite));
    }
}
