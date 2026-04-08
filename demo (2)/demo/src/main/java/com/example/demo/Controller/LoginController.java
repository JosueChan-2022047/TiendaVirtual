package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "Login"; // Busca login.html en templates
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String username, @RequestParam String password) {
        // Validación simple de prueba
        if ("admin".equals(username) && "1234".equals(password)) {
            return "redirect:/home";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Busca home.html en templates
    }
}