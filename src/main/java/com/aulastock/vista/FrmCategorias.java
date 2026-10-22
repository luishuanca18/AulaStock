/*
 * Formulario generado para el proyecto AulaStock.
 */
package com.aulastock.vista;
//DAO significa Data Access Object, en español: Objeto de Acceso a Datos.
import com.aulastock.dao.CategoriaDAO;
import com.aulastock.modelo.Categoria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FrmCategorias extends javax.swing.JFrame {

    public FrmCategorias() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(244, 247, 251));
        spCategorias.getViewport().setBackground(java.awt.Color.WHITE);
        tblCategorias.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblCategorias.getTableHeader().setBackground(new java.awt.Color(26, 48, 76));
        tblCategorias.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblCategorias.getTableHeader().setPreferredSize(new java.awt.Dimension(820, 36));
        tblCategorias.getTableHeader().setReorderingAllowed(false);
        tblCategorias.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblCategorias.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblCategorias.getColumnModel().getColumn(2).setPreferredWidth(470);
        setSize(900, 650);
        setLocationRelativeTo(null);
        chkActivo.setSelected(true);
        txtNombre.requestFocus();
        cargarDatosBDaTblCategorias();
        btnGuardar.setVisible(false);

    }

    private void cargarDatosBDaTblCategorias() {

        DefaultTableModel tblCATEGORIAS = (DefaultTableModel) tblCategorias.getModel();
        tblCATEGORIAS.setRowCount(0);

        CategoriaDAO categoriaDAO= new CategoriaDAO();

       ArrayList<Categoria> listaCategorias;
       listaCategorias=categoriaDAO.listar();

        for ( Categoria categoria : listaCategorias) {
            ArrayList<Object> filaCategorias = new ArrayList<>();
            filaCategorias.add(categoria.getIdCategoria());
            filaCategorias.add(categoria.getNombre());
            filaCategorias.add(categoria.getDescripcion());
            //filaCategorias = {categoria.getIdCategoria(),categoria.getNombre(),categoria.getDescripcion()};
            tblCATEGORIAS.addRow(filaCategorias.toArray());

        }
    }
    
    private void mostrarMensaje(String titulo, String texto, String tipo) {
        FrmMensaje mensaje = new FrmMensaje(this, titulo, texto, tipo);
        mensaje.setVisible(true);
    }

    private void limpiar(){


        txtNombre.setText("");
        txtDescripcion.setText("");

        
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
        spCategorias = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Categorias");
        setBackground(new java.awt.Color(244, 247, 251));
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setText("GESTION DE CATEGORIAS");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setBackground(new java.awt.Color(26, 48, 76));
        lblTitulo.setOpaque(true);
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 0, 900, 72);

        lblIdCategoria.setText("ID Categoria");
        lblIdCategoria.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblIdCategoria.setForeground(new java.awt.Color(99, 115, 134));
        getContentPane().add(lblIdCategoria);
        lblIdCategoria.setBounds(30, 90, 100, 25);

        txtIdCategoria.setEditable(false);
        txtIdCategoria.setBackground(new java.awt.Color(231, 237, 245));
        txtIdCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdCategoria.setForeground(new java.awt.Color(39, 56, 77));
        txtIdCategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        getContentPane().add(txtIdCategoria);
        txtIdCategoria.setBounds(140, 94, 150, 34);

        lblNombre.setText("Nombre");
        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(99, 115, 134));
        getContentPane().add(lblNombre);
        lblNombre.setBounds(320, 90, 100, 25);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(39, 56, 77));
        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        getContentPane().add(txtNombre);
        txtNombre.setBounds(420, 94, 300, 34);

        lblDescripcion.setText("Descripcion");
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(99, 115, 134));
        getContentPane().add(lblDescripcion);
        lblDescripcion.setBounds(30, 135, 100, 25);

        spDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(39, 56, 77));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 9, 6, 9));
        spDescripcion.setViewportView(txtDescripcion);

        getContentPane().add(spDescripcion);
        spDescripcion.setBounds(140, 145, 580, 80);

        chkActivo.setText("Activo");
        chkActivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkActivo.setForeground(new java.awt.Color(39, 56, 77));
        chkActivo.setOpaque(false);
        getContentPane().add(chkActivo);
        chkActivo.setBounds(740, 145, 105, 30);

        btnNuevo.setBackground(new java.awt.Color(226, 238, 252));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(26, 48, 76));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setIconTextGap(8);
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(90, 270, 132, 40);

        btnGuardar.setBackground(new java.awt.Color(20, 126, 103));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categorias/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setIconTextGap(8);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(230, 270, 132, 40);

        btnActualizar.setBackground(new java.awt.Color(226, 238, 252));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(26, 48, 76));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categorias/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setIconTextGap(8);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(370, 270, 132, 40);

        btnEliminar.setBackground(new java.awt.Color(253, 232, 235));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(143, 32, 47));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categorias/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setIconTextGap(8);
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(520, 270, 132, 40);

        btnLimpiar.setBackground(new java.awt.Color(225, 232, 240));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(39, 56, 77));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categorias/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setIconTextGap(8);
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(660, 270, 132, 40);

        spCategorias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCategorias.setBackground(new java.awt.Color(255, 255, 255));
        tblCategorias.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCategorias.setForeground(new java.awt.Color(39, 56, 77));
        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Categoria", "Nombre", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategorias.setGridColor(new java.awt.Color(232, 237, 244));
        tblCategorias.setRowHeight(30);
        tblCategorias.setSelectionBackground(new java.awt.Color(218, 234, 248));
        tblCategorias.setSelectionForeground(new java.awt.Color(26, 48, 76));
        tblCategorias.setShowVerticalLines(false);
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        spCategorias.setViewportView(tblCategorias);
        if (tblCategorias.getColumnModel().getColumnCount() > 0) {
            tblCategorias.getColumnModel().getColumn(0).setResizable(false);
            tblCategorias.getColumnModel().getColumn(1).setResizable(false);
            tblCategorias.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(spCategorias);
        spCategorias.setBounds(30, 365, 820, 230);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        
        limpiar();

        CategoriaDAO categoriaDAO = new CategoriaDAO();

        int siguienteId = categoriaDAO.nuevo();
        txtIdCategoria.setText(""+siguienteId);
        tblCategorias.clearSelection();
        txtNombre.requestFocus();
        btnGuardar.setVisible(true );

    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {

        String id = txtIdCategoria.getText().trim();

        if (id.isEmpty() || txtIdCategoria.getText().equals("AUTOMATICO")) {

            mostrarMensaje("Selecciona una categoria", "Selecciona una categoria de la tabla para eliminarla.", "aviso");
            return;

        }
        int idCategoria = Integer.parseInt(id);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        boolean eliminacionExitosa = categoriaDAO.eliminar(categoria);

        if (eliminacionExitosa) {
            mostrarMensaje("Categoria eliminada", "La categoria se elimino correctamente.", "exito");
            cargarDatosBDaTblCategorias();

            txtIdCategoria.setText("");
            txtNombre.setText("");
            txtDescripcion.setText("");
        }
        else {
            mostrarMensaje("No se pudo eliminar", "No se pudo eliminar la categoria. Revisa el detalle del error en la consola.", "error");
        }

        
    }

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {


        int filaSeleccionada = tblCategorias.getSelectedRow();
        txtIdCategoria.setText(tblCategorias.getValueAt(filaSeleccionada, 0).toString());
        txtNombre.setText(tblCategorias.getValueAt(filaSeleccionada, 1).toString());
        txtDescripcion.setText(tblCategorias.getValueAt(filaSeleccionada, 2).toString());


    }


    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiar();
        txtIdCategoria.setText("");



    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {

        Categoria categoria = new Categoria();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        int filaSeleccionada = tblCategorias.getSelectedRow();

        if (filaSeleccionada == -1){
            mostrarMensaje("Selecciona una categoria", "Selecciona una categoria de la tabla para actualizarla.", "aviso");
            return;
        }
        int idCategoria = Integer.parseInt(tblCategorias.getValueAt(filaSeleccionada, 0).toString());
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(txtNombre.getText().trim());
        categoria.setDescripcion(txtDescripcion.getText().trim());
        categoria.setEstado(chkActivo.isSelected());

        boolean actualizacionExitosa = categoriaDAO.actualizarCategoria(categoria);

        if (actualizacionExitosa) {
            mostrarMensaje("Categoria actualizada", "Los cambios de la categoria se guardaron correctamente.", "exito");
            cargarDatosBDaTblCategorias();
        }else {
            mostrarMensaje("No se pudo actualizar", "No se pudieron guardar los cambios. Revisa el detalle del error en la consola.", "error");
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = new Categoria();

        if(txtNombre.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()){
            mostrarMensaje("Completa los datos", "Ingresa el nombre y la descripcion de la categoria para continuar.", "aviso");
            return;
        }
            categoria.setNombre(txtNombre.getText().trim());
            categoria.setDescripcion(txtDescripcion.getText().trim());
            categoria.setEstado(chkActivo.isSelected());

            boolean registroExitoso =  categoriaDAO.registrar(categoria);
                if (registroExitoso) {
                    mostrarMensaje("Categoria registrada", "La nueva categoria se guardo correctamente.", "exito");
                    txtIdCategoria.setText("");
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    txtNombre.requestFocus();
                } else {
                    mostrarMensaje("No se pudo registrar", "No se pudo guardar la categoria. Revisa el detalle del error en la consola.", "error");
                }

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmCategorias().setVisible(true));


    }



    





























    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIdCategoria;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spCategorias;
    private javax.swing.JScrollPane spDescripcion;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
