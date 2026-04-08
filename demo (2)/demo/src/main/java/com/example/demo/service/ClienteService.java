package com.example.demo.service;

import com.example.demo.entity.Clientes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    List<Clientes> getAllClientes();

    Clientes getClienteById(Integer id);

    Clientes saveCliente(Clientes cliente) throws RuntimeException;

    Clientes updateCliente(Integer id, Clientes cliente);

    void deleteCliente(Integer id);
}