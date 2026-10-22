/*
    AulaStock - Estructura y datos de demostracion.
    Incluye 8 categorias, 10 clientes, 20 productos, 10 ventas,
    20 detalles de venta y 20 movimientos de salida.
    ATENCION: este script elimina y vuelve a crear las tablas existentes.
    Usarlo para preparar una base de pruebas nueva, no para actualizar datos.
    Despues ejecutar procedimientos_completos.sql.
*/
USE master;
GO

IF DB_ID(N'AulaStock') IS NULL
BEGIN
    CREATE DATABASE AulaStock;
END;
GO

USE AulaStock;
GO


IF OBJECT_ID(N'dbo.movimientoInventario', N'U') IS NOT NULL DROP TABLE dbo.movimientoInventario;
IF OBJECT_ID(N'dbo.detalleVenta', N'U') IS NOT NULL DROP TABLE dbo.detalleVenta;
IF OBJECT_ID(N'dbo.venta', N'U') IS NOT NULL DROP TABLE dbo.venta;
IF OBJECT_ID(N'dbo.producto', N'U') IS NOT NULL DROP TABLE dbo.producto;
IF OBJECT_ID(N'dbo.cliente', N'U') IS NOT NULL DROP TABLE dbo.cliente;
IF OBJECT_ID(N'dbo.CATEGORIA', N'U') IS NOT NULL DROP TABLE dbo.CATEGORIA;
GO

CREATE TABLE dbo.CATEGORIA (
    idCategoria INT IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NULL,
    estado BIT NOT NULL,
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (idCategoria)
);
GO

CREATE TABLE dbo.cliente (
    idCliente INT IDENTITY(1,1) NOT NULL,
    dni VARCHAR(8) NOT NULL,
    nombres VARCHAR(60) NOT NULL,
    apellidos VARCHAR(60) NOT NULL,
    telefono VARCHAR(15) NULL,
    correo VARCHAR(100) NULL,
    direccion VARCHAR(150) NULL,
    estado BIT NOT NULL CONSTRAINT DF_cliente_estado DEFAULT 1,
    CONSTRAINT PK_cliente PRIMARY KEY (idCliente),
    CONSTRAINT UQ_cliente_dni UNIQUE (dni)
);
GO

CREATE TABLE dbo.producto (
    idProducto INT IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    idCategoria INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL CONSTRAINT DF_producto_stock DEFAULT 0,
    stockMinimo INT NOT NULL CONSTRAINT DF_producto_stockMinimo DEFAULT 5,
    estado BIT NOT NULL CONSTRAINT DF_producto_estado DEFAULT 1,
    CONSTRAINT PK_producto PRIMARY KEY (idProducto),
    CONSTRAINT UQ_producto_codigo UNIQUE (codigo),
    CONSTRAINT FK_producto_CATEGORIA FOREIGN KEY (idCategoria)
        REFERENCES dbo.CATEGORIA(idCategoria)
);
GO

CREATE TABLE dbo.venta (
    idVenta INT IDENTITY(1,1) NOT NULL,
    fecha DATETIME NOT NULL CONSTRAINT DF_venta_fecha DEFAULT GETDATE(),
    idCliente INT NOT NULL,
    total DECIMAL(10,2) NOT NULL CONSTRAINT DF_venta_total DEFAULT 0,
    tipoDocumento VARCHAR(20) NULL,
    numeroDocumento VARCHAR(30) NULL,
    CONSTRAINT PK_venta PRIMARY KEY (idVenta),
    CONSTRAINT FK_venta_cliente FOREIGN KEY (idCliente)
        REFERENCES dbo.cliente(idCliente)
);
GO

CREATE TABLE dbo.detalleVenta (
    idDetalle INT IDENTITY(1,1) NOT NULL,
    idVenta INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    CONSTRAINT PK_detalleVenta PRIMARY KEY (idDetalle),
    CONSTRAINT FK_detalleVenta_venta FOREIGN KEY (idVenta)
        REFERENCES dbo.venta(idVenta),
    CONSTRAINT FK_detalleVenta_producto FOREIGN KEY (idProducto)
        REFERENCES dbo.producto(idProducto)
);
GO

CREATE TABLE dbo.movimientoInventario (
    idMovimiento INT IDENTITY(1,1) NOT NULL,
    idProducto INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    cantidad INT NOT NULL,
    motivo VARCHAR(150) NULL,
    fecha DATETIME NOT NULL CONSTRAINT DF_movimiento_fecha DEFAULT GETDATE(),
    CONSTRAINT PK_movimientoInventario PRIMARY KEY (idMovimiento),
    CONSTRAINT FK_movimientoInventario_producto FOREIGN KEY (idProducto)
        REFERENCES dbo.producto(idProducto)
);
GO

SET IDENTITY_INSERT dbo.CATEGORIA ON;
INSERT INTO dbo.CATEGORIA (idCategoria, nombre, descripcion, estado) VALUES
    (1, 'Cuadernos', 'Cuadernos escolares y universitarios', 1),
    (2, 'Escritura', 'Lapiceros, lapices y marcadores', 1),
    (3, 'Papeleria', 'Hojas, cartulinas y papel', 1),
    (4, 'Organizacion', 'Folders, archivadores y separadores', 1),
    (5, 'Arte', 'Materiales para dibujo y pintura', 1),
    (6, 'Oficina', 'Articulos de uso administrativo', 1),
    (7, 'Tecnologia', 'Accesorios tecnologicos para estudio', 1),
    (8, 'Mochilas', 'Mochilas, cartucheras y accesorios', 1);
SET IDENTITY_INSERT dbo.CATEGORIA OFF;
GO

SET IDENTITY_INSERT dbo.cliente ON;
INSERT INTO dbo.cliente
    (idCliente, dni, nombres, apellidos, telefono, correo, direccion, estado)
VALUES
    (1, '70000001', 'Ana', 'Torres Diaz', '900000001', 'ana.torres@example.com', 'Av. Los Alamos 101', 1),
    (2, '70000002', 'Carlos', 'Mendoza Ruiz', '900000002', 'carlos.mendoza@example.com', 'Jr. Las Flores 202', 1),
    (3, '70000003', 'Daniela', 'Rojas Vega', '900000003', 'daniela.rojas@example.com', 'Calle Lima 303', 1),
    (4, '70000004', 'Eduardo', 'Castro Leon', '900000004', 'eduardo.castro@example.com', 'Av. Grau 404', 1),
    (5, '70000005', 'Fiorella', 'Salazar Pena', '900000005', 'fiorella.salazar@example.com', 'Jr. Union 505', 1),
    (6, '70000006', 'Gabriel', 'Herrera Soto', '900000006', 'gabriel.herrera@example.com', 'Calle Sol 606', 1),
    (7, '70000007', 'Lucia', 'Vargas Ramos', '900000007', 'lucia.vargas@example.com', 'Av. Primavera 707', 1),
    (8, '70000008', 'Marco', 'Quispe Flores', '900000008', 'marco.quispe@example.com', 'Jr. Central 808', 1),
    (9, '70000009', 'Patricia', 'Navarro Cruz', '900000009', 'patricia.navarro@example.com', 'Calle Norte 909', 1),
    (10, '70000010', 'Renato', 'Campos Silva', '900000010', 'renato.campos@example.com', 'Av. Peru 1001', 1);
SET IDENTITY_INSERT dbo.cliente OFF;
GO

SET IDENTITY_INSERT dbo.producto ON;
INSERT INTO dbo.producto
    (idProducto, codigo, nombre, idCategoria, precio, stock, stockMinimo, estado)
VALUES
    (1, 'P001', 'Cuaderno cuadriculado A4', 1, 12.50, 40, 10, 1),
    (2, 'P002', 'Cuaderno rayado A4', 1, 12.00, 35, 10, 1),
    (3, 'P003', 'Cuaderno espiral A5', 1, 8.50, 28, 8, 1),
    (4, 'P004', 'Lapicero azul', 2, 1.50, 100, 20, 1),
    (5, 'P005', 'Lapicero negro', 2, 1.50, 95, 20, 1),
    (6, 'P006', 'Lapiz 2B', 2, 1.20, 80, 15, 1),
    (7, 'P007', 'Resaltador amarillo', 2, 3.50, 30, 8, 1),
    (8, 'P008', 'Papel bond A4 paquete 500', 3, 24.90, 25, 5, 1),
    (9, 'P009', 'Cartulina blanca', 3, 1.00, 60, 12, 1),
    (10, 'P010', 'Papel lustre surtido', 3, 4.50, 20, 5, 1),
    (11, 'P011', 'Folder manila A4', 4, 1.20, 70, 15, 1),
    (12, 'P012', 'Archivador palanca', 4, 11.90, 18, 5, 1),
    (13, 'P013', 'Temperas escolares', 5, 9.50, 16, 5, 1),
    (14, 'P014', 'Caja de colores 12 unidades', 5, 7.90, 22, 6, 1),
    (15, 'P015', 'Tijera escolar', 6, 4.80, 24, 6, 1),
    (16, 'P016', 'Engrapador pequeno', 6, 13.50, 12, 4, 1),
    (17, 'P017', 'Memoria USB 32 GB', 7, 29.90, 15, 5, 1),
    (18, 'P018', 'Mouse USB', 7, 25.00, 10, 4, 1),
    (19, 'P019', 'Mochila escolar negra', 8, 59.90, 14, 4, 1),
    (20, 'P020', 'Cartuchera escolar', 8, 12.90, 20, 5, 1);
SET IDENTITY_INSERT dbo.producto OFF;
GO

/* Diez ventas con documentos y totales calculados a partir de sus detalles. */
SET IDENTITY_INSERT dbo.venta ON;
INSERT INTO dbo.venta
    (idVenta, fecha, idCliente, total, tipoDocumento, numeroDocumento)
