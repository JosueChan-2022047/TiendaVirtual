package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpi_cliente")
    private Long dpi_cliente; // CAMBIADO: Long coincide con BIGINT de MySQL

    private String nombre_cliente;
    private String apellido_cliente;
    private String direccion;
    private Integer estado;


    public Long getDpi_cliente() {
        return dpi_cliente;
    }

    public void setDpi_cliente(Long dpi_cliente) {
        this.dpi_cliente = dpi_cliente;
    }


    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}