USE AulaStock;
GO

IF COL_LENGTH('dbo.venta', 'tipoDocumento') IS NULL
    ALTER TABLE dbo.venta ADD tipoDocumento VARCHAR(20) NULL;
GO

IF COL_LENGTH('dbo.venta', 'numeroDocumento') IS NULL
    ALTER TABLE dbo.venta ADD numeroDocumento VARCHAR(30) NULL;
GO

CREATE OR ALTER PROCEDURE dbo.usp_registrar_venta
    @fecha DATETIME,
    @idCliente INT,
    @total DECIMAL(10,2),
    @tipoDocumento VARCHAR(20),
    @numeroDocumento VARCHAR(30)
AS
BEGIN
    
    SET NOCOUNT ON;
    IF NOT EXISTS (SELECT 1 FROM dbo.cliente WHERE idCliente = @idCliente AND estado = 1)
        THROW 50101, 'El cliente no existe o esta inactivo.', 1;

    IF @fecha IS NULL OR @total IS NULL OR @total <= 0
        THROW 50102, 'La fecha y el total de la venta no son validos.', 1;

    IF @tipoDocumento IS NULL OR @tipoDocumento NOT IN ('Boleta', 'Factura', 'Otro')
        THROW 50103, 'Seleccione un tipo de documento valido.', 1;

    IF @numeroDocumento IS NULL OR @numeroDocumento = ''
        THROW 50104, 'El numero de documento es obligatorio.', 1;

    IF EXISTS (
        SELECT 1 FROM dbo.venta
        WHERE tipoDocumento = @tipoDocumento AND numeroDocumento = @numeroDocumento
    )
        THROW 50105, 'Ya existe una venta con ese tipo y numero de documento.', 1;

    INSERT INTO dbo.venta (fecha, idCliente, total, tipoDocumento, numeroDocumento)
    VALUES (@fecha, @idCliente, @total, @tipoDocumento, @numeroDocumento);

    SELECT CONVERT(INT, SCOPE_IDENTITY()) AS idVenta;
END;
GO


CREATE OR ALTER PROCEDURE dbo.usp_registrar_detalle_venta
    @idVenta INT,
    @idProducto INT,
    @cantidad INT,
    @precioUnitario DECIMAL(10,2)
AS
BEGIN
    SET NOCOUNT OFF;
    IF @cantidad IS NULL OR @cantidad <= 0
        THROW 50107, 'La cantidad debe ser mayor que cero.', 1;

    IF @precioUnitario IS NULL OR @precioUnitario <= 0
        THROW 50108, 'El precio debe ser mayor que cero.', 1;

    IF NOT EXISTS (SELECT 1 FROM dbo.venta WHERE idVenta = @idVenta)
        THROW 50109, 'La venta indicada no existe.', 1;

 
    UPDATE dbo.producto
    SET stock = stock - @cantidad
    WHERE idProducto = @idProducto AND estado = 1 AND stock >= @cantidad;

    IF @@ROWCOUNT = 0
        THROW 50110, 'Stock insuficiente o producto inactivo. No se guardo la venta.', 1;

    INSERT INTO dbo.detalleVenta
        (idVenta, idProducto, cantidad, precioUnitario, subtotal)
    VALUES
        (@idVenta, @idProducto, @cantidad, @precioUnitario, @cantidad * @precioUnitario);

    INSERT INTO dbo.movimientoInventario (idProducto, tipo, cantidad, motivo, fecha)
    SELECT @idProducto, 'SALIDA', @cantidad,
           'Venta Nro. ' + CONVERT(VARCHAR(20), @idVenta), fecha
    FROM dbo.venta
    WHERE idVenta = @idVenta;
END;
GO
