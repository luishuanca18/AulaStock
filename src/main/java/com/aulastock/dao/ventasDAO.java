package com.aulastock.dao;

import com.aulastock.modelo.Productos;
import com.aulastock.modelo.Clientes;
import com.aulastock.modelo.DetalleVenta;
import com.aulastock.modelo.Ventas;
import com.aulastock.util.ConexionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
import java.sql.Timestamp;
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
        if (conexionBaseDeDatos == null) {
            return registroExitoso;
        }
        String usp_registrar_venta = "{call dbo.usp_registrar_venta(?,?,?,?,?,?)}";
        String usp_registrar_detalle_venta = "{call dbo.usp_registrar_detalle_venta(?,?,?,?)}";

        try {
            // No confirma cada INSERT por separado. Al final se confirma toda la venta.
            conexionBaseDeDatos.setAutoCommit(false);

            CallableStatement prepararRegistro = conexionBaseDeDatos.prepareCall(usp_registrar_venta);
            prepararRegistro.setTimestamp(1, Timestamp.valueOf(ventaNueva.getFecha() + " 00:00:00"));
            prepararRegistro.setInt(2, ventaNueva.getIdCliente());
            prepararRegistro.setDouble(3, total);
            prepararRegistro.setString(4, ventaNueva.getTipoDocumento());
            prepararRegistro.setString(5, ventaNueva.getNumeroDocumento());
            // SQL devuelve aqui el ID real que genero IDENTITY.
            prepararRegistro.registerOutParameter(6, Types.INTEGER);
            prepararRegistro.executeUpdate();
            int idVenta = prepararRegistro.getInt(6);
            prepararRegistro.close();

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
            ventaNueva.setTotal(total);
            registroExitoso = true;
            conexionBaseDeDatos.close();

        } catch (SQLException | IllegalArgumentException errorRegistro) {
            // Si falla una parte, deshace tambien los registros anteriores de esta venta.
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
