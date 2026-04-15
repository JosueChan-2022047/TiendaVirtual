package com.example.demo.Controller;

import com.example.demo.entity.Usuarios;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam("usuario") String userForm,
                             @RequestParam("password") String passForm,
                             HttpSession session) {


        Usuarios user = (Usuarios) usuarioService.buscarPorUsername(userForm.trim());

        if (user != null && user.getPassword().trim().equals(passForm.trim())) {
            session.setAttribute("usuarioLogueado", user);
            return "redirect:/home";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuarios nuevoUsuario) {
        try {

            nuevoUsuario.setRol("USER");
            nuevoUsuario.setEstado(1);


            usuarioService.saveUsuario(nuevoUsuario);

            return "redirect:/login?registrado=true";
        } catch (Exception e) {
            return "redirect:/login?errorRegistro=true";
        }
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}