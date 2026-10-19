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
        btnGenerar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnLimpiar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        javax.swing.table.DefaultTableCellRenderer encabezadoTabla =
                new javax.swing.table.DefaultTableCellRenderer();
        encabezadoTabla.setBackground(new java.awt.Color(26, 48, 76));
        encabezadoTabla.setForeground(java.awt.Color.WHITE);
        encabezadoTabla.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        encabezadoTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezadoTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
        tblReporte.getTableHeader().setDefaultRenderer(encabezadoTabla);
        getContentPane().setBackground(new java.awt.Color(244, 247, 251));
        spReporte.getViewport().setBackground(java.awt.Color.WHITE);
        tblReporte.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblReporte.getTableHeader().setBackground(new java.awt.Color(26, 48, 76));
        tblReporte.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblReporte.getTableHeader().setPreferredSize(new java.awt.Dimension(800, 36));
        tblReporte.getTableHeader().setReorderingAllowed(false);
        spResumen.getViewport().setBackground(java.awt.Color.WHITE);
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
            //hola tio//
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
        lblTitulo.setText("REPORTES");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 0, 1050, 72);

        lblTipoReporte.setText("Tipo de reporte");
        getContentPane().add(lblTipoReporte);
        lblTipoReporte.setBounds(30, 90, 120, 25);
        getContentPane().add(cboTipoReporte);
        cboTipoReporte.setBounds(150, 90, 280, 34);

        lblFechaDesde.setText("Desde");
        getContentPane().add(lblFechaDesde);
        lblFechaDesde.setBounds(460, 90, 60, 25);
        getContentPane().add(txtFechaDesde);
        txtFechaDesde.setBounds(520, 90, 140, 34);

        lblFechaHasta.setText("Hasta");
        getContentPane().add(lblFechaHasta);
        lblFechaHasta.setBounds(690, 90, 60, 25);
        getContentPane().add(txtFechaHasta);
        txtFechaHasta.setBounds(750, 90, 140, 34);

        btnGenerar.setText("Generar");
        getContentPane().add(btnGenerar);
        btnGenerar.setBounds(30, 140, 140, 40);

        btnLimpiar.setText("Limpiar");
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(185, 140, 140, 40);

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
        lblResumen.setText("RESUMEN");
        getContentPane().add(lblResumen);
        lblResumen.setBounds(30, 550, 120, 30);

        txtResumen.setEditable(false);
        txtResumen.setColumns(20);
        txtResumen.setRows(5);
        spResumen.setViewportView(txtResumen);

        getContentPane().add(spResumen);
        spResumen.setBounds(150, 545, 840, 90);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 25));
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoReporte.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTipoReporte.setForeground(new java.awt.Color(99, 115, 134));
        cboTipoReporte.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboTipoReporte.setForeground(new java.awt.Color(39, 56, 77));
        cboTipoReporte.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        lblFechaDesde.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblFechaDesde.setForeground(new java.awt.Color(99, 115, 134));
        txtFechaDesde.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFechaDesde.setForeground(new java.awt.Color(39, 56, 77));
        txtFechaDesde.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaDesde.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblFechaHasta.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblFechaHasta.setForeground(new java.awt.Color(99, 115, 134));
        txtFechaHasta.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFechaHasta.setForeground(new java.awt.Color(39, 56, 77));
        txtFechaHasta.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaHasta.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        spReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        tblReporte.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblReporte.setForeground(new java.awt.Color(39, 56, 77));
        tblReporte.setBackground(new java.awt.Color(255, 255, 255));
        tblReporte.setRowHeight(30);
        tblReporte.setShowVerticalLines(false);
        tblReporte.setGridColor(new java.awt.Color(232, 237, 244));
        tblReporte.setSelectionBackground(new java.awt.Color(218, 234, 248));
        tblReporte.setSelectionForeground(new java.awt.Color(26, 48, 76));
        lblResumen.setFont(new java.awt.Font("Segoe UI", 1, 15));
        lblResumen.setForeground(new java.awt.Color(26, 48, 76));
        spResumen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        txtResumen.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtResumen.setForeground(new java.awt.Color(39, 56, 77));
        txtResumen.setBackground(new java.awt.Color(255, 255, 255));
        txtResumen.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 9, 6, 9));
        txtResumen.setLineWrap(true);
        txtResumen.setWrapStyleWord(true);
        lblTitulo.setBackground(new java.awt.Color(26, 48, 76));
        lblTitulo.setOpaque(true);
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnGenerar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnGenerar.setBackground(new java.awt.Color(20, 126, 103));
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setOpaque(true);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setIconTextGap(8);
        btnGenerar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/reportes-claro.png")));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnLimpiar.setBackground(new java.awt.Color(225, 232, 240));
        btnLimpiar.setForeground(new java.awt.Color(39, 56, 77));
        btnLimpiar.setOpaque(true);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setIconTextGap(8);
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/limpiar.png")));

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
