drop database if exists Tienda_virtual_in5cm;
create database Tienda_virtual_in5cm;
use Tienda_virtual_in5cm;

-- =========================
-- TABLAS
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
    dpi_cliente int not null auto_increment,
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
    clientes_dpi_cliete int,
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

-- =========================
-- LLAVES FORÁNEAS
-- =========================

alter table ventas
add constraint fk_cliente
foreign key (clientes_dpi_cliete) references clientes(dpi_cliente);

alter table ventas
add constraint fk_usuario
foreign key (usuarios_codigo_usuario) references usuarios(codigo_usuario);

alter table detalleventa
add constraint fk_producto
foreign key (productos_codigo_producto) references productos(codigo_producto);

alter table detalleventa
add constraint fk_venta
foreign key (ventas_codigo_venta) references ventas(codigo_venta);

delimiter $$

-- USUARIOS
create procedure sp_agregar_usuario(
    in p_codigo int,
    in p_username varchar(45),
    in p_password varchar(45),
    in p_email varchar(60),
    in p_rol varchar(45),
    in p_estado int
)
begin
    insert into usuarios(codigo_usuario, username, password, email, rol, estado)
    values(p_codigo, p_username, p_password, p_email, p_rol, p_estado);
end $$

-- CLIENTES
create procedure sp_agregar_cliente(
    in p_dpi int,
    in p_nombre varchar(50),
    in p_apellido varchar(50),
    in p_direccion varchar(100),
    in p_estado int
)
begin
    insert into clientes(dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado)
    values(p_dpi, p_nombre, p_apellido, p_direccion, p_estado);
end $$

-- PRODUCTOS
create procedure sp_agregar_producto(
    in p_codigo int,
    in p_nombre varchar(60),
    in p_precio decimal(10,2),
    in p_stock int,
    in p_estado int
)
begin
    insert into productos(codigo_producto, nombre_producto, precio, stock, estado)
    values(p_codigo, p_nombre, p_precio, p_stock, p_estado);
end $$

-- VENTAS
create procedure sp_agregar_venta(
    in p_codigo int,
    in p_fecha date,
    in p_total decimal(10,2),
    in p_estado int,
    in p_cliente int,
    in p_usuario int
)
begin
    insert into ventas(codigo_venta, fecha_venta, total, estado, clientes_dpi_cliete, usuarios_codigo_usuario)
    values(p_codigo, p_fecha, p_total, p_estado, p_cliente, p_usuario);
end $$

-- DETALLE VENTA
create procedure sp_agregar_detalle_venta(
    in p_codigo int,
    in p_cantidad int,
    in p_precio decimal(10,2),
    in p_subtotal decimal(10,2),
    in p_producto int,
    in p_venta int
)
begin
    insert into detalleventa(codigo_detalle_venta, cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
    values(p_codigo, p_cantidad, p_precio, p_subtotal, p_producto, p_venta);
end $$

delimiter ;

-- DATOS DE CLIENTES
call sp_agregar_cliente(1001,'Juan','Perez','Zona 1',1);
call sp_agregar_cliente(1002,'Maria','Lopez','Zona 2',1);
call sp_agregar_cliente(1003,'Carlos','Ramirez','Zona 3',1);
call sp_agregar_cliente(1004,'Ana','Gomez','Zona 4',1);
call sp_agregar_cliente(1005,'Luis','Martinez','Zona 5',1);
call sp_agregar_cliente(1006,'Sofia','Hernandez','Zona 6',1);
call sp_agregar_cliente(1007,'Pedro','Castillo','Zona 7',1);
call sp_agregar_cliente(1008,'Laura','Vasquez','Zona 8',1);
call sp_agregar_cliente(1009,'Diego','Morales','Zona 9',1);
call sp_agregar_cliente(1010,'Elena','Rojas','Zona 10',1);

-- DATOS DE USUARIOS
call sp_agregar_usuario(1,'admin','123','admin@mail.com','ADMIN',1);
call sp_agregar_usuario(2,'user1','123','user1@mail.com','USER',1);
call sp_agregar_usuario(3,'user2','123','user2@mail.com','USER',1);
call sp_agregar_usuario(4,'user3','123','user3@mail.com','USER',1);
call sp_agregar_usuario(5,'user4','123','user4@mail.com','USER',1);
call sp_agregar_usuario(6,'user5','123','user5@mail.com','USER',1);
call sp_agregar_usuario(7,'user6','123','user6@mail.com','USER',1);
call sp_agregar_usuario(8,'user7','123','user7@mail.com','USER',1);
call sp_agregar_usuario(9,'user8','123','user8@mail.com','USER',1);
call sp_agregar_usuario(10,'user9','123','user9@mail.com','USER',1);

-- DATOS DE PRODUCTOS
call sp_agregar_producto(1,'Leche',3500.00,10,1);
call sp_agregar_producto(2,'Cafe',50.00,100,1);
call sp_agregar_producto(3,'Queso',120.00,50,1);
call sp_agregar_producto(4,'Jugo',900.00,20,1);
call sp_agregar_producto(5,'Consome',750.00,15,1);
call sp_agregar_producto(6,'Yogurt',40.00,200,1);
call sp_agregar_producto(7,'Agua',500.00,25,1);
call sp_agregar_producto(8,'Arroz',1500.00,30,1);
call sp_agregar_producto(9,'Frijol',300.00,40,1);
call sp_agregar_producto(10,'Aceite',200.00,60,1);

-- DATOS DE VENTAS
call sp_agregar_venta(1,'2026-01-01',3500.00,1,1001,1);
call sp_agregar_venta(2,'2026-01-02',100.00,1,1002,2);
call sp_agregar_venta(3,'2026-01-03',240.00,1,1003,3);
call sp_agregar_venta(4,'2026-01-04',900.00,1,1004,4);
call sp_agregar_venta(5,'2026-01-05',750.00,1,1005,5);
call sp_agregar_venta(6,'2026-01-06',80.00,1,1006,6);
call sp_agregar_venta(7,'2026-01-07',500.00,1,1007,7);
call sp_agregar_venta(8,'2026-01-08',1500.00,1,1008,8);
call sp_agregar_venta(9,'2026-01-09',300.00,1,1009,9);
call sp_agregar_venta(10,'2026-01-10',200.00,1,1010,10);

-- DATOS DE DETALLE VENTA
call sp_agregar_detalle_venta(1,1,3500.00,3500.00,1,1);
call sp_agregar_detalle_venta(2,2,50.00,100.00,2,2);
call sp_agregar_detalle_venta(3,2,120.00,240.00,3,3);
call sp_agregar_detalle_venta(4,1,900.00,900.00,4,4);
call sp_agregar_detalle_venta(5,1,750.00,750.00,5,5);
call sp_agregar_detalle_venta(6,2,40.00,80.00,6,6);
call sp_agregar_detalle_venta(7,1,500.00,500.00,7,7);
call sp_agregar_detalle_venta(8,1,1500.00,1500.00,8,8);
call sp_agregar_detalle_venta(9,1,300.00,300.00,9,9);
call sp_agregar_detalle_venta(10,1,200.00,200.00,10,10);