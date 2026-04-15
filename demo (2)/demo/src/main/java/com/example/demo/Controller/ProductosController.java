package com.example.demo.Controller;

import com.example.demo.entity.Productos;
import com.example.demo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Productos")
public class ProductosController {

    private final ProductoService productoService;

    public ProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaProductos", productoService.getAllProductos());
        model.addAttribute("producto", new Productos());
        return "Productos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Productos producto) {
        productoService.saveProducto(producto);
        return "redirect:/Productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return "redirect:/Productos";
    }
}