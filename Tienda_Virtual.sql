Drop database if exists Tienda_Virtual;
create database Tienda_Virtual;
use Tienda_Virtual;

create table Usuarios (
	codigo_usuario int not null auto_increment,
    username varchar (45),
    password varchar (45),
    email varchar (60),
    rol varchar (45),
    estado int,
    primary key (codigo_Usuario)
);

create table Ventas (
	codigo_venta int not null auto_increment, 
    fecha_venta Date,
    total decimal (10,2),
    estado int,
    Clientes_dpi_cliete int,
    Usuarios_codigo_usuario int,
    primary key (codigo_venta),
    constraint fk_Ventas_Clientes foreign key
    (Clientes_dpi_cliente)
		references Clientes(dpi_clientes),
			constraint fk_Ventas_Usuarios foreign key
		(Usuarios_codigo_usuario)
			references Usuarios(codigo_usuario)
);

create table Clientes (
	dpi_cliente int not null auto_increment,
    nombre_cliente varchar (50),
    apellido_cliente varchar (50),
    direccion varchar (100),
    estado int,
    primary key (dpi_cliente)
    
);

create table DetalleVenta (
	codigo_detalle_venta int not null auto_increment,
    cantidad int,
    precio_unitario decimal (10,2),
    subtotal decimal (10,2),
    Productos_codigo_producto int,
    Ventas_codigo_venta int,
    primary key (codigo_detalle_venta),
    constraint fk_Detalle_Productos foreign key (Productos_codigo_producto) 
        references Productos(codigo_producto),
    constraint fk_Detalle_Ventas foreign key (Ventas_codigo_venta) 
        references Ventas(codigo_venta)
);
    

create table Productos (
	codigo_producto int not null auto_increment, 
    nombre_producto varchar (60),
    precio decimal (10,2),
    stock int,
    estado int,
    primary key (codigo_producto)
);

