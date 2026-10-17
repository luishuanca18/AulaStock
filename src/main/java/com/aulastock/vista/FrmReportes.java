/*
 * Formulario generado para el proyecto AulaStock.
 */

//dayerrrrrrrr

package com.aulastock.vista;

import com.aulastock.dao.ReporteDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmReportes extends javax.swing.JFrame {
    public FrmReportes() {
        initComponents();
        setSize(1050, 700);
        setLocationRelativeTo(null);

        configurarTiposReporte();

        btnGenerar.addActionListener(e -> generarReporte());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }
       private void configurarTiposReporte() {
        cboTipoReporte.removeAllItems();
        cboTipoReporte.addItem("Ventas");
        cboTipoReporte.addItem("Ingresos");
        cboTipoReporte.addItem("Documentos");
    }
        private void limpiarFormulario() {
        txtFechaDesde.setText("");
        txtFechaHasta.setText("");
        txtResumen.setText("");

        javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) tblReporte.getModel();

        modelo.setRowCount(0);
    }
    private void generarReporte() {
    try {
        String desde = txtFechaDesde.getText().trim();
        String hasta = txtFechaHasta.getText().trim();

        if (desde.isEmpty() || hasta.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Ingresa las fechas desde y hasta."
            );
            return;
        }

        LocalDate fechaDesde = LocalDate.parse(desde);
        LocalDate fechaHasta = LocalDate.parse(hasta);

        if (fechaDesde.isAfter(fechaHasta)) {
            JOptionPane.showMessageDialog(
                this,
                "La fecha desde no puede ser posterior a la fecha hasta."
            );
            return;
        }

        DefaultTableModel modelo =
            (DefaultTableModel) tblReporte.getModel();

        ReporteDAO dao = new ReporteDAO();

        String tipo = cboTipoReporte.getSelectedItem().toString();

        boolean hayResultados;

        switch (tipo) {
            case "Ventas":
                hayResultados = dao.listarVentas(
                    Date.valueOf(fechaDesde),
                    Date.valueOf(fechaHasta),
                    modelo
                );
                break;

            case "Ingresos":
                hayResultados = dao.listarIngresos(
                    Date.valueOf(fechaDesde),
                    Date.valueOf(fechaHasta),
                    modelo
                );
                break;

            case "Documentos":
                hayResultados = dao.listarDocumentos(
                    Date.valueOf(fechaDesde),
                    Date.valueOf(fechaHasta),
                    modelo
                );
                break;

            default:
                JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un tipo de reporte válido."
                );
                return;
        }

        if (!hayResultados) {
            txtResumen.setText(
                "No se encontraron registros en el período."
            );

            JOptionPane.showMessageDialog(
                this,
                "No hay registros para las fechas seleccionadas."
            );
            return;
        }

        java.math.BigDecimal total =
            java.math.BigDecimal.ZERO;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object importe = modelo.getValueAt(i, 4);

            if (importe instanceof java.math.BigDecimal) {
                total = total.add(
                    (java.math.BigDecimal) importe
                );
            }
        }

        if (tipo.equals("Ingresos")) {
            txtResumen.setText(
                "Cantidad de ventas: "
                + modelo.getValueAt(0, 3)
                + "\nTotal de ingresos: S/ "
                + total.toPlainString()
            );
        } else {
            txtResumen.setText(
                "Registros encontrados: "
                + modelo.getRowCount()
                + "\nTotal del período: S/ "
                + total.toPlainString()
            );
        }

    } catch (java.time.format.DateTimeParseException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Formato de fecha inválido. Usa AAAA-MM-DD."
        );

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error al consultar el reporte: "
            + ex.getMessage()
        );
    }
}
               
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblTipoReporte = new javax.swing.JLabel();
        cboTipoReporte = new javax.swing.JComboBox();
        lblFechaDesde = new javax.swing.JLabel();
        txtFechaDesde = new javax.swing.JTextField();
        lblFechaHasta = new javax.swing.JLabel();
        txtFechaHasta = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        spReporte = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        lblResumen = new javax.swing.JLabel();
        spResumen = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Reportes");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        lblTitulo.setText("REPORTES");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 45);

        lblTipoReporte.setText("Tipo de reporte");
        getContentPane().add(lblTipoReporte);
        lblTipoReporte.setBounds(30, 90, 120, 25);
        getContentPane().add(cboTipoReporte);
        cboTipoReporte.setBounds(150, 90, 280, 28);

        lblFechaDesde.setText("Desde");
        getContentPane().add(lblFechaDesde);
        lblFechaDesde.setBounds(460, 90, 60, 25);
        getContentPane().add(txtFechaDesde);
        txtFechaDesde.setBounds(520, 90, 140, 28);

        lblFechaHasta.setText("Hasta");
        getContentPane().add(lblFechaHasta);
        lblFechaHasta.setBounds(690, 90, 60, 25);
        getContentPane().add(txtFechaHasta);
        txtFechaHasta.setBounds(750, 90, 140, 28);

        btnGenerar.setText("Generar");
        getContentPane().add(btnGenerar);
        btnGenerar.setBounds(30, 140, 110, 32);

        btnLimpiar.setText("Limpiar");
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(150, 140, 110, 32);

        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Documento", "Descripcion", "Cantidad", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spReporte.setViewportView(tblReporte);

        getContentPane().add(spReporte);
        spReporte.setBounds(30, 200, 960, 320);

        lblResumen.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblResumen.setText("RESUMEN");
        getContentPane().add(lblResumen);
        lblResumen.setBounds(30, 550, 120, 30);

        txtResumen.setEditable(false);
        txtResumen.setColumns(20);
        txtResumen.setRows(5);
        spResumen.setViewportView(txtResumen);

        getContentPane().add(spResumen);
        spResumen.setBounds(150, 545, 840, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmReportes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox cboTipoReporte;
    private javax.swing.JLabel lblFechaDesde;
    private javax.swing.JLabel lblFechaHasta;
    private javax.swing.JLabel lblResumen;
    private javax.swing.JLabel lblTipoReporte;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spReporte;
    private javax.swing.JScrollPane spResumen;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtFechaDesde;
    private javax.swing.JTextField txtFechaHasta;
    private javax.swing.JTextArea txtResumen;
    // End of variables declaration//GEN-END:variables
}
