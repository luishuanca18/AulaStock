/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulastock.dao;
import com.aulastock.util.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author dayer
 */
public class ReporteDAO {
    public boolean listarVentas(
            Date fechaDesde,
            Date fechaHasta,
            DefaultTableModel modelo) throws SQLException {

        String sql =
            "SELECT v.fecha, v.tipoDocumento, " +
            "v.numeroDocumento, v.total " +
            "FROM dbo.venta v " +
            "WHERE v.fecha >= ? " +
            "AND v.fecha < DATEADD(DAY, 1, ?) " +
            "ORDER BY v.fecha DESC";

        modelo.setRowCount(0);

        try (Connection cn = ConexionSQL.conectar()) {

            if (cn == null) {
                throw new SQLException(
                    "No se pudo conectar con SQL Server."
                );
            }

            try (PreparedStatement ps =
                    cn.prepareStatement(sql)) {

                ps.setDate(1, fechaDesde);
                ps.setDate(2, fechaHasta);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        String tipo =
                            rs.getString("tipoDocumento");

                        String numero =
                            rs.getString("numeroDocumento");

                        String documento;

                        if (tipo == null || numero == null) {
                            documento = "Sin comprobante";
                        } else {
                            documento = tipo + " " + numero;
                        }

                        modelo.addRow(new Object[] {
                            rs.getTimestamp("fecha"),
                            documento,
                            "Venta",
                            1,
                            rs.getBigDecimal("total")
                        });
                    }
                }
            }
        }

        return modelo.getRowCount() > 0;
    }
    public boolean listarIngresos(
        Date fechaDesde,
        Date fechaHasta,
        DefaultTableModel modelo) throws SQLException {

    String sql =
        "SELECT COUNT(*) AS cantidadVentas, " +
        "COALESCE(SUM(total), 0) AS totalIngresos " +
        "FROM dbo.Venta " +
        "WHERE fecha >= ? " +
        "AND fecha < DATEADD(DAY, 1, ?)";

    modelo.setRowCount(0);

    try (Connection cn = ConexionSQL.conectar()) {

        if (cn == null) {
            throw new SQLException(
                "No se pudo conectar con SQL Server."
            );
        }

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, fechaDesde);
            ps.setDate(2, fechaHasta);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    int cantidad = rs.getInt("cantidadVentas");
                    java.math.BigDecimal total =
                        rs.getBigDecimal("totalIngresos");

                    modelo.addRow(new Object[] {
                        "Período seleccionado",
                        "-",
                        "Total de ingresos",
                        cantidad,
                        total
                    });

                    return cantidad > 0;
                }
            }
        }
    }

    return false;
}

public boolean listarDocumentos(
        Date fechaDesde,
        Date fechaHasta,
        DefaultTableModel modelo) throws SQLException {

    String sql =
        "SELECT fecha, tipoDocumento, numeroDocumento, total " +
        "FROM dbo.Venta " +
        "WHERE fecha >= ? " +
        "AND fecha < DATEADD(DAY, 1, ?) " +
        "ORDER BY fecha DESC";

    modelo.setRowCount(0);

    try (Connection cn = ConexionSQL.conectar()) {

        if (cn == null) {
            throw new SQLException(
                "No se pudo conectar con SQL Server."
            );
        }

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, fechaDesde);
            ps.setDate(2, fechaHasta);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    String tipo = rs.getString("tipoDocumento");
                    String numero = rs.getString("numeroDocumento");

                    String documento =
                        (tipo == null ? "" : tipo) + " " +
                        (numero == null ? "" : numero);

                    if (documento.trim().isEmpty()) {
                        documento = "Sin comprobante";
                    } else {
                        documento = documento.trim();
                    }

                    modelo.addRow(new Object[] {
                        rs.getTimestamp("fecha"),
                        documento,
                        "Documento",
                        1,
                        rs.getBigDecimal("total")
                    });
                }
            }
        }
    }

    return modelo.getRowCount() > 0;
}
}
