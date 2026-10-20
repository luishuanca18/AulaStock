USE AulaStock;
GO

/* Conserva los datos existentes; agrega estas columnas solo si faltan. */
IF COL_LENGTH('dbo.venta', 'tipoDocumento') IS NULL
    ALTER TABLE dbo.venta ADD tipoDocumento VARCHAR(20) NULL;
GO

IF COL_LENGTH('dbo.venta', 'numeroDocumento') IS NULL
    ALTER TABLE dbo.venta ADD numeroDocumento VARCHAR(30) NULL;
GO

/*
    Guarda los datos generales y devuelve el ID real de la venta.
    El DAO desactiva autocommit antes de llamar a este procedimiento.
    SQL Server inicia la transaccion implicita al consultar una tabla.
    Despues guarda todos los detalles y confirma mediante commit().
*/
CREATE OR ALTER PROCEDURE dbo.usp_registrar_venta
    @fecha DATETIME,
    @idCliente INT,
    @total DECIMAL(10,2),
    @tipoDocumento VARCHAR(20),
    @numeroDocumento VARCHAR(30),
    @idVenta INT OUTPUT
AS
BEGIN
    SET NOCOUNT OFF;
    SET XACT_ABORT ON;

    /* Se acepta una transaccion abierta o el modo implicito usado por JDBC. */
    IF @@TRANCOUNT = 0 AND (@@OPTIONS & 2) = 0
        THROW 50100, 'La venta debe registrarse dentro de una transaccion.', 1;

    IF NOT EXISTS (SELECT 1 FROM dbo.cliente WHERE idCliente = @idCliente AND estado = 1)
        THROW 50101, 'El cliente no existe o esta inactivo.', 1;

    IF @fecha IS NULL OR @total IS NULL OR @total <= 0
        THROW 50102, 'La fecha y el total de la venta no son validos.', 1;

    IF @tipoDocumento IS NULL OR @tipoDocumento NOT IN ('Boleta', 'Factura', 'Otro')
        THROW 50103, 'Seleccione un tipo de documento valido.', 1;

    SET @numeroDocumento = LTRIM(RTRIM(@numeroDocumento));
    IF @numeroDocumento IS NULL OR @numeroDocumento = ''
        THROW 50104, 'El numero de documento es obligatorio.', 1;

    IF EXISTS (
        SELECT 1 FROM dbo.venta WITH (UPDLOCK, HOLDLOCK)
        WHERE tipoDocumento = @tipoDocumento AND numeroDocumento = @numeroDocumento
    )
        THROW 50105, 'Ya existe una venta con ese tipo y numero de documento.', 1;

    INSERT INTO dbo.venta (fecha, idCliente, total, tipoDocumento, numeroDocumento)
    VALUES (@fecha, @idCliente, @total, @tipoDocumento, @numeroDocumento);

    SET @idVenta = CONVERT(INT, SCOPE_IDENTITY());
END;
GO

/* Guarda un detalle, descuenta stock y registra su salida de inventario. */
CREATE OR ALTER PROCEDURE dbo.usp_registrar_detalle_venta
    @idVenta INT,
    @idProducto INT,
    @cantidad INT,
    @precioUnitario DECIMAL(10,2)
AS
BEGIN
    SET NOCOUNT OFF;
    SET XACT_ABORT ON;

    IF @@TRANCOUNT = 0
        THROW 50106, 'El detalle debe registrarse en la transaccion de la venta.', 1;

    IF @cantidad IS NULL OR @cantidad <= 0
        THROW 50107, 'La cantidad debe ser mayor que cero.', 1;

    IF @precioUnitario IS NULL OR @precioUnitario <= 0
        THROW 50108, 'El precio debe ser mayor que cero.', 1;

    IF NOT EXISTS (SELECT 1 FROM dbo.venta WHERE idVenta = @idVenta)
        THROW 50109, 'La venta indicada no existe.', 1;

    /* La condicion comprueba el stock real, aunque otro usuario haya vendido. */
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
