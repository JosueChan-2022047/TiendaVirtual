package com.example.demo.Controller;

import com.example.demo.entity.Ventas;
import com.example.demo.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentaService ventaService;

    public VentasController(VentaService ventaService){
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Ventas> listar(){
        return ventaService.getAllVentas();
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Ventas v, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            return new ResponseEntity<>(ventaService.saveVenta(v), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(ventaService.getVentaById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            ventaService.deleteVenta(id);
            return ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar venta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody Ventas v, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.ok(ventaService.updateVenta(id, v));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}