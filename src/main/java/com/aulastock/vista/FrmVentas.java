/*
 * Formulario generado para el proyecto AulaStock.
 */

///cesarrrrrrrrrrrrrrrrrrrrr
package com.aulastock.vista;

public class FrmVentas extends javax.swing.JFrame {

    public FrmVentas() {
        initComponents();
        setSize(1180, 760);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNumeroVenta = new javax.swing.JLabel();
        txtNumeroVenta = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox();
        lblProducto = new javax.swing.JLabel();
        cboProducto = new javax.swing.JComboBox();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        spDetalleVenta = new javax.swing.JScrollPane();
        tblDetalleVenta = new javax.swing.JTable();
        lblTotalTexto = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnRegistrarVenta = new javax.swing.JButton();
        btnCancelarVenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Ventas");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        lblTitulo.setText("REGISTRO DE VENTAS");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 45);

        lblNumeroVenta.setText("Nro. venta");
        getContentPane().add(lblNumeroVenta);
        lblNumeroVenta.setBounds(30, 90, 100, 25);

        txtNumeroVenta.setEditable(false);
        getContentPane().add(txtNumeroVenta);
        txtNumeroVenta.setBounds(130, 90, 150, 28);

        lblFecha.setText("Fecha");
        getContentPane().add(lblFecha);
        lblFecha.setBounds(310, 90, 70, 25);
        getContentPane().add(txtFecha);
        txtFecha.setBounds(380, 90, 150, 28);

        lblCliente.setText("Cliente");
        getContentPane().add(lblCliente);
        lblCliente.setBounds(560, 90, 70, 25);
        getContentPane().add(cboCliente);
        cboCliente.setBounds(630, 90, 420, 28);

        lblProducto.setText("Producto");
        getContentPane().add(lblProducto);
        lblProducto.setBounds(30, 145, 80, 25);
        getContentPane().add(cboProducto);
        cboProducto.setBounds(110, 145, 360, 28);

        lblCantidad.setText("Cantidad");
        getContentPane().add(lblCantidad);
        lblCantidad.setBounds(500, 145, 80, 25);
        getContentPane().add(txtCantidad);
        txtCantidad.setBounds(580, 145, 100, 28);

        lblPrecio.setText("Precio");
        getContentPane().add(lblPrecio);
        lblPrecio.setBounds(710, 145, 70, 25);

        txtPrecio.setEditable(false);
        getContentPane().add(txtPrecio);
        txtPrecio.setBounds(780, 145, 120, 28);

        btnAgregarProducto.setText("Agregar");
        getContentPane().add(btnAgregarProducto);
        btnAgregarProducto.setBounds(930, 145, 110, 30);

        btnQuitarProducto.setText("Quitar");
        getContentPane().add(btnQuitarProducto);
        btnQuitarProducto.setBounds(1050, 145, 90, 30);

        tblDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spDetalleVenta.setViewportView(tblDetalleVenta);

        getContentPane().add(spDetalleVenta);
        spDetalleVenta.setBounds(30, 210, 1110, 350);

        lblTotalTexto.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lblTotalTexto.setText("TOTAL");
        getContentPane().add(lblTotalTexto);
        lblTotalTexto.setBounds(780, 590, 100, 35);

        lblTotal.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        lblTotal.setText("S/ 0.00");
        getContentPane().add(lblTotal);
        lblTotal.setBounds(900, 590, 200, 35);

        btnRegistrarVenta.setText("Registrar venta");
        getContentPane().add(btnRegistrarVenta);
        btnRegistrarVenta.setBounds(780, 645, 160, 36);

        btnCancelarVenta.setText("Cancelar");
        getContentPane().add(btnCancelarVenta);
        btnCancelarVenta.setBounds(960, 645, 140, 36);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmVentas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnQuitarProducto;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JComboBox cboCliente;
    private javax.swing.JComboBox cboProducto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNumeroVenta;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTexto;
    private javax.swing.JScrollPane spDetalleVenta;
    private javax.swing.JTable tblDetalleVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNumeroVenta;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
