/*
 * Formulario generado para el proyecto AulaStock.
 */
//brendaaaaaaaaaaaaaaaaaaaaaaaaaaaa
package com.aulastock.vista;

public class FrmClientes extends javax.swing.JFrame {

    public FrmClientes() {
        initComponents();
        setSize(1100, 720);
        setLocationRelativeTo(null);
        //Hola
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblNombres = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        spClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Clientes");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        lblTitulo.setText("GESTION DE CLIENTES");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 45);

        lblCodigo.setText("Codigo");
        getContentPane().add(lblCodigo);
        lblCodigo.setBounds(30, 90, 90, 25);
        getContentPane().add(txtCodigo);
        txtCodigo.setBounds(120, 90, 130, 28);

        lblDni.setText("DNI");
        getContentPane().add(lblDni);
        lblDni.setBounds(280, 90, 60, 25);
        getContentPane().add(txtDni);
        txtDni.setBounds(340, 90, 140, 28);

        lblNombres.setText("Nombres");
        getContentPane().add(lblNombres);
        lblNombres.setBounds(510, 90, 80, 25);
        getContentPane().add(txtNombres);
        txtNombres.setBounds(590, 90, 300, 28);

        lblApellidos.setText("Apellidos");
        getContentPane().add(lblApellidos);
        lblApellidos.setBounds(30, 135, 90, 25);
        getContentPane().add(txtApellidos);
        txtApellidos.setBounds(120, 135, 280, 28);

        lblTelefono.setText("Telefono");
        getContentPane().add(lblTelefono);
        lblTelefono.setBounds(430, 135, 80, 25);
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(510, 135, 150, 28);

        lblCorreo.setText("Correo");
        getContentPane().add(lblCorreo);
        lblCorreo.setBounds(690, 135, 70, 25);
        getContentPane().add(txtCorreo);
        txtCorreo.setBounds(760, 135, 280, 28);

        lblDireccion.setText("Direccion");
        getContentPane().add(lblDireccion);
        lblDireccion.setBounds(30, 180, 90, 25);
        getContentPane().add(txtDireccion);
        txtDireccion.setBounds(120, 180, 540, 28);

        chkActivo.setText("Activo");
        getContentPane().add(chkActivo);
        chkActivo.setBounds(700, 180, 100, 28);

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
        txtBuscar.setBounds(110, 295, 420, 28);

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(540, 295, 100, 28);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "DNI", "Nombres", "Apellidos", "Telefono", "Correo", "Direccion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spClientes.setViewportView(tblClientes);

        getContentPane().add(spClientes);
        spClientes.setBounds(30, 345, 1020, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmClientes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spClientes;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
