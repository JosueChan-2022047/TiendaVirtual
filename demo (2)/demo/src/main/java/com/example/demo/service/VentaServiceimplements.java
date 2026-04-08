package com.example.demo.service;

import com.example.demo.entity.Ventas;
import com.example.demo.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceimplements implements VentaService {

    private final VentasRepository ventaRepository;

    public VentaServiceimplements(VentasRepository ventasRepository) {
        this.ventaRepository = ventasRepository;
    }

    @Override
    public List<Ventas> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Ventas getVentaById(Integer id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    public Ventas saveVenta(Ventas venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Ventas updateVenta(Integer id, Ventas venta) {
        Ventas existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no existe"));

        existente.setFecha_venta(venta.getFecha_venta());
        existente.setTotal(venta.getTotal());
        existente.setEstado(venta.getEstado());
        existente.setCliente(venta.getCliente());
        existente.setUsuario(venta.getUsuario());

        return ventaRepository.save(existente);
    }

    @Override
    public void deleteVenta(Integer id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        ventaRepository.deleteById(id);
    }
}