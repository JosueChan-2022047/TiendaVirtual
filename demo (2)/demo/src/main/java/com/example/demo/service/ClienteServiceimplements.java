package com.example.demo.service;

import com.example.demo.entity.Clientes;
import com.example.demo.repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceimplements implements ClienteService {

    private final ClientesRepository clienteRepository;

    public ClienteServiceimplements(ClientesRepository clientesRepository) {
        this.clienteRepository = clientesRepository;
    }

    @Override
    public List<Clientes> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Clientes getClienteById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Clientes saveCliente(Clientes cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Clientes updateCliente(Integer id, Clientes cliente) {
        Clientes existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no existe"));

        existente.setNombre_cliente(cliente.getNombre_cliente());
        existente.setApellido_cliente(cliente.getApellido_cliente());
        existente.setDireccion(cliente.getDireccion());
        existente.setEstado(cliente.getEstado());

        return clienteRepository.save(existente);
    }

    @Override
    public void deleteCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        clienteRepository.deleteById(id);
    }
}