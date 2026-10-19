USE AulaStock;
GO

/*
    Lista el inventario de productos activos con filtros opcionales.
    @texto          busca por codigo o nombre (NULL o vacio = sin filtro)
    @idCategoria    NULL o 0 = todas las categorias
    @soloBajoStock  1 = solo productos con stock menor o igual al minimo
*/
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

/*
    Registra una ENTRADA o SALIDA de inventario y actualiza el stock del producto.
    Todo ocurre en una transaccion: si algo falla, no se guarda nada.
*/
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
