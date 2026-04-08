package com.example.demo.service;

import com.example.demo.entity.Ventas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentaService {

    List<Ventas> getAllVentas();

    Ventas getVentaById(Integer id);

    Ventas saveVenta(Ventas venta) throws RuntimeException;

    Ventas updateVenta(Integer id, Ventas venta);

    void deleteVenta(Integer id);
}