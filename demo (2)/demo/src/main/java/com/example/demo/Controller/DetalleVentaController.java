package com.example.demo.Controller;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("/DetalleVenta")
    public String listarDetalles(Model model) {

        model.addAttribute("detalles", detalleVentaService.obtenerTodos());
        return "detalleVenta";
    }

    @PostMapping("/DetalleVenta/guardar")
    @ResponseBody
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalle) {
        try {

            if (detalle.getCantidad() != null && detalle.getPrecio_unitario() != null) {
                detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecio_unitario());
            }


            detalleVentaService.guardar(detalle);
            return ResponseEntity.ok().build();
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud");
        }
    }

    @DeleteMapping("/DetalleVenta/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            detalleVentaService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}