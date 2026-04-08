package com.example.demo.Controller;

import com.example.demo.entity.Clientes;
import com.example.demo.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/clientes")
    public class ClientesController {

        private final ClienteService clienteService;

        public ClientesController(ClienteService clienteService){
            this.clienteService = clienteService;
        }

        @GetMapping
        public List<Clientes> listar(){
            return clienteService.getAllClientes();
        }

        @PostMapping
        public ResponseEntity<Object> create(@Valid @RequestBody Clientes c, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return new ResponseEntity<>(clienteService.saveCliente(c), HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> get(@PathVariable Integer id){
            try {
                return ResponseEntity.ok(clienteService.getClienteById(id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable Integer id){
            try {
                clienteService.deleteCliente(id);
                return ResponseEntity.status(202).build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al eliminar cliente");
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> update(@PathVariable Integer id, @Valid @RequestBody Clientes c, BindingResult br){
            if (br.hasErrors()){
                return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
            }
            try {
                return ResponseEntity.ok(clienteService.updateCliente(id, c));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }

