USE AulaStock;
go

CREATE OR alter PROCEDURE usp_actualizar_categoria

@idCategoria int,
@nombre varchar(50),
@descripcion varchar(100),
@estado bit

AS BEGIN
SET NOCOUNT OFF;	
	UPDATE CATEGORIA
	SET 
		nombre = @nombre,
		descripcion = @descripcion,
		estado = @estado
	WHERE 
		idCategoria = @idCategoria;

END;
go

