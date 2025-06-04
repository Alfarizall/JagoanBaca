package com.example.tokobuku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("message", "Selamat datang di halaman Admin!");
        return "admin"; // nanti kita buat admin.html
    }
}

