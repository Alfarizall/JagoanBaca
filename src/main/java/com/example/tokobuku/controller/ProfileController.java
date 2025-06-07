package com.example.tokobuku.controller;

import com.example.tokobuku.model.User;
import com.example.tokobuku.repository.UserRepository;
import com.example.tokobuku.service.FavoriteService;
import com.example.tokobuku.service.TransactionService;
import com.example.tokobuku.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private TransactionService transactionService;    @GetMapping    
    public String viewProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        System.out.println("Loading profile page");
        if (userDetails == null) {
            System.out.println("UserDetails is null");
            return "redirect:/login";
        }
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        model.addAttribute("favorites", favoriteService.getUserFavorites(user));
        model.addAttribute("transactions", transactionService.getUserTransactions(user.getUsername()));
        return "profile";
    }    @PostMapping("/update")
    public String updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @RequestParam String fullName,
                              @RequestParam String username,
                              RedirectAttributes redirectAttributes) {
        System.out.println("Processing profile update for user: " + username);
        User user = userDetails.getUser();
        
        // Check if username is being changed and is not already taken
        if (!user.getUsername().equals(username)) {
            if (userRepository.findByUsername(username).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Username sudah digunakan");
                return "redirect:/user/profile";
            }
        }

        user.setFullName(fullName);
        user.setUsername(username);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("success", "Profil berhasil diperbarui");
        return "redirect:/user/profile";
    }
}
