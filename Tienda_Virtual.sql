drop database if exists Tienda_virtual_in5cm;
create database Tienda_virtual_in5cm;
use Tienda_virtual_in5cm;

-- =========================
-- 1. TABLAS
-- =========================

create table usuarios (
    codigo_usuario int not null auto_increment,
    username varchar(45),
    password varchar(45),
    email varchar(60),
    rol varchar(45),
    estado int,
    primary key (codigo_usuario)
);

create table clientes (
    dpi_cliente bigint not null auto_increment, -- Cambiado a bigint para DPI de Guatemala
    nombre_cliente varchar(50),
    apellido_cliente varchar(50),
    direccion varchar(100),
    estado int,
    primary key (dpi_cliente)
);

create table productos (
    codigo_producto int not null auto_increment,
    nombre_producto varchar(60),
    precio decimal(10,2),
    stock int,
    estado int,
    primary key (codigo_producto)
);

create table ventas (
    codigo_venta int not null auto_increment,
    fecha_venta date,
    total decimal(10,2),
    estado int,
    clientes_dpi_cliete bigint,
    usuarios_codigo_usuario int,
    primary key (codigo_venta)
);

create table detalleventa (
    codigo_detalle_venta int not null auto_increment,
    cantidad int,
    precio_unitario decimal(10,2),
    subtotal decimal(10,2),
    productos_codigo_producto int,
    ventas_codigo_venta int,
    primary key (codigo_detalle_venta)
);

-- =============================================
-- 2. LLAVES FORÁNEAS (CON BORRADO EN CASCADA)
-- =============================================

alter table ventas
add constraint fk_cliente
foreign key (clientes_dpi_cliete) references clientes(dpi_cliente)
on delete cascade; -- Permite eliminar clientes con ventas

alter table ventas
add constraint fk_usuario
foreign key (usuarios_codigo_usuario) references usuarios(codigo_usuario)
on delete cascade;

alter table detalleventa
add constraint fk_producto
foreign key (productos_codigo_producto) references productos(codigo_producto)
on delete cascade; -- Permite eliminar productos con historial

alter table detalleventa
add constraint fk_venta
foreign key (ventas_codigo_venta) references ventas(codigo_venta)
on delete cascade;

-- =========================
-- 3. PROCEDIMIENTOS
-- =========================

delimiter $$

create procedure sp_agregar_usuario(
    in p_codigo int, in p_username varchar(45), in p_password varchar(45), 
    in p_email varchar(60), in p_rol varchar(45), in p_estado int
)
begin
    insert into usuarios(codigo_usuario, username, password, email, rol, estado)
    values(p_codigo, p_username, p_password, p_email, p_rol, p_estado);
end $$

create procedure sp_agregar_cliente(
    in p_dpi bigint, in p_nombre varchar(50), in p_apellido varchar(50), 
    in p_direccion varchar(100), in p_estado int
)
begin
    insert into clientes(dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado)
    values(p_dpi, p_nombre, p_apellido, p_direccion, p_estado);
end $$

create procedure sp_agregar_producto(
    in p_codigo int, in p_nombre varchar(60), in p_precio decimal(10,2), 
    in p_stock int, in p_estado int
)
begin
    insert into productos(codigo_producto, nombre_producto, precio, stock, estado)
    values(p_codigo, p_nombre, p_precio, p_stock, p_estado);
end $$

create procedure sp_agregar_venta(
    in p_codigo int, in p_fecha date, in p_total decimal(10,2), 
    in p_estado int, in p_cliente bigint, in p_usuario int
)
begin
    insert into ventas(codigo_venta, fecha_venta, total, estado, clientes_dpi_cliete, usuarios_codigo_usuario)
    values(p_codigo, p_fecha, p_total, p_estado, p_cliente, p_usuario);
end $$

create procedure sp_agregar_detalle_venta(
    in p_codigo int, in p_cantidad int, in p_precio decimal(10,2), 
    in p_subtotal decimal(10,2), in p_producto int, in p_venta int
)
begin
    insert into detalleventa(codigo_detalle_venta, cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
    values(p_codigo, p_cantidad, p_precio, p_subtotal, p_producto, p_venta);
end $$

delimiter ;

-- =========================
-- 4. CARGA DE DATOS
-- =========================

-- CLIENTES
call sp_agregar_cliente(1001,'Juan','Perez','Zona 1',1);
call sp_agregar_cliente(1002,'Maria','Lopez','Zona 2',1);
call sp_agregar_cliente(1003,'Carlos','Ramirez','Zona 3',1);
call sp_agregar_cliente(1004,'Ana','Gomez','Zona 4',1);
call sp_agregar_cliente(1005,'Luis','Martinez','Zona 5',1);

-- USUARIOS
call sp_agregar_usuario(1,'admin','123','jchan-2022047@kinal.org.gt','ADMIN',1);
call sp_agregar_usuario(2,'user1','123','jchan-2022047@kinal.edu.gt','USER',1);

-- PRODUCTOS
call sp_agregar_producto(1,'Leche',35.00,10,1);
call sp_agregar_producto(2,'Cafe',50.00,100,1);
call sp_agregar_producto(3,'Queso',120.00,50,1);

-- VENTAS
call sp_agregar_venta(1,'2026-01-01',35.00,1,1001,1);
call sp_agregar_venta(2,'2026-01-02',100.00,1,1002,2);

-- DETALLE VENTA
call sp_agregar_detalle_venta(1,1,35.00,35.00,1,1);
call sp_agregar_detalle_venta(2,2,50.00,100.00,2,2);

-- CONSULTAS FINALES
select * from usuarios;
select * from clientes;
select * from productos;
select * from ventas;
select * from detalleventa;