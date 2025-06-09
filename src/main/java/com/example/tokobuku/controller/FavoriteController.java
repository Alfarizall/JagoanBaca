package com.example.tokobuku.controller;

import com.example.tokobuku.model.Favorite;
import com.example.tokobuku.model.User;
import com.example.tokobuku.security.CustomUserDetails;
import com.example.tokobuku.service.FavoriteService;
import com.example.tokobuku.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private BookService bookService;
    
    @GetMapping("/check/{bookId}")
    @ResponseBody
    public boolean checkFavorite(@PathVariable Long bookId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return favoriteService.existsByUserAndBookId(user, bookId);
    }

    @GetMapping
    public String getFavorites(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        model.addAttribute("favorites", favoriteService.getUserFavorites(user));
        return "favorites";
    }

    @PostMapping("/toggle/{bookId}")
    @ResponseBody
    public boolean toggleFavorite(@PathVariable Long bookId, 
                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return favoriteService.toggleFavorite(user, bookService.getBookById(bookId).get());
    }

    @DeleteMapping("/{bookId}")
    @ResponseBody
    public void removeFavorite(@PathVariable Long bookId, 
                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        favoriteService.removeFavorite(user, bookId);
    }
}
