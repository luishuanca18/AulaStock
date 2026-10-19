/*
 * Formulario generado para el proyecto AulaStock.
 */


// mariiiiiiiiiiiiiiiiiiiii

package com.aulastock.vista;

import com.aulastock.modelo.Categoria;
import com.aulastock.util.ConexionSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FrmProductos extends javax.swing.JFrame {

    public FrmProductos() {
        initComponents();
        btnNuevo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnGuardar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnActualizar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnEliminar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnLimpiar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnBuscar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        javax.swing.table.DefaultTableCellRenderer encabezadoTabla =
                new javax.swing.table.DefaultTableCellRenderer();
        encabezadoTabla.setBackground(new java.awt.Color(26, 48, 76));
        encabezadoTabla.setForeground(java.awt.Color.WHITE);
        encabezadoTabla.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        encabezadoTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezadoTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
        tblProductos.getTableHeader().setDefaultRenderer(encabezadoTabla);
        getContentPane().setBackground(new java.awt.Color(244, 247, 251));
        spProductos.getViewport().setBackground(java.awt.Color.WHITE);
        tblProductos.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblProductos.getTableHeader().setBackground(new java.awt.Color(26, 48, 76));
        tblProductos.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblProductos.getTableHeader().setPreferredSize(new java.awt.Dimension(800, 36));
        tblProductos.getTableHeader().setReorderingAllowed(false);
        setSize(1050, 700);
        setLocationRelativeTo(null);
        cargarCategorias();
        cargarProductos();

    }
    private void cargarProductos() {

        DefaultTableModel tablaProductos = (DefaultTableModel) tblProductos.getModel();
        tablaProductos.setRowCount(0);

        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String consultaProductos ="SELECT P.idProducto,P.codigo,P.nombre,C.nombre AS nombreCategoria,P.precio,P.stock,P.stockMinimo " +
                "FROM producto P INNER JOIN CATEGORIA C ON P.idCategoria = C.idCategoria ORDER BY  P.codigo ";

        try {
            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaProductos);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();

            while (resultadoConsulta.next()) {

                Object[]  productos = new  Object[6];
                productos[0]=resultadoConsulta.getString("codigo");
                productos[1]= resultadoConsulta.getString("nombre");
                productos[2]= resultadoConsulta.getString("nombreCategoria");
                productos[3]=resultadoConsulta.getDouble("precio");
                productos[4] =resultadoConsulta.getInt("stock");
                productos[5]=resultadoConsulta.getInt("stockMinimo");
                tablaProductos.addRow(productos);
            }
            resultadoConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();

        } catch (SQLException e) {
            System.out.println("Error al cargar produtos: " + e.getMessage());
        }
    }

    private void cargarCategorias(){

        cboCategoria.removeAllItems();
        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String consultaCategoria = "select idCategoria,nombre from dbo.CATEGORIA where estado=1 order by nombre";

        try {
            PreparedStatement consultaPreparada = conexionBaseDeDatos.prepareStatement(consultaCategoria);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();

            while (resultadoConsulta.next()) {
                Categoria categoria = new Categoria();

                categoria.setIdCategoria(resultadoConsulta.getInt("idCategoria"));
                categoria.setNombre(resultadoConsulta.getString("nombre"));
                cboCategoria.addItem(categoria);
            }
            resultadoConsulta.close();
            consultaPreparada.close();
            conexionBaseDeDatos.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener la categoria"+e.getMessage());
        }
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
        lblTitulo.setText("GESTION DE PRODUCTOS");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 0, 1050, 72);

        lblCodigo.setText("Codigo");
        getContentPane().add(lblCodigo);
        lblCodigo.setBounds(30, 90, 100, 25);
        getContentPane().add(txtCodigo);
        txtCodigo.setBounds(140, 90, 150, 34);

        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(320, 90, 100, 25);
        getContentPane().add(txtNombre);
        txtNombre.setBounds(420, 90, 300, 34);

        lblCategoria.setText("Categoria");
        getContentPane().add(lblCategoria);
        lblCategoria.setBounds(30, 135, 100, 25);
        getContentPane().add(cboCategoria);
        cboCategoria.setBounds(140, 135, 220, 34);

        lblPrecio.setText("Precio");
        getContentPane().add(lblPrecio);
        lblPrecio.setBounds(390, 135, 80, 25);
        getContentPane().add(txtPrecio);
        txtPrecio.setBounds(470, 135, 120, 34);

        lblStock.setText("Stock");
        getContentPane().add(lblStock);
        lblStock.setBounds(620, 135, 70, 25);
        getContentPane().add(txtStock);
        txtStock.setBounds(690, 135, 100, 34);

        lblStockMinimo.setText("Stock minimo");
        getContentPane().add(lblStockMinimo);
        lblStockMinimo.setBounds(30, 180, 100, 25);
        getContentPane().add(txtStockMinimo);
        txtStockMinimo.setBounds(140, 180, 120, 34);

        chkActivo.setText("Activo");
        getContentPane().add(chkActivo);
        chkActivo.setBounds(300, 180, 100, 28);

        btnNuevo.setText("Nuevo");
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(30, 235, 134, 40);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(174, 235, 134, 40);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(318, 235, 134, 40);

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(462, 235, 134, 40);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(606, 235, 134, 40);

        lblBuscar.setText("Buscar");
        getContentPane().add(lblBuscar);
        lblBuscar.setBounds(30, 295, 80, 25);
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(110, 295, 520, 36);

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(650, 295, 140, 36);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Categoria", "Precio", "Stock", "Stock minimo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        spProductos.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setResizable(false);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblProductos.getColumnModel().getColumn(1).setResizable(false);
            tblProductos.getColumnModel().getColumn(1).setPreferredWidth(260);
            tblProductos.getColumnModel().getColumn(2).setResizable(false);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(160);
            tblProductos.getColumnModel().getColumn(3).setResizable(false);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(4).setResizable(false);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblProductos.getColumnModel().getColumn(5).setResizable(false);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        getContentPane().add(spProductos);
        spProductos.setBounds(30, 345, 760, 280);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 25));
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCodigo.setForeground(new java.awt.Color(99, 115, 134));
        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtCodigo.setForeground(new java.awt.Color(39, 56, 77));
        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblNombre.setForeground(new java.awt.Color(99, 115, 134));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNombre.setForeground(new java.awt.Color(39, 56, 77));
        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCategoria.setForeground(new java.awt.Color(99, 115, 134));
        cboCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboCategoria.setForeground(new java.awt.Color(39, 56, 77));
        cboCategoria.setBackground(new java.awt.Color(255, 255, 255));
        cboCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblPrecio.setForeground(new java.awt.Color(99, 115, 134));
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPrecio.setForeground(new java.awt.Color(39, 56, 77));
        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblStock.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblStock.setForeground(new java.awt.Color(99, 115, 134));
        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtStock.setForeground(new java.awt.Color(39, 56, 77));
        txtStock.setBackground(new java.awt.Color(255, 255, 255));
        txtStock.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblStockMinimo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblStockMinimo.setForeground(new java.awt.Color(99, 115, 134));
        txtStockMinimo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtStockMinimo.setForeground(new java.awt.Color(39, 56, 77));
        txtStockMinimo.setBackground(new java.awt.Color(255, 255, 255));
        txtStockMinimo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        chkActivo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chkActivo.setForeground(new java.awt.Color(39, 56, 77));
        chkActivo.setOpaque(false);
        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBuscar.setForeground(new java.awt.Color(99, 115, 134));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtBuscar.setForeground(new java.awt.Color(39, 56, 77));
        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        spProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        tblProductos.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblProductos.setForeground(new java.awt.Color(39, 56, 77));
        tblProductos.setBackground(new java.awt.Color(255, 255, 255));
        tblProductos.setRowHeight(30);
        tblProductos.setShowVerticalLines(false);
        tblProductos.setGridColor(new java.awt.Color(232, 237, 244));
        tblProductos.setSelectionBackground(new java.awt.Color(218, 234, 248));
        tblProductos.setSelectionForeground(new java.awt.Color(26, 48, 76));
        lblTitulo.setBackground(new java.awt.Color(26, 48, 76));
        lblTitulo.setOpaque(true);
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnNuevo.setBackground(new java.awt.Color(226, 238, 252));
        btnNuevo.setForeground(new java.awt.Color(26, 48, 76));
        btnNuevo.setOpaque(true);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setIconTextGap(8);
        btnNuevo.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/nuevo.png")));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnGuardar.setBackground(new java.awt.Color(20, 126, 103));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setOpaque(true);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setIconTextGap(8);
        btnGuardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/guardar-claro.png")));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnActualizar.setBackground(new java.awt.Color(226, 238, 252));
        btnActualizar.setForeground(new java.awt.Color(26, 48, 76));
        btnActualizar.setOpaque(true);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setIconTextGap(8);
        btnActualizar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/actualizar.png")));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnEliminar.setBackground(new java.awt.Color(253, 232, 235));
        btnEliminar.setForeground(new java.awt.Color(143, 32, 47));
        btnEliminar.setOpaque(true);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setIconTextGap(8);
        btnEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/eliminar.png")));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnLimpiar.setBackground(new java.awt.Color(225, 232, 240));
        btnLimpiar.setForeground(new java.awt.Color(39, 56, 77));
        btnLimpiar.setOpaque(true);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setIconTextGap(8);
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/limpiar.png")));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnBuscar.setBackground(new java.awt.Color(226, 238, 252));
        btnBuscar.setForeground(new java.awt.Color(26, 48, 76));
        btnBuscar.setOpaque(true);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setIconTextGap(8);
        btnBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/buscar.png")));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {


        Connection conexionBaseDeDatos =  ConexionSQL.conectar();
        String usp_actualizar_productos = "{call usp_actualizar_productos(?,?,?,?,?,?,?)}";

        Categoria comboBoxCategoria = (Categoria) cboCategoria.getSelectedItem();

        try {
         CallableStatement actualizarPreparada = conexionBaseDeDatos.prepareCall(usp_actualizar_productos);
         actualizarPreparada.setString(1, txtCodigo.getText());
         actualizarPreparada.setString(2, txtNombre.getText().trim());
         actualizarPreparada.setInt(3, comboBoxCategoria.getIdCategoria());
         actualizarPreparada.setDouble(4, Double.parseDouble(txtPrecio.getText().trim()));
         actualizarPreparada.setInt(5, Integer.parseInt(txtStock.getText().trim()));
         actualizarPreparada.setInt(6, Integer.parseInt(txtStockMinimo.getText().trim()));
         actualizarPreparada.setBoolean(7,chkActivo.isSelected());

         int filasActualizadas = actualizarPreparada.executeUpdate();
         if (filasActualizadas > 0) {
             cargarProductos();
             JOptionPane.showMessageDialog(this,"Se ha actualizado el producto con exito");}
         else  {JOptionPane.showMessageDialog(this,"No se ha actualizado el producto");}

         actualizarPreparada.close();
         conexionBaseDeDatos.close();


        } catch (SQLException e) {
            System.out.println("Error en usp_actualizar_productos: " + e.getMessage());
        }


    }

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {

        String nombreCategoria = tblProductos.getValueAt(tblProductos.getSelectedRow(), 2).toString();

        for (int i = 0 ; i < cboCategoria.getItemCount(); i++) {
            Categoria categoria = (Categoria) cboCategoria.getItemAt(i);
            if (categoria.getNombre().equals(nombreCategoria)) {
                cboCategoria.setSelectedIndex(i);
                break;
            }
        }
        int filaSeleccionada = tblProductos.getSelectedRow();
        txtCodigo.setText(tblProductos.getValueAt(filaSeleccionada, 0).toString());
        txtNombre.setText(tblProductos.getValueAt(filaSeleccionada, 1).toString());
        //cboCategoria.setSelectedItem(tblProductos.getValueAt(filaSeleccionada, 2).toString());
        txtPrecio.setText(tblProductos.getValueAt(filaSeleccionada, 3).toString());
        txtStock.setText(tblProductos.getValueAt(filaSeleccionada, 4).toString());
        txtStockMinimo.setText(tblProductos.getValueAt(filaSeleccionada, 5).toString());

    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {

// las clases son mañana

    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        Categoria categoriaSeleccionada = (Categoria) cboCategoria.getSelectedItem();

        Connection conexionBaseDeDatos = ConexionSQL.conectar();
        String usp_guardar_productos="{call usp_guardar_productos(?,?,?,?,?,?,?)}";

        try {
            CallableStatement guardarProductos = conexionBaseDeDatos.prepareCall(usp_guardar_productos);
            guardarProductos.setString(1, txtCodigo.getText().trim());
            guardarProductos.setString(2, txtNombre.getText().trim());
            guardarProductos.setInt(3,categoriaSeleccionada.getIdCategoria());
            guardarProductos.setDouble(4,Double.parseDouble(txtPrecio.getText().trim()));
            guardarProductos.setInt(5, Integer.parseInt(txtStock.getText().trim()));
            guardarProductos.setInt(6, Integer.parseInt(txtStockMinimo.getText().trim()));
            guardarProductos.setBoolean(7, chkActivo.isSelected());
            int filasAgregadas =  guardarProductos.executeUpdate();
            guardarProductos.close();
            conexionBaseDeDatos.close();

            if (filasAgregadas > 0) {JOptionPane.showMessageDialog(this,"Se guardo el Producto correctamente");}
            else  {JOptionPane.showMessageDialog(this,"No se pudo guardar el Producto ingresado");}

        } catch (SQLException e) {
            System.out.println("Error en usp_guardar_productos " + e.getMessage());
        }


    }

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
