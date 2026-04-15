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


    @GetMapping
    public String listar(Model model){
        List<Clientes> lista = clienteService.getAllClientes();
        model.addAttribute("listaClientes", lista);

        model.addAttribute("cliente", new Clientes());
        return "Clientes";
    }


    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Clientes cliente) {
        try {
            clienteService.saveCliente(cliente);
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
        return "redirect:/Clientes";
    }


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