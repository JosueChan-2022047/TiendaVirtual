package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalleventa") // Corregido: en tu SQL es 'detalleventa', sin la 's'
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta") // Recomendado especificar el nombre exacto
    private Integer codigo_detalle_venta;

    private Integer cantidad;
    private Double precio_unitario;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "productos_codigo_producto") // Corregido: según tu script SQL
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "ventas_codigo_venta") // Corregido: según tu script SQL
    private Ventas venta;


    public Integer getCodigo_detalle_venta() {
        return codigo_detalle_venta;
    }

    public void setCodigo_detalle_venta(Integer codigo_detalle_venta) {
        this.codigo_detalle_venta = codigo_detalle_venta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {

    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public void setId_detalle(Integer id) {
    }
}