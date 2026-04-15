package com.example.demo.Controller;

import com.example.demo.entity.Ventas;
import com.example.demo.service.VentaService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.UsuarioService; // Asegúrate de tener este servicio creado
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/Ventas")
public class VentasController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public VentasController(VentaService ventaService, ClienteService clienteService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaVentas", ventaService.getAllVentas());
        model.addAttribute("listaClientes", clienteService.getAllClientes());
        model.addAttribute("listaUsuarios", usuarioService.getAllUsuarios());

        Ventas nuevaVenta = new Ventas();
        nuevaVenta.setFecha_venta(LocalDate.now()); // Fecha por defecto hoy
        model.addAttribute("venta", nuevaVenta);

        return "Ventas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("venta") Ventas venta) {
        ventaService.saveVenta(venta);
        return "redirect:/Ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
        return "redirect:/Ventas";
    }
}