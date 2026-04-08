package com.example.demo.service;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService {

    private final DetalleVentaRepository detalleRepository;

    public DetalleVentaServiceImplements(DetalleVentaRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<DetalleVenta> getAllDetalles() {
        return detalleRepository.findAll();
    }

    @Override
    public DetalleVenta getDetalleById(Integer id) {
        return detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
    }

    @Override
    public DetalleVenta saveDetalle(DetalleVenta detalle) {
        detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecio_unitario());
        return detalleRepository.save(detalle);
    }

    @Override
    public DetalleVenta updateDetalle(Integer id, DetalleVenta detalle) {
        DetalleVenta existente = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no existe"));

        existente.setCantidad(detalle.getCantidad());
        existente.setPrecio_unitario(detalle.getPrecio_unitario());
        existente.setSubtotal(detalle.getCantidad() * detalle.getPrecio_unitario());
        existente.setProducto(detalle.getProducto());
        existente.setVenta(detalle.getVenta());

        return detalleRepository.save(existente);
    }

    @Override
    public void deleteDetalle(Integer id) {
        if (!detalleRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        detalleRepository.deleteById(id);
    }
}