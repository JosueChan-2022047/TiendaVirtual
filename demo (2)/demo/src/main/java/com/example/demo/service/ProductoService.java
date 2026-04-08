package com.example.demo.service;

import com.example.demo.entity.Productos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    List<Productos> getAllProductos();

    Productos getProductoById(Integer id);

    Productos saveProducto(Productos producto) throws RuntimeException;

    Productos updateProducto(Integer id, Productos producto);

    void deleteProducto(Integer id);
}