package com.example.demo.repository;

import com.example.demo.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    @Query("SELECT d FROM DetalleVenta d WHERE d.venta.codigo_venta = :cod")
    List<DetalleVenta> findByCodigoVenta(@Param("cod") Integer cod);
}