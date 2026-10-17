package com.aulastock.dao;

import com.aulastock.modelo.Clientes;
import com.aulastock.util.ConexionSQL;
import java.sql.*;
import java.util.ArrayList;

public class ClientesDAO {

    // LISTAR
    public ArrayList<Clientes> listar() {
        ArrayList<Clientes> clientesBaseDeDatos = new ArrayList<>();
        String consultaSQL = "SELECT idCliente, dni, nombres, apellidos, "
                + "telefono, correo, direccion, estado "
                + "FROM dbo.cliente ORDER BY nombres";
        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        try {
            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaSQL);
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            while (resultadosConsulta.next()) {
                Clientes cliente = new Clientes();
                cliente.setIdCliente(resultadosConsulta.getInt("idCliente"));
                cliente.setDni(resultadosConsulta.getString("dni"));
                cliente.setNombres(resultadosConsulta.getString("nombres"));
                cliente.setApellidos(resultadosConsulta.getString("apellidos"));
                cliente.setTelefono(resultadosConsulta.getString("telefono"));
                cliente.setCorreo(resultadosConsulta.getString("correo"));
                cliente.setDireccion(resultadosConsulta.getString("direccion"));
                cliente.setEstado(resultadosConsulta.getBoolean("estado"));
                clientesBaseDeDatos.add(cliente);
            }
            resultadosConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();

        } catch (SQLException e) {
            System.out.println(
                    "Error al listar clientes: " + e.getMessage()
            );
        }

        return clientesBaseDeDatos;
    }

    // REGISTRAR
    public boolean registrar(Clientes cliente) {
        boolean registroExitoso = false;
        String consultaSQL = "INSERT INTO dbo.cliente "
                + "(dni, nombres, apellidos, telefono, correo, direccion, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        try {
            PreparedStatement prepararRegistro = conexionBaseDeDatos.prepareStatement(consultaSQL);

            prepararRegistro.setString(1, cliente.getDni());
            prepararRegistro.setString(2, cliente.getNombres());
            prepararRegistro.setString(3, cliente.getApellidos());
            prepararRegistro.setString(4, cliente.getTelefono());
            prepararRegistro.setString(5, cliente.getCorreo());
            prepararRegistro.setString(6, cliente.getDireccion());
            prepararRegistro.setBoolean(7, cliente.isEstado());

            int filasAfectadas = prepararRegistro.executeUpdate();
            registroExitoso = filasAfectadas > 0;
            prepararRegistro.close();
            conexionBaseDeDatos.close();
        } catch (SQLException e) {
            System.out.println(
                    "Error al registrar cliente: " + e.getMessage()
            );
        }
        return registroExitoso;
    }

    // ACTUALIZAR
    public boolean actualizarCliente(Clientes cliente) {
        boolean actualizado = false;
        String consultaSQL = "UPDATE dbo.cliente SET "
                + "dni = ?, "
                + "nombres = ?, "
                + "apellidos = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "direccion = ?, "
                + "estado = ? "
                + "WHERE idCliente = ?";
        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        try {
            PreparedStatement actualizarRegistro = conexionBaseDeDatos.prepareStatement(consultaSQL);

            actualizarRegistro.setString(1, cliente.getDni());
            actualizarRegistro.setString(2, cliente.getNombres());
            actualizarRegistro.setString(3, cliente.getApellidos());
            actualizarRegistro.setString(4, cliente.getTelefono());
            actualizarRegistro.setString(5, cliente.getCorreo());
            actualizarRegistro.setString(6, cliente.getDireccion());
            actualizarRegistro.setBoolean(7, cliente.isEstado());
            actualizarRegistro.setInt(8, cliente.getIdCliente());

            int filasAfectadas = actualizarRegistro.executeUpdate();
            actualizado = filasAfectadas > 0;
            actualizarRegistro.close();
            conexionBaseDeDatos.close();
        } catch (SQLException e) {
            System.out.println(
                    "Error al actualizar cliente: " + e.getMessage()
            );
        }
        return actualizado;
    }

    // ELIMINAR
    public boolean eliminar(Clientes cliente) {
        boolean eliminado = false;
        String consultaSQL = "DELETE FROM dbo.cliente WHERE idCliente = ?";
        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        try {
            PreparedStatement eliminarRegistro = conexionBaseDeDatos.prepareStatement(consultaSQL);
            eliminarRegistro.setInt(1, cliente.getIdCliente());
            int filasAfectadas = eliminarRegistro.executeUpdate();

            eliminado = filasAfectadas > 0;
            eliminarRegistro.close();
            conexionBaseDeDatos.close();

        } catch (SQLException e) {
            System.out.println(
                    "Error al eliminar cliente: " + e.getMessage()
            );
        }

        return eliminado;
    }
}