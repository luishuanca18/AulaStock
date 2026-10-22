/*
    AulaStock - Todos los procedimientos almacenados.
    Ejecutar completo en SQL Server Management Studio, despues de crear
    las tablas de AulaStock y las columnas tipoDocumento y numeroDocumento
    de la tabla venta.
    Contiene 11 procedimientos, sin definiciones repetidas.
    No crea tablas ni usuarios y no carga datos de ejemplo.
    CREATE OR ALTER permite crear o actualizar los procedimientos.
*/
USE AulaStock;
GO

/* Origen: categorias.sql */
CREATE OR ALTER PROCEDURE dbo.usp_registrar_categoria
    @nombre VARCHAR(50),
    @descripcion VARCHAR(100),
    @estado BIT
AS
BEGIN
    SET NOCOUNT OFF;

    INSERT INTO dbo.CATEGORIA (nombre, descripcion, estado)
    VALUES (@nombre, @descripcion, @estado);
END;
GO

/* Origen: categorias.sql */
CREATE OR ALTER PROCEDURE dbo.usp_actualizar_categoria
    @idCategoria INT,
    @nombre VARCHAR(50),
    @descripcion VARCHAR(100),
    @estado BIT
AS
BEGIN
    SET NOCOUNT OFF;

    UPDATE dbo.CATEGORIA
    SET nombre = @nombre,
        descripcion = @descripcion,
        estado = @estado
    WHERE idCategoria = @idCategoria;
END;
GO

/* Origen: eliminarCategoria.sql */
CREATE OR ALTER PROCEDURE dbo.usp_eliminar_categoria
@idCategoria int

AS
BEGIN

	SET NOCOUNT OFF;

	DELETE from CATEGORIA 
	where idCategoria = @idCategoria;

END;
GO

/* Origen: productos.sql */
CREATE OR ALTER PROCEDURE dbo.usp_listar_productos
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        p.idProducto,
        p.codigo,
        p.nombre,
        p.idCategoria,
        c.nombre AS nombreCategoria,
        p.precio,
        p.stock,
        p.stockMinimo,
        p.estado
    FROM dbo.producto AS p
    INNER JOIN dbo.CATEGORIA AS c
        ON p.idCategoria = c.idCategoria
    ORDER BY p.nombre;
END;
GO

/* Origen: guardar_productos.sql */
CREATE OR ALTER PROCEDURE dbo.usp_guardar_productos

@codigo varchar(20),
@nombre varchar(100),
@idCategoria int,
@precio decimal(10,2),
@stock int,
@stockMinimo int,
@estado bit

AS BEGIN 
SET NOCOUNT OFF
	INSERT producto (codigo,nombre,idCategoria,precio,stock,stockMinimo,estado) 
	VALUES (@codigo,@nombre,@idCategoria,@precio,@stock,@stockMinimo,@estado)


END;
GO

/* Origen: actualizar_productos.sql */
CREATE OR ALTER PROCEDURE dbo.usp_actualizar_productos

@codigo varchar(20),
@nombre varchar (100),
@idCategoria int,
@precio decimal(10,2),
@stock int,
@stockMinimo int,
@estado bit
as begin 
	set nocount off
update producto set 
	nombre = @nombre,
	idCategoria=@idCategoria,
	precio = @precio,
	stock = @stock,
	stockMinimo=@stockMinimo,
	estado = @estado

where codigo = @codigo

end;
GO

/* Origen: eliminar productos.sql */
CREATE OR ALTER  PROCEDURE dbo.usp_eliminar_productos
    @codigo VARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    DELETE FROM producto WHERE codigo = @codigo;
END;
GO

/* Origen: inventario.sql */
CREATE OR ALTER PROCEDURE dbo.usp_listar_inventario
    @texto VARCHAR(100) = NULL,
    @idCategoria INT = NULL,
    @soloBajoStock BIT = 0
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        p.idProducto,
        p.codigo,
        p.nombre,
        p.idCategoria,
        c.nombre AS nombreCategoria,
        p.stock,
        p.stockMinimo
    FROM dbo.producto AS p
    INNER JOIN dbo.CATEGORIA AS c
        ON p.idCategoria = c.idCategoria
    WHERE p.estado = 1
      AND (@texto IS NULL OR @texto = ''
           OR p.nombre LIKE '%' + @texto + '%'
           OR p.codigo LIKE '%' + @texto + '%')
      AND (@idCategoria IS NULL OR @idCategoria = 0 OR p.idCategoria = @idCategoria)
      AND (@soloBajoStock = 0 OR p.stock <= p.stockMinimo)
    ORDER BY p.nombre;
END;
GO

/* Origen: inventario.sql */
CREATE OR ALTER PROCEDURE dbo.usp_registrar_movimiento
    @idProducto INT,
    @tipo VARCHAR(10),
    @cantidad INT,
    @motivo VARCHAR(150)
AS
BEGIN
    SET NOCOUNT ON;
    SET XACT_ABORT ON;

    IF @tipo NOT IN ('ENTRADA', 'SALIDA')
        THROW 50001, 'El tipo de movimiento debe ser ENTRADA o SALIDA.', 1;

    IF @cantidad IS NULL OR @cantidad <= 0
        THROW 50002, 'La cantidad debe ser mayor que cero.', 1;

    BEGIN TRANSACTION;

    DECLARE @stockActual INT;

    SELECT @stockActual = stock
    FROM dbo.producto WITH (UPDLOCK, ROWLOCK)
    WHERE idProducto = @idProducto AND estado = 1;

    IF @stockActual IS NULL
    BEGIN
        ROLLBACK TRANSACTION;
        THROW 50003, 'El producto no existe o esta inactivo.', 1;
    END;

    IF @tipo = 'SALIDA' AND @cantidad > @stockActual
    BEGIN
        ROLLBACK TRANSACTION;
        THROW 50004, 'Stock insuficiente para registrar la salida.', 1;
    END;

    INSERT INTO dbo.movimientoInventario (idProducto, tipo, cantidad, motivo)
    VALUES (@idProducto, @tipo, @cantidad, @motivo);

    UPDATE dbo.producto
    SET stock = stock + CASE WHEN @tipo = 'ENTRADA' THEN @cantidad ELSE -@cantidad END
    WHERE idProducto = @idProducto;

    COMMIT TRANSACTION;
END;
GO

/* Origen: ventas3.0.sql */
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

/* Origen: ventas3.0.sql */
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
