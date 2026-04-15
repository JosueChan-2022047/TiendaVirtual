package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo_venta;

    private LocalDate fecha_venta;
    private Double total;
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "clientes_dpi_cliete")
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "usuarios_codigo_usuario")
    private Usuarios usuario;

    public Integer getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(Integer codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}