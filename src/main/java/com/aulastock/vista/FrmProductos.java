/*
 * Formulario generado para el proyecto AulaStock.
 */


// mariiiiiiiiiiiiiiiiiiiii

package com.aulastock.vista;

public class FrmProductos extends javax.swing.JFrame {

    public FrmProductos() {
        initComponents();
        setSize(1050, 700);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblStockMinimo = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        spProductos = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Productos");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        lblTitulo.setText("GESTION DE PRODUCTOS");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 45);

        lblCodigo.setText("Codigo");
        getContentPane().add(lblCodigo);
        lblCodigo.setBounds(30, 90, 100, 25);
        getContentPane().add(txtCodigo);
        txtCodigo.setBounds(140, 90, 150, 28);

        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(320, 90, 100, 25);
        getContentPane().add(txtNombre);
        txtNombre.setBounds(420, 90, 300, 28);

        lblCategoria.setText("Categoria");
        getContentPane().add(lblCategoria);
        lblCategoria.setBounds(30, 135, 100, 25);
        getContentPane().add(cboCategoria);
        cboCategoria.setBounds(140, 135, 220, 28);

        lblPrecio.setText("Precio");
        getContentPane().add(lblPrecio);
        lblPrecio.setBounds(390, 135, 80, 25);
        getContentPane().add(txtPrecio);
        txtPrecio.setBounds(470, 135, 120, 28);

        lblStock.setText("Stock");
        getContentPane().add(lblStock);
        lblStock.setBounds(620, 135, 70, 25);
        getContentPane().add(txtStock);
        txtStock.setBounds(690, 135, 100, 28);

        lblStockMinimo.setText("Stock minimo");
        getContentPane().add(lblStockMinimo);
        lblStockMinimo.setBounds(30, 180, 100, 25);
        getContentPane().add(txtStockMinimo);
        txtStockMinimo.setBounds(140, 180, 120, 28);

        chkActivo.setText("Activo");
        getContentPane().add(chkActivo);
        chkActivo.setBounds(300, 180, 100, 28);

        btnNuevo.setText("Nuevo");
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(30, 235, 110, 32);

        btnGuardar.setText("Guardar");
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(150, 235, 110, 32);

        btnActualizar.setText("Actualizar");
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(270, 235, 110, 32);

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(390, 235, 110, 32);

        btnLimpiar.setText("Limpiar");
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(510, 235, 110, 32);

        lblBuscar.setText("Buscar");
        getContentPane().add(lblBuscar);
        lblBuscar.setBounds(30, 295, 80, 25);
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(110, 295, 360, 28);

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(480, 295, 100, 28);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Categoria", "Precio", "Stock", "Stock minimo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spProductos.setViewportView(tblProductos);

        getContentPane().add(spProductos);
        spProductos.setBounds(30, 345, 960, 280);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmProductos().setVisible(true));
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cboCategoria;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStockMinimo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockMinimo;
    // End of variables declaration//GEN-END:variables
}
