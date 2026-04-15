package com.example.demo.service;

import com.example.demo.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {

    List<DetalleVenta> obtenerTodos();
    void guardar(DetalleVenta detalle);
    void eliminar(Integer id);


    List<DetalleVenta> obtenerDetallesPorVenta(Integer id);
    DetalleVenta getDetalleById(Integer id);
}