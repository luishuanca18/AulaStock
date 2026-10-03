/*
 * Formulario generado para el proyecto AulaStock.
 */
package com.aulastock.vista;

import com.aulastock.dao.CategoriaDAO;
import com.aulastock.modelo.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FrmCategorias extends javax.swing.JFrame {

    public FrmCategorias() {
        initComponents();
        setSize(900, 650);
        setLocationRelativeTo(null);
        chkActivo.setSelected(true);
        txtIdCategoria.setText("AUTOMATICO");
        txtNombre.requestFocus();
        cargarDatosBDaTblCategorias();

    }

    private void cargarDatosBDaTblCategorias() {

        DefaultTableModel tblCATEGORIAS = (DefaultTableModel) tblCategorias.getModel();
        tblCATEGORIAS.setRowCount(0);

        CategoriaDAO categoriaDAO= new CategoriaDAO();

       ArrayList<Categoria> listaCategorias;
       listaCategorias=categoriaDAO.listar();

        for ( Categoria categoria : listaCategorias) {
            Object[] filaCategorias = {categoria.getIdCategoria(),categoria.getNombre(),categoria.getDescripcion()};
            tblCATEGORIAS.addRow(filaCategorias);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblIdCategoria = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        spDescripcion = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        chkActivo = new javax.swing.JCheckBox();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        spCategorias = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Categorias");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        lblTitulo.setText("GESTION DE CATEGORIAS");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 45);

        lblIdCategoria.setText("ID Categoria");
        getContentPane().add(lblIdCategoria);
        lblIdCategoria.setBounds(30, 90, 100, 25);

        txtIdCategoria.setEditable(false);
        getContentPane().add(txtIdCategoria);
        txtIdCategoria.setBounds(140, 90, 150, 28);

        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(320, 90, 100, 25);
        getContentPane().add(txtNombre);
        txtNombre.setBounds(420, 90, 300, 28);

        lblDescripcion.setText("Descripcion");
        getContentPane().add(lblDescripcion);
        lblDescripcion.setBounds(30, 135, 100, 25);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        spDescripcion.setViewportView(txtDescripcion);

        getContentPane().add(spDescripcion);
        spDescripcion.setBounds(140, 135, 580, 80);

        chkActivo.setText("Activo");
        getContentPane().add(chkActivo);
        chkActivo.setBounds(740, 135, 100, 28);

        btnNuevo.setText("Nuevo");
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(30, 240, 110, 32);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(150, 240, 110, 32);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(270, 240, 110, 32);

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(390, 240, 110, 32);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(510, 240, 110, 32);

        lblBuscar.setText("Buscar");
        getContentPane().add(lblBuscar);
        lblBuscar.setBounds(30, 300, 80, 25);
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(110, 300, 360, 28);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(680, 240, 100, 28);

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Categoria", "Nombre", "Descripcion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spCategorias.setViewportView(tblCategorias);

        getContentPane().add(spCategorias);
        spCategorias.setBounds(30, 350, 820, 220);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {


    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {

        Categoria categoria = new Categoria();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        int isCategoria = tblCategorias.getSelectedRow();






    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = new Categoria();

        if(txtNombre.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "HAY ESPACIOS VACIOS, INGRESE TODA LA INFORMACION");
            return;
        }
            categoria.setNombre(txtNombre.getText().trim());
            categoria.setDescripcion(txtDescripcion.getText().trim());
            categoria.setEstado(chkActivo.isSelected());

            boolean registroExitoso =  categoriaDAO.registrar(categoria);
                if (registroExitoso) {
                    JOptionPane.showMessageDialog(this, "Registro Exitoso");
                    txtIdCategoria.setText("");
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    txtNombre.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Registro Fallido");
                }

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmCategorias().setVisible(true));


    }



    





























    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIdCategoria;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spCategorias;
    private javax.swing.JScrollPane spDescripcion;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
