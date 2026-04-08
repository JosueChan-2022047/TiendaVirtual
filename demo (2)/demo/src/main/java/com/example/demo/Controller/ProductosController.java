package com.example.demo.Controller;

import com.example.demo.entity.Productos;
import com.example.demo.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/productos")
    public class ProductosController {

        private final ProductoService productoService;

        public ProductosController(ProductoService productoService){
            this.productoService = productoService;
        }

        @GetMapping
        public List<Productos> listarTodos(){
            return productoService.getAllProductos();
        }

        @PostMapping
        public ResponseEntity<Object> createProducto(@Valid @RequestBody Productos producto, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> getById(@PathVariable Integer id){
            try {
                return ResponseEntity.ok(productoService.getProductoById(id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable Integer id){
            try {
                productoService.deleteProducto(id);
                return ResponseEntity.status(202).build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al eliminar producto");
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody Productos p, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return ResponseEntity.ok(productoService.updateProducto(id, p));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }

