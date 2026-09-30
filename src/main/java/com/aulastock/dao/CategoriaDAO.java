package com.aulastock.dao;

import com.aulastock.modelo.Categoria;
import com.aulastock.util.ConexionSQL;
import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO {

    public ArrayList<Categoria> listar() {

        ArrayList<Categoria>  categoriasBaseDeDatos = new ArrayList<>();

        String consultaSQL = "select idCategoria,nombre,descripcion,estado from CATEGORIA order by nombre";

        Connection conexionBaseDeDatos = ConexionSQL.conectar();

        try {

            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaSQL);
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while (resultadosConsulta.next()){

                Categoria categoriaBaseDeDatos = new Categoria();
                categoriaBaseDeDatos.setIdCategoria(resultadosConsulta.getInt("idCategoria"));
                categoriaBaseDeDatos.setNombre(resultadosConsulta.getString("nombre"));
                categoriaBaseDeDatos.setDescripcion(resultadosConsulta.getString("descripcion"));
                categoriaBaseDeDatos.setEstado(resultadosConsulta.getBoolean("estado"));

                categoriasBaseDeDatos.add(categoriaBaseDeDatos);

            }

            resultadosConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();

        } catch (SQLException errorSQL) {
            System.out.println("Error al listar categoriasBaseDeDatos: "+errorSQL.getMessage());
        }

        return categoriasBaseDeDatos;
    }

//    public boolean registrar(Categoria categoriaNueva) {
//
//        boolean registroExitoso=false;
//
//             String consultas = "INSERT INTO CATEGORIA (nombre, descripcion, estado) VALUES (?, ?, ?)";
//
//             Connection conexionBaseDeDatos = ConexionSQL.conectar();
//            try {
//                PreparedStatement preparacionConsulta = conexionBaseDeDatos.prepareStatement(consultas);
//                preparacionConsulta.setString(1, categoriaNueva.getNombre());
//                preparacionConsulta.setString(2, categoriaNueva.getDescripcion());
//                preparacionConsulta.setBoolean(3, categoriaNueva.isEstado());
//
//                int filasAfectadas = preparacionConsulta.executeUpdate();
//                    registroExitoso=filasAfectadas>0;
//
//                preparacionConsulta.close();
//                conexionBaseDeDatos.close();
//
//            } catch (SQLException errorRegistrar) {
//                System.out.println("errorRegistrar: "+errorRegistrar.getMessage());
//            }
//
//        return registroExitoso;
//    }

    public boolean registrar(Categoria registroCategoria) {

        boolean registroExitoso=false;

            Connection conexionBaseDeDatos = ConexionSQL.conectar();
            String usp_registrar_categoria = "{call dbo.usp_registrar_categoria(?,?,?)}";

        try {
            CallableStatement prepararRegistro = conexionBaseDeDatos.prepareCall(usp_registrar_categoria);
            prepararRegistro.setString(1,registroCategoria.getNombre());
            prepararRegistro.setString(2,registroCategoria.getDescripcion());
            prepararRegistro.setBoolean(3,registroCategoria.isEstado());
            int filasAfectadas = prepararRegistro.executeUpdate();
            registroExitoso=filasAfectadas>0;
            prepararRegistro.close();
            conexionBaseDeDatos.close();

        } catch (SQLException errorSql) {
            System.out.println("errorRegistrar: "+errorSql.getMessage());;
        }

        return registroExitoso;
    }
    public boolean actualizar(Categoria actualizarCategoria) {
        boolean actualizado = false;

        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String usp_actualizar_categoria = "{call usp_actualizar_categoria(?,?,?,?)}";


        try {
            CallableStatement  actualizarRegistro = conexionBaseDeDatos.prepareCall(usp_actualizar_categoria);
            actualizarRegistro.setInt(1,actualizarCategoria.getIdCategoria());
            actualizarRegistro.setString(2,actualizarCategoria.getNombre());
            actualizarRegistro.setString(3,actualizarCategoria.getDescripcion());
            actualizarRegistro.setBoolean(4,actualizarCategoria.isEstado());
            int filasAfectadas = actualizarRegistro.executeUpdate();
            actualizado = filasAfectadas>0;
            actualizarRegistro.close();
            conexionBaseDeDatos.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizarCategoria: "+e.getMessage());
        }


        return actualizado;
    }

    public boolean eliminar(Categoria eliminarCategoria) {

        boolean eliminado=false;



        return  eliminado;
    }


}///final clase
