USE AulaStock;
GO

/*
    Devuelve los productos junto con el nombre de su categoria.
    CREATE OR ALTER permite crear el procedimiento o actualizarlo.
*/
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
