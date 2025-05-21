package com.example.crud.controller;

import com.example.crud.Model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthManualController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userRepo.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("username", username);
            return "redirect:/mahasiswa/";
        } else {
            model.addAttribute("error", "Login gagal");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        if (userRepo.existsById(username)) {
            model.addAttribute("error", "Username sudah digunakan");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // 🔴 Perlu dienkripsi di aplikasi nyata!
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

