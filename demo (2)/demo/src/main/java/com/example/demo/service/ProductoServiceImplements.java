package com.example.demo.service;

import com.example.demo.entity.Productos;
import com.example.demo.repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImplements implements ProductoService {

    private final ProductosRepository productoRepository;

    public ProductoServiceImplements(ProductosRepository productosRepository) {
        this.productoRepository = productosRepository;
    }

    @Override
    public List<Productos> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Productos getProductoById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Productos saveProducto(Productos producto) throws RuntimeException {
        return productoRepository.save(producto);
    }

    @Override
    public Productos updateProducto(Integer id, Productos producto) {
        Productos existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no existe"));

        existente.setNombre_producto(producto.getNombre_producto());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setEstado(producto.getEstado());

        return productoRepository.save(existente);
    }

    @Override
    public void deleteProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        productoRepository.deleteById(id);
    }
}