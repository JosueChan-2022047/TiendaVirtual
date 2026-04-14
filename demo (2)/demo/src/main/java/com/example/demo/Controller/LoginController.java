package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String validar(@RequestParam String usuario,
                          @RequestParam String password) {

        if (usuario.equals("admin") && password.equals("1234")) {
            return "redirect:/home";
        }

        return "redirect:/login?error=true";
    }
}