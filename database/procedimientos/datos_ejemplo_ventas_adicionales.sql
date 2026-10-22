/*
    Agrega ocho ventas ficticias a las dos del archivo datos_ejemplo_ventas.sql.
    Resultado esperado entre ambos archivos: diez ventas DEMO y veinte detalles.
    No modifica la venta inicial sin documento ni repite documentos existentes.
    Utiliza precios actuales, descuenta stock y registra las salidas.
    Ejecutar completo en SSMS. Requiere los clientes y productos iniciales.
*/
USE AulaStock;
GO

SET XACT_ABORT ON;

/* Una fila de esta lista describe una venta con dos productos. */
DECLARE @ejemplos TABLE (
    numero INT PRIMARY KEY,
    tipoDocumento VARCHAR(20),
    numeroDocumento VARCHAR(30),
    fecha DATETIME,
    dni VARCHAR(8),
    codigo1 VARCHAR(20),
    cantidad1 INT,
    codigo2 VARCHAR(20),
    cantidad2 INT
);

INSERT INTO @ejemplos VALUES
    (1, 'Boleta',  'DEMO-B001-000002', '2026-10-11T09:00:00', '70000003', 'P002', 1, 'P005', 2),
    (2, 'Factura', 'DEMO-F001-000002', '2026-10-12T10:15:00', '70000004', 'P003', 2, 'P006', 3),
    (3, 'Boleta',  'DEMO-B001-000003', '2026-10-13T11:30:00', '70000005', 'P007', 2, 'P009', 4),
    (4, 'Factura', 'DEMO-F001-000003', '2026-10-14T14:00:00', '70000006', 'P010', 1, 'P011', 3),
    (5, 'Boleta',  'DEMO-B001-000004', '2026-10-15T15:45:00', '70000007', 'P012', 1, 'P013', 1),
    (6, 'Factura', 'DEMO-F001-000004', '2026-10-16T16:20:00', '70000008', 'P014', 2, 'P015', 1),
    (7, 'Boleta',  'DEMO-B001-000005', '2026-10-17T12:00:00', '70000009', 'P016', 1, 'P017', 1),
    (8, 'Factura', 'DEMO-F001-000005', '2026-10-18T13:30:00', '70000010', 'P018', 1, 'P020', 2);

DECLARE @i INT = 1;
DECLARE @tipo VARCHAR(20), @documento VARCHAR(30), @fecha DATETIME;
DECLARE @dni VARCHAR(8), @codigo1 VARCHAR(20), @codigo2 VARCHAR(20);
DECLARE @cantidad1 INT, @cantidad2 INT;
DECLARE @idCliente INT, @idProducto1 INT, @idProducto2 INT, @idVenta INT;
DECLARE @precio1 DECIMAL(10,2), @precio2 DECIMAL(10,2);
DECLARE @stock1 INT, @stock2 INT;

BEGIN TRY
    BEGIN TRANSACTION;

    WHILE @i <= 8
    BEGIN
        SELECT @tipo = tipoDocumento, @documento = numeroDocumento,
               @fecha = fecha, @dni = dni,
               @codigo1 = codigo1, @cantidad1 = cantidad1,
               @codigo2 = codigo2, @cantidad2 = cantidad2
        FROM @ejemplos WHERE numero = @i;

        IF NOT EXISTS (
            SELECT 1 FROM dbo.venta
            WHERE tipoDocumento = @tipo AND numeroDocumento = @documento
        )
        BEGIN
            /* Reinicia los IDs para detectar datos faltantes en esta vuelta. */
            SET @idCliente = NULL;
            SET @idProducto1 = NULL;
            SET @idProducto2 = NULL;

            SELECT @idCliente = idCliente
            FROM dbo.cliente WHERE dni = @dni;

            SELECT @idProducto1 = idProducto, @precio1 = precio, @stock1 = stock
            FROM dbo.producto WHERE codigo = @codigo1;

            SELECT @idProducto2 = idProducto, @precio2 = precio, @stock2 = stock
            FROM dbo.producto WHERE codigo = @codigo2;

            IF @idCliente IS NULL OR @idProducto1 IS NULL OR @idProducto2 IS NULL
                THROW 50010, 'Falta un cliente o producto inicial requerido. No se agregaron las ventas nuevas.', 1;

            IF @stock1 < @cantidad1 OR @stock2 < @cantidad2
                THROW 50011, 'Stock insuficiente para los ejemplos. No se agregaron las ventas nuevas.', 1;

            INSERT INTO dbo.venta
                (fecha, idCliente, total, tipoDocumento, numeroDocumento)
            VALUES
                (@fecha, @idCliente,
                 @cantidad1 * @precio1 + @cantidad2 * @precio2,
                 @tipo, @documento);

            SET @idVenta = CONVERT(INT, SCOPE_IDENTITY());

            INSERT INTO dbo.detalleVenta
                (idVenta, idProducto, cantidad, precioUnitario, subtotal)
            VALUES
                (@idVenta, @idProducto1, @cantidad1, @precio1, @cantidad1 * @precio1),
                (@idVenta, @idProducto2, @cantidad2, @precio2, @cantidad2 * @precio2);

            INSERT INTO dbo.movimientoInventario
                (idProducto, tipo, cantidad, motivo, fecha)
            VALUES
                (@idProducto1, 'SALIDA', @cantidad1, 'Venta de prueba ' + @documento, @fecha),
                (@idProducto2, 'SALIDA', @cantidad2, 'Venta de prueba ' + @documento, @fecha);

            UPDATE dbo.producto
            SET stock = stock - @cantidad1 WHERE idProducto = @idProducto1;

            UPDATE dbo.producto
            SET stock = stock - @cantidad2 WHERE idProducto = @idProducto2;
        END;

        SET @i = @i + 1;
    END;

    COMMIT TRANSACTION;
END TRY
BEGIN CATCH
    IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION;
    THROW;
END CATCH;
GO

/* Incluye los dos ejemplos anteriores y los ocho nuevos. */
SELECT idVenta, fecha, idCliente, total, tipoDocumento, numeroDocumento
FROM dbo.venta
WHERE numeroDocumento LIKE 'DEMO-%'
ORDER BY fecha, idVenta;

SELECT d.idDetalle, d.idVenta, p.codigo, p.nombre,
       d.cantidad, d.precioUnitario, d.subtotal
FROM dbo.detalleVenta d
INNER JOIN dbo.venta v ON d.idVenta = v.idVenta
INNER JOIN dbo.producto p ON d.idProducto = p.idProducto
WHERE v.numeroDocumento LIKE 'DEMO-%'
ORDER BY d.idVenta, d.idDetalle;
GO
