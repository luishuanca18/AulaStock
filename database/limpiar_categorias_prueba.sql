/*
    Elimina las categorias de prueba (prueba, asdasd, prueba100 y la categoria sin nombre)
    de una base AulaStock que ya fue creada con la version anterior de AulaStock.sql.
    Solo borra las que no tienen productos asociados.
    Ejecutar una sola vez desde SQL Server Management Studio.
*/
USE AulaStock;
GO

DELETE c
FROM dbo.CATEGORIA AS c
WHERE (c.nombre IN ('prueba', 'asdasd', 'prueba100') OR LTRIM(RTRIM(c.nombre)) = '')
  AND NOT EXISTS (SELECT 1 FROM dbo.producto AS p WHERE p.idCategoria = c.idCategoria);

SELECT idCategoria, nombre, descripcion, estado
FROM dbo.CATEGORIA
ORDER BY idCategoria;
GO