VALUES
    (1, '2026-10-17T10:00:00', 1, 29.50, 'Boleta', 'DEMO-B001-000001'),
    (2, '2026-10-18T11:30:00', 2, 30.90, 'Factura', 'DEMO-F001-000001'),
    (3, '2026-10-11T09:00:00', 3, 15.00, 'Boleta', 'DEMO-B001-000002'),
    (4, '2026-10-12T10:15:00', 4, 20.60, 'Factura', 'DEMO-F001-000002'),
    (5, '2026-10-13T11:30:00', 5, 11.00, 'Boleta', 'DEMO-B001-000003'),
    (6, '2026-10-14T14:00:00', 6, 8.10, 'Factura', 'DEMO-F001-000003'),
    (7, '2026-10-15T15:45:00', 7, 21.40, 'Boleta', 'DEMO-B001-000004'),
    (8, '2026-10-16T16:20:00', 8, 20.60, 'Factura', 'DEMO-F001-000004'),
    (9, '2026-10-17T12:00:00', 9, 43.40, 'Boleta', 'DEMO-B001-000005'),
    (10, '2026-10-18T13:30:00', 10, 50.80, 'Factura', 'DEMO-F001-000005');
SET IDENTITY_INSERT dbo.venta OFF;
GO

/* Cada venta tiene dos productos. */
INSERT INTO dbo.detalleVenta
    (idVenta, idProducto, cantidad, precioUnitario, subtotal)
VALUES
    (1, 1, 2, 12.50, 25.00),
    (1, 4, 3, 1.50, 4.50),
    (2, 8, 1, 24.90, 24.90),
    (2, 4, 4, 1.50, 6.00),
    (3, 2, 1, 12.00, 12.00),
    (3, 5, 2, 1.50, 3.00),
    (4, 3, 2, 8.50, 17.00),
    (4, 6, 3, 1.20, 3.60),
    (5, 7, 2, 3.50, 7.00),
    (5, 9, 4, 1.00, 4.00),
    (6, 10, 1, 4.50, 4.50),
    (6, 11, 3, 1.20, 3.60),
    (7, 12, 1, 11.90, 11.90),
    (7, 13, 1, 9.50, 9.50),
    (8, 14, 2, 7.90, 15.80),
    (8, 15, 1, 4.80, 4.80),
    (9, 16, 1, 13.50, 13.50),
    (9, 17, 1, 29.90, 29.90),
    (10, 18, 1, 25.00, 25.00),
    (10, 20, 2, 12.90, 25.80);
GO

/* Una salida de inventario por cada detalle de venta. */
INSERT INTO dbo.movimientoInventario (idProducto, tipo, cantidad, motivo, fecha)
SELECT d.idProducto, 'SALIDA', d.cantidad,
       'Venta de prueba ' + v.numeroDocumento, v.fecha
FROM dbo.detalleVenta d
INNER JOIN dbo.venta v ON d.idVenta = v.idVenta;
GO

/* Descuenta de las existencias iniciales las unidades vendidas en los ejemplos. */
UPDATE dbo.producto SET stock = stock - 2 WHERE idProducto = 1;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 2;
UPDATE dbo.producto SET stock = stock - 2 WHERE idProducto = 3;
UPDATE dbo.producto SET stock = stock - 7 WHERE idProducto = 4;
UPDATE dbo.producto SET stock = stock - 2 WHERE idProducto = 5;
UPDATE dbo.producto SET stock = stock - 3 WHERE idProducto = 6;
UPDATE dbo.producto SET stock = stock - 2 WHERE idProducto = 7;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 8;
UPDATE dbo.producto SET stock = stock - 4 WHERE idProducto = 9;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 10;
UPDATE dbo.producto SET stock = stock - 3 WHERE idProducto = 11;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 12;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 13;
UPDATE dbo.producto SET stock = stock - 2 WHERE idProducto = 14;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 15;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 16;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 17;
UPDATE dbo.producto SET stock = stock - 1 WHERE idProducto = 18;
UPDATE dbo.producto SET stock = stock - 2 WHERE idProducto = 20;
GO

/* Comprueba que hay datos de ejemplo en todas las tablas. */
SELECT 'CATEGORIA' AS tabla, COUNT(*) AS registros FROM dbo.CATEGORIA
UNION ALL
SELECT 'cliente', COUNT(*) FROM dbo.cliente
UNION ALL
SELECT 'producto', COUNT(*) FROM dbo.producto
UNION ALL
SELECT 'venta', COUNT(*) FROM dbo.venta
UNION ALL
SELECT 'detalleVenta', COUNT(*) FROM dbo.detalleVenta
UNION ALL
SELECT 'movimientoInventario', COUNT(*) FROM dbo.movimientoInventario;
GO

PRINT 'Tablas y datos de ejemplo creados. Ejecute procedimientos_completos.sql.';
GO
