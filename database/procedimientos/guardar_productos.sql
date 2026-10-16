use AulaStock;
go

CREATE OR ALTER PROCEDURE usp_guardar_productos

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










