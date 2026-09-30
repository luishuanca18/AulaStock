USE AulaStock;
GO

/* Registra una categoria nueva. */
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

/* Actualiza una categoria mediante su identificador. */
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
