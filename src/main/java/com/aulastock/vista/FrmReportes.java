/*
 * Formulario generado para el proyecto AulaStock.
 */

//dayerrrrrrrr

package com.aulastock.vista;

public class FrmReportes extends javax.swing.JFrame {

    public FrmReportes() {
        initComponents();
        setSize(1050, 700);
        setLocationRelativeTo(null);
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
