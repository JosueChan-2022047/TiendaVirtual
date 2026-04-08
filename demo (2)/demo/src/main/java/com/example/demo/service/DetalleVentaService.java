package com.example.demo.service;

import com.example.demo.entity.DetalleVenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetalleVentaService {

    List<DetalleVenta> getAllDetalles();

    DetalleVenta getDetalleById(Integer id);

    DetalleVenta saveDetalle(DetalleVenta detalle) throws RuntimeException;

    DetalleVenta updateDetalle(Integer id, DetalleVenta detalle);

    void deleteDetalle(Integer id);
}