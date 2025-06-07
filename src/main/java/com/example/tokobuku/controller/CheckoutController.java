package com.example.tokobuku.controller;

import com.example.tokobuku.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @GetMapping
    public String showCheckoutPage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(required = false) String mode,
            Model model) {
        
        // Add user details to prefill form
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("mode", mode); // For direct purchase flow
        
        return "checkout";
    }
}
