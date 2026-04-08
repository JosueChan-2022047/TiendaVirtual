package com.example.demo.Controller;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/detalle")
    public class DetalleVentaController {

        private final DetalleVentaService detalleService;

        public DetalleVentaController(DetalleVentaService detalleService){
            this.detalleService = detalleService;
        }

        @GetMapping
        public List<DetalleVenta> listar(){
            return detalleService.getAllDetalles();
        }

        @PostMapping
        public ResponseEntity<Object> create(@Valid @RequestBody DetalleVenta d, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return new ResponseEntity<>(detalleService.saveDetalle(d), HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> get(@PathVariable Integer id){
            try {
                return ResponseEntity.ok(detalleService.getDetalleById(id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable Integer id){
            try {
                detalleService.deleteDetalle(id);
                return ResponseEntity.status(202).build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al eliminar detalle");
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody DetalleVenta d, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return ResponseEntity.ok(detalleService.updateDetalle(id, d));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }

