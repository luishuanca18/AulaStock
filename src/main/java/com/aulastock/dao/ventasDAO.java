package com.aulastock.dao;

import com.aulastock.modelo.Productos;
import com.aulastock.util.ConexionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ventasDAO {

    public ArrayList<Productos> cargarProductos() {

        ArrayList<Productos> productosBaseDeDatos = new ArrayList<>();

        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String consultaProductos =  "SELECT idProducto, nombre FROM dbo.producto WHERE estado = 1 ORDER BY nombre";

        try {
            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaProductos);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();

            while (resultadoConsulta.next()) {

                Productos producto = new Productos();
                producto.setIdProductos(resultadoConsulta.getInt("idProducto"));
                producto.setNombre(resultadoConsulta.getString("nombre"));
                productosBaseDeDatos.add(producto);
            }
            resultadoConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();
        } catch (SQLException e) {
            System.out.println("error sql consultaProductos: " + e.getMessage());
        }

        return productosBaseDeDatos;

    }


}
