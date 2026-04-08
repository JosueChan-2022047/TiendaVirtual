package com.example.demo.Controller;


import com.example.demo.entity.Usuarios;
import com.example.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/usuarios")
    public class UsuariosController {

        private final UsuarioService usuarioService;

        public UsuariosController(UsuarioService usuarioService){
            this.usuarioService = usuarioService;
        }

        @GetMapping
        public List<Usuarios> listar(){
            return usuarioService.getAllUsuarios();
        }

        @PostMapping
        public ResponseEntity<Object> create(@Valid @RequestBody Usuarios u, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return new ResponseEntity<>(usuarioService.saveUsuario(u), HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> get(@PathVariable Integer id){
            try {
                return ResponseEntity.ok(usuarioService.getUsuarioById(id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable Integer id){
            try {
                usuarioService.deleteUsuario(id);
                return ResponseEntity.status(202).build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al eliminar usuario");
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody Usuarios u, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return ResponseEntity.ok(usuarioService.updateUsuario(id, u));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }

