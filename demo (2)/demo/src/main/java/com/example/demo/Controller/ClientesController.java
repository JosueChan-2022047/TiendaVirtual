package com.example.demo.Controller;

import com.example.demo.entity.Clientes;
import com.example.demo.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Clientes")
public class ClientesController {

    private final ClienteService clienteService;

    public ClientesController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    // 1. Carga la página principal con la tabla y el objeto para el modal
    @GetMapping
    public String listar(Model model){
        List<Clientes> lista = clienteService.getAllClientes();
        model.addAttribute("listaClientes", lista);
        // Enviamos un objeto vacío para que el modal de "Nuevo" no de error
        model.addAttribute("cliente", new Clientes());
        return "Clientes";
    }

    // 2. Método para GUARDAR (Crea y Actualiza)
    // Este es el que te faltaba y por eso daba 404
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Clientes cliente) {
        try {
            clienteService.saveCliente(cliente);
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
        return "redirect:/Clientes";
    }

    // 3. Método para ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Integer id){
        try {
            clienteService.deleteCliente(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        return "redirect:/Clientes";
    }
}