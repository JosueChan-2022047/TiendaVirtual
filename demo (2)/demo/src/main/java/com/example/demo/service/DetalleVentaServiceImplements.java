package com.example.demo.service;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // IMPORTANTE

import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> obtenerTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void guardar(DetalleVenta detalle) {
        
        if (detalle.getCantidad() != null && detalle.getPrecio_unitario() != null) {
            detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecio_unitario());
        }
        repository.save(detalle);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorVenta(Integer id) {

        return repository.findAll();
    }

    @Override
    public DetalleVenta getDetalleById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}