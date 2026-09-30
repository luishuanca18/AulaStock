/*
 * Formulario generado para el proyecto AulaStock.
 */

//rociooooooooooooooooo
package com.aulastock.vista;

public class FrmInventario extends javax.swing.JFrame {

    public FrmInventario() {
        initComponents();
        setSize(1100, 720);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox();
        chkBajoStock = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        btnActualizarTabla = new javax.swing.JButton();
        spInventario = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        lblMovimiento = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        cboProducto = new javax.swing.JComboBox();
        lblTipoMovimiento = new javax.swing.JLabel();
        cboTipoMovimiento = new javax.swing.JComboBox();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblMotivo = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        btnRegistrarMovimiento = new javax.swing.JButton();
        btnLimpiarMovimiento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Inventario");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        lblTitulo.setText("CONTROL DE INVENTARIO");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 45);

        lblBuscar.setText("Buscar producto");
        getContentPane().add(lblBuscar);
        lblBuscar.setBounds(30, 90, 120, 25);
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(150, 90, 320, 28);

        lblCategoria.setText("Categoria");
        getContentPane().add(lblCategoria);
        lblCategoria.setBounds(500, 90, 90, 25);
        getContentPane().add(cboCategoria);
        cboCategoria.setBounds(590, 90, 220, 28);

        chkBajoStock.setText("Solo bajo stock");
        getContentPane().add(chkBajoStock);
        chkBajoStock.setBounds(840, 90, 150, 28);

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(30, 135, 100, 30);

        btnActualizarTabla.setText("Actualizar");
        getContentPane().add(btnActualizarTabla);
        btnActualizarTabla.setBounds(140, 135, 110, 30);

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Categoria", "Stock actual", "Stock minimo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spInventario.setViewportView(tblInventario);

        getContentPane().add(spInventario);
        spInventario.setBounds(30, 185, 1020, 260);

        lblMovimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblMovimiento.setText("REGISTRAR MOVIMIENTO");
        getContentPane().add(lblMovimiento);
        lblMovimiento.setBounds(30, 475, 300, 30);

        lblProducto.setText("Producto");
        getContentPane().add(lblProducto);
        lblProducto.setBounds(30, 525, 80, 25);
        getContentPane().add(cboProducto);
        cboProducto.setBounds(110, 525, 300, 28);

        lblTipoMovimiento.setText("Tipo");
        getContentPane().add(lblTipoMovimiento);
        lblTipoMovimiento.setBounds(440, 525, 50, 25);
        getContentPane().add(cboTipoMovimiento);
        cboTipoMovimiento.setBounds(490, 525, 160, 28);

        lblCantidad.setText("Cantidad");
        getContentPane().add(lblCantidad);
        lblCantidad.setBounds(680, 525, 80, 25);
        getContentPane().add(txtCantidad);
        txtCantidad.setBounds(760, 525, 100, 28);

        lblMotivo.setText("Motivo");
        getContentPane().add(lblMotivo);
        lblMotivo.setBounds(30, 570, 70, 25);
        getContentPane().add(txtMotivo);
        txtMotivo.setBounds(110, 570, 540, 28);

        btnRegistrarMovimiento.setText("Registrar");
        getContentPane().add(btnRegistrarMovimiento);
        btnRegistrarMovimiento.setBounds(680, 570, 130, 32);

        btnLimpiarMovimiento.setText("Limpiar");
        getContentPane().add(btnLimpiarMovimiento);
        btnLimpiarMovimiento.setBounds(820, 570, 110, 32);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmInventario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarTabla;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiarMovimiento;
    private javax.swing.JButton btnRegistrarMovimiento;
    private javax.swing.JComboBox cboCategoria;
    private javax.swing.JComboBox cboProducto;
    private javax.swing.JComboBox cboTipoMovimiento;
    private javax.swing.JCheckBox chkBajoStock;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblMovimiento;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblTipoMovimiento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spInventario;
    private javax.swing.JTable tblInventario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtMotivo;
    // End of variables declaration//GEN-END:variables
}
