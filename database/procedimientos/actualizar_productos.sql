CREATE OR ALTER PROCEDURE usp_actualizar_productos

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
