package com.aulastock.dao;

import com.aulastock.modelo.MovimientoInventario;
import com.aulastock.modelo.ProductoInventario;
import com.aulastock.util.ConexionSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventarioDAO {

    /**
     * Lista el inventario usando dbo.usp_listar_inventario.
     *
     * @param texto         codigo o nombre a buscar (vacio = todos)
     * @param idCategoria   0 = todas las categorias
     * @param soloBajoStock true = solo productos con stock bajo
     */
    public ArrayList<ProductoInventario> listar(String texto, int idCategoria, boolean soloBajoStock) {

        ArrayList<ProductoInventario> listaInventario = new ArrayList<>();
        String usp_listar_inventario = "{call dbo.usp_listar_inventario(?,?,?)}";

        try (Connection conexionBaseDeDatos = ConexionSQL.conectar();
             CallableStatement consultaInventario = conexionBaseDeDatos.prepareCall(usp_listar_inventario)) {

            consultaInventario.setString(1, texto);
            consultaInventario.setInt(2, idCategoria);
            consultaInventario.setBoolean(3, soloBajoStock);

            try (ResultSet resultados = consultaInventario.executeQuery()) {
                while (resultados.next()) {
                    ProductoInventario producto = new ProductoInventario();
                    producto.setIdProducto(resultados.getInt("idProducto"));
                    producto.setCodigo(resultados.getString("codigo"));
                    producto.setNombre(resultados.getString("nombre"));
                    producto.setIdCategoria(resultados.getInt("idCategoria"));
                    producto.setNombreCategoria(resultados.getString("nombreCategoria"));
                    producto.setStock(resultados.getInt("stock"));
                    producto.setStockMinimo(resultados.getInt("stockMinimo"));
                    listaInventario.add(producto);
                }
            }

        } catch (SQLException | NullPointerException errorSQL) {
            // NullPointerException: ConexionSQL.conectar() devuelve null si no hay conexion
            System.out.println("Error al listar inventario: " + errorSQL.getMessage());
        }

        return listaInventario;
    }

    /**
     * Registra una entrada o salida con dbo.usp_registrar_movimiento.
     * Si la base de datos rechaza el movimiento (por ejemplo, stock insuficiente),
     * lanza SQLException con el mensaje del procedimiento para mostrarlo al usuario.
     */
    public void registrarMovimiento(MovimientoInventario movimiento) throws SQLException {

        String usp_registrar_movimiento = "{call dbo.usp_registrar_movimiento(?,?,?,?)}";

        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        if (conexionBaseDeDatos == null) {
            throw new SQLException("No se pudo conectar con la base de datos.");
        }

        try (conexionBaseDeDatos;
             CallableStatement registro = conexionBaseDeDatos.prepareCall(usp_registrar_movimiento)) {

            registro.setInt(1, movimiento.getIdProducto());
            registro.setString(2, movimiento.getTipo());
            registro.setInt(3, movimiento.getCantidad());
            registro.setString(4, movimiento.getMotivo());
            registro.execute();
        }
    }
}
