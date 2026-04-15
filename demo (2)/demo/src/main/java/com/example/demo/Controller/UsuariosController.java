package com.example.demo.Controller;

import com.example.demo.entity.Usuarios;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        // SEGURIDAD: Si no hay sesión o no es ADMIN, mandarlo al home
        Usuarios logueado = (Usuarios) session.getAttribute("usuarioLogueado");
        if (logueado == null || !"ADMIN".equals(logueado.getRol())) {
            return "redirect:/home";
        }

        model.addAttribute("listaUsuarios", usuarioService.getAllUsuarios());
        return "Usuarios";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuarios u) {
        usuarioService.saveUsuario(u);
        return "redirect:/Usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return "redirect:/Usuarios";
    }
}