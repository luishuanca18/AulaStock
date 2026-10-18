USE AulaStock
GO

CREATE OR ALTER PROCEDURE usp_eliminar_categoria
@idCategoria int

AS
BEGIN

	SET NOCOUNT OFF;

	DELETE from CATEGORIA 
	where idCategoria = @idCategoria;

END;
GO
