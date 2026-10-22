package com.aulastock.dao;

import com.aulastock.modelo.Productos;
import com.aulastock.modelo.Clientes;
import com.aulastock.modelo.DetalleVenta;
import com.aulastock.modelo.Ventas;
import com.aulastock.util.ConexionSQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ventasDAO {

    public ArrayList<Productos> cargarProductos() {

        ArrayList<Productos> productosBaseDeDatos = new ArrayList<>();

        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String consultaProductos = "SELECT idProducto, codigo, nombre, precio, stock "
                + "FROM dbo.producto WHERE estado = 1 ORDER BY nombre";

        try {
            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaProductos);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();

            while (resultadoConsulta.next()) {

                Productos producto = new Productos();
                producto.setIdProductos(resultadoConsulta.getInt("idProducto"));
                producto.setNombre(resultadoConsulta.getString("nombre"));
                producto.setCodigo(resultadoConsulta.getString("codigo"));
                producto.setPrecio(resultadoConsulta.getDouble("precio"));
                producto.setStock(resultadoConsulta.getInt("stock"));
                productosBaseDeDatos.add(producto);
            }
            resultadoConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();

        } catch (SQLException e) {
            productosBaseDeDatos.clear();
            System.out.println("error sql consultaProductos: " + e.getMessage());
        }

        return productosBaseDeDatos;

    }

    public ArrayList<Clientes> cargarClientes() {
        ArrayList<Clientes> clientesBaseDeDatos = new ArrayList<>();
        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String consultaClientes = "SELECT idCliente, dni, nombres, apellidos "
                + "FROM dbo.cliente WHERE estado = 1 ORDER BY nombres, apellidos";

        try {
            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaClientes);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();

            while (resultadoConsulta.next()) {
                Clientes cliente = new Clientes();
                cliente.setIdCliente(resultadoConsulta.getInt("idCliente"));
                cliente.setDni(resultadoConsulta.getString("dni"));
                cliente.setNombres(resultadoConsulta.getString("nombres"));
                cliente.setApellidos(resultadoConsulta.getString("apellidos"));
                clientesBaseDeDatos.add(cliente);
            }

            resultadoConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();
        } catch (SQLException errorSQL) {
            clientesBaseDeDatos.clear();
            System.out.println("Error al cargar clientes: " + errorSQL.getMessage());
        }

        return clientesBaseDeDatos;
    }

    public boolean registrar(Ventas ventaNueva, ArrayList<DetalleVenta> detallesVenta) {

        boolean registroExitoso = false;


        if (ventaNueva == null || detallesVenta == null || detallesVenta.isEmpty()) {
            return registroExitoso;
        }


        double total = 0;
        for (DetalleVenta detalle : detallesVenta) {
            total = total + detalle.getCantidad() * detalle.getPrecioUnitario();
        }
        total = Math.round(total * 100.0) / 100.0;

        Connection conexionBaseDeDatos = ConexionSQL.conectar();


        String usp_registrar_venta = "{call dbo.usp_registrar_venta(?,?,?,?,?)}";

        try {

            conexionBaseDeDatos.setAutoCommit(false);

            CallableStatement prepararRegistro = conexionBaseDeDatos.prepareCall(usp_registrar_venta);
            prepararRegistro.setDate(1, Date.valueOf(ventaNueva.getFecha()));
            prepararRegistro.setInt(2, ventaNueva.getIdCliente());
            prepararRegistro.setDouble(3, total);
            prepararRegistro.setString(4, ventaNueva.getTipoDocumento());
            prepararRegistro.setString(5, ventaNueva.getNumeroDocumento());

            ResultSet resultadoRegistro = prepararRegistro.executeQuery();
            int idVenta = 0;
            if (resultadoRegistro.next()) {
                idVenta = resultadoRegistro.getInt("idVenta");
            }
            resultadoRegistro.close();
            prepararRegistro.close();

            if (idVenta <= 0) {
                throw new SQLException("No se recibio el ID de la venta registrada.");
            }

            String usp_registrar_detalle_venta = "{call dbo.usp_registrar_detalle_venta(?,?,?,?)}";

            for (DetalleVenta detalle : detallesVenta) {
                CallableStatement prepararDetalle = conexionBaseDeDatos.prepareCall(usp_registrar_detalle_venta);
                prepararDetalle.setInt(1, idVenta);
                prepararDetalle.setInt(2, detalle.getIdProducto());
                prepararDetalle.setInt(3, detalle.getCantidad());
                prepararDetalle.setDouble(4, detalle.getPrecioUnitario());
                prepararDetalle.executeUpdate();
                prepararDetalle.close();
            }

            conexionBaseDeDatos.commit();
            ventaNueva.setIdVenta(idVenta);
            registroExitoso = true;
            conexionBaseDeDatos.close();

        } catch (SQLException | IllegalArgumentException errorRegistro) {

            if (conexionBaseDeDatos != null) {
                try {
                    conexionBaseDeDatos.rollback();
                } catch (SQLException errorRevertir) {
                    System.out.println("Error al deshacer: " + errorRevertir.getMessage());
                }
                try {
                    conexionBaseDeDatos.close();
                } catch (SQLException errorCerrar) {
                    System.out.println("Error al cerrar la conexion: " + errorCerrar.getMessage());
                }
            }
            System.out.println("Error al registrar venta: " + errorRegistro.getMessage());
        }

        return registroExitoso;
    }


}
