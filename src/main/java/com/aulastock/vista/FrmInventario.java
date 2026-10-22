/*
 * Formulario generado para el proyecto AulaStock.
 */

//rociooooooooooooooooo
package com.aulastock.vista;

import com.aulastock.dao.CategoriaDAO;
import com.aulastock.dao.InventarioDAO;
import com.aulastock.modelo.Categoria;
import com.aulastock.modelo.MovimientoInventario;
import com.aulastock.modelo.ProductoInventario;
import com.aulastock.util.Estilos;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class FrmInventario extends javax.swing.JFrame {

    private static final int COLUMNA_STOCK = 3;
    private static final int COLUMNA_STOCK_MINIMO = 4;
    private static final int COLUMNA_ESTADO = 5;
    private static final String ESTADO_BAJO = "BAJO STOCK";
    private static final String ESTADO_OK = "OK";

    private final InventarioDAO inventarioDAO = new InventarioDAO();
    private ArrayList<ProductoInventario> productosMostrados = new ArrayList<>();
    private boolean cargandoCombos = false;

    public FrmInventario() {
        initComponents();
        setSize(1100, 650);
        setLocationRelativeTo(null);

        aplicarEstilos();
        configurarCombos();
        configurarEventos();
        cargarCategorias();
        cargarProductosMovimiento();
        cargarTablaInventario();
    }

    // ===================== CONFIGURACION INICIAL =====================

    private void aplicarEstilos() {
        Estilos.ventana(this);
        Estilos.encabezado(lblTitulo);
        Estilos.seccion(lblMovimiento);
        Estilos.etiquetas(lblBuscar, lblCategoria, lblProducto, lblTipoMovimiento,
                lblCantidad, lblMotivo, lblDetalle);
        Estilos.campos(txtBuscar, txtCantidad, txtDetalle);
        Estilos.combos(cboCategoria, cboProducto, cboTipoMovimiento, cboMotivo);
        Estilos.casillas(chkBajoStock);
        Estilos.botonPrimario(btnBuscar);
        Estilos.botonSecundario(btnActualizarTabla);
        Estilos.botonPrimario(btnRegistrarMovimiento);
        Estilos.botonSecundario(btnLimpiarMovimiento);
        Estilos.tabla(tblInventario, spInventario);

        // Filas con bajo stock en rojo palido; la columna Estado en rojo o verde
        tblInventario.setDefaultRenderer(Object.class, new Estilos.RendererFilas() {
            @Override
            protected Color colorDeFila(JTable tabla, int fila) {
                boolean bajo = fila < productosMostrados.size() && productosMostrados.get(fila).isBajoStock();
                return bajo ? Estilos.ALERTA_FONDO : super.colorDeFila(tabla, fila);
            }

            @Override
            public Component getTableCellRendererComponent(JTable tabla, Object valor,
                    boolean seleccionado, boolean foco, int fila, int columna) {
                super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
                boolean esNumero = columna == COLUMNA_STOCK || columna == COLUMNA_STOCK_MINIMO;
                setHorizontalAlignment(esNumero ? SwingConstants.CENTER : SwingConstants.LEFT);
                if (columna == COLUMNA_ESTADO) {
                    setFont(Estilos.FUENTE_NEGRITA);
                    setForeground(ESTADO_BAJO.equals(valor) ? Estilos.ALERTA_TEXTO : Estilos.EXITO_TEXTO);
                }
                return this;
            }
        });

        // Anchos de columna: Codigo, Producto, Categoria, Stock, Stock minimo, Estado
        int[] anchos = {90, 330, 180, 120, 120, 160};
        for (int i = 0; i < anchos.length; i++) {
            tblInventario.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    @SuppressWarnings("unchecked")
    private void configurarCombos() {
        cboTipoMovimiento.setModel(new DefaultComboBoxModel<>(
                new String[]{MovimientoInventario.ENTRADA, MovimientoInventario.SALIDA}));
        actualizarMotivos();

        // Categoria.toString() devuelve la descripcion; aqui mostramos el nombre
        cboCategoria.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                Object texto = (value instanceof Categoria) ? ((Categoria) value).getNombre() : value;
                return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
            }
        });
    }

    // Los eventos se registran aqui para no modificar initComponents()
    private void configurarEventos() {
        btnBuscar.addActionListener(evt -> cargarTablaInventario());
        txtBuscar.addActionListener(evt -> cargarTablaInventario());       // Enter en el buscador
        cboCategoria.addActionListener(evt -> {
            if (!cargandoCombos) {
                cargarTablaInventario();
            }
        });
        chkBajoStock.addActionListener(evt -> cargarTablaInventario());
        btnActualizarTabla.addActionListener(evt -> actualizarTodo());
        cboTipoMovimiento.addActionListener(evt -> actualizarMotivos());
        btnRegistrarMovimiento.addActionListener(evt -> registrarMovimiento());
        btnLimpiarMovimiento.addActionListener(evt -> limpiarMovimiento());

        // Al hacer clic en una fila, se selecciona ese producto en el combo
        tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarProductoDeTabla();
            }
        });
    }

    // Los motivos cambian segun el tipo de movimiento elegido
    @SuppressWarnings("unchecked")
    private void actualizarMotivos() {
        String tipo = (String) cboTipoMovimiento.getSelectedItem();
        String[] motivos = MovimientoInventario.SALIDA.equals(tipo)
                ? MovimientoInventario.MOTIVOS_SALIDA
                : MovimientoInventario.MOTIVOS_ENTRADA;
        cboMotivo.setModel(new DefaultComboBoxModel<>(motivos));
    }

    // ===================== CARGA DE DATOS =====================

    @SuppressWarnings("unchecked")
    private void cargarCategorias() {
        cargandoCombos = true;
        DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Todas");
        for (Categoria categoria : new CategoriaDAO().listar()) {
            if (categoria.isEstado()) {
                modelo.addElement(categoria);
            }
        }
        cboCategoria.setModel(modelo);
        cargandoCombos = false;
    }

    @SuppressWarnings("unchecked")
    private void cargarProductosMovimiento() {
        DefaultComboBoxModel<ProductoInventario> modelo = new DefaultComboBoxModel<>();
        for (ProductoInventario producto : inventarioDAO.listar("", 0, false)) {
            modelo.addElement(producto);
        }
        cboProducto.setModel(modelo);
    }

    private void cargarTablaInventario() {
        String texto = txtBuscar.getText().trim();
        Object seleccion = cboCategoria.getSelectedItem();
        int idCategoria = (seleccion instanceof Categoria) ? ((Categoria) seleccion).getIdCategoria() : 0;

        productosMostrados = inventarioDAO.listar(texto, idCategoria, chkBajoStock.isSelected());

        DefaultTableModel modeloTabla = (DefaultTableModel) tblInventario.getModel();
        modeloTabla.setRowCount(0);

        for (ProductoInventario producto : productosMostrados) {
            modeloTabla.addRow(new Object[]{
                producto.getCodigo(),
                producto.getNombre(),
                producto.getNombreCategoria(),
                producto.getStock(),
                producto.getStockMinimo(),
                producto.isBajoStock() ? ESTADO_BAJO : ESTADO_OK
            });
        }
    }

    private void actualizarTodo() {
        txtBuscar.setText("");
        chkBajoStock.setSelected(false);
        cargarCategorias();
        cargarProductosMovimiento();
        cargarTablaInventario();
    }

    private void seleccionarProductoDeTabla() {
        int fila = tblInventario.getSelectedRow();
        if (fila < 0 || fila >= productosMostrados.size()) {
            return;
        }
        int idSeleccionado = productosMostrados.get(fila).getIdProducto();
        for (int i = 0; i < cboProducto.getItemCount(); i++) {
            ProductoInventario producto = (ProductoInventario) cboProducto.getItemAt(i);
            if (producto.getIdProducto() == idSeleccionado) {
                cboProducto.setSelectedIndex(i);
                break;
            }
        }
    }

    // ===================== MOVIMIENTOS =====================

    private void registrarMovimiento() {
        ProductoInventario producto = (ProductoInventario) cboProducto.getSelectedItem();
        String tipo = (String) cboTipoMovimiento.getSelectedItem();
        String motivo = (String) cboMotivo.getSelectedItem();
        String detalle = txtDetalle.getText().trim();
        String textoCantidad = txtCantidad.getText().trim();

        if (producto == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(textoCantidad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad numérica válida");
            txtCantidad.requestFocus();
            return;
        }
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero");
            txtCantidad.requestFocus();
            return;
        }
        if (MovimientoInventario.MOTIVO_OTRO.equals(motivo) && detalle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Si el motivo es \"" + MovimientoInventario.MOTIVO_OTRO
                    + "\", escriba el detalle");
            txtDetalle.requestFocus();
            return;
        }
        String motivoCompleto = detalle.isEmpty() ? motivo : motivo + " - " + detalle;
        if (motivoCompleto.length() > MovimientoInventario.LARGO_MAXIMO_MOTIVO) {
            JOptionPane.showMessageDialog(this, "El detalle es demasiado largo. Máximo "
                    + (MovimientoInventario.LARGO_MAXIMO_MOTIVO - motivo.length() - 3) + " caracteres");
            txtDetalle.requestFocus();
            return;
        }
        if (MovimientoInventario.SALIDA.equals(tipo) && cantidad > producto.getStock()) {
            JOptionPane.showMessageDialog(this,
                    "Stock insuficiente. Stock actual de " + producto.getNombre() + ": " + producto.getStock());
            return;
        }

        try {
            inventarioDAO.registrarMovimiento(
                    new MovimientoInventario(producto.getIdProducto(), tipo, cantidad, motivoCompleto));
            JOptionPane.showMessageDialog(this, "Movimiento registrado correctamente");
            limpiarMovimiento();
            cargarProductosMovimiento();
            cargarTablaInventario();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(this, "No se pudo registrar: " + error.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarMovimiento() {
        if (cboProducto.getItemCount() > 0) {
            cboProducto.setSelectedIndex(0);
        }
        cboTipoMovimiento.setSelectedIndex(0);   // vuelve a ENTRADA y recarga sus motivos
        txtCantidad.setText("");
        txtDetalle.setText("");
        tblInventario.clearSelection();
        txtBuscar.setText("");
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
        cboMotivo = new javax.swing.JComboBox();
        lblDetalle = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JTextField();
        btnRegistrarMovimiento = new javax.swing.JButton();
        btnLimpiarMovimiento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AulaStock - Inventario");
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setText("Control de inventario");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 0, 1100, 70);

        lblBuscar.setText("Buscar producto");
        getContentPane().add(lblBuscar);
        lblBuscar.setBounds(30, 95, 120, 30);

        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(150, 95, 320, 30);

        lblCategoria.setText("Categoría");
        getContentPane().add(lblCategoria);
        lblCategoria.setBounds(500, 95, 80, 30);

        getContentPane().add(cboCategoria);
        cboCategoria.setBounds(580, 95, 230, 30);

        chkBajoStock.setText("Solo bajo stock");
        getContentPane().add(chkBajoStock);
        chkBajoStock.setBounds(840, 95, 160, 30);

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(30, 140, 110, 34);

        btnActualizarTabla.setText("Actualizar");
        getContentPane().add(btnActualizarTabla);
        btnActualizarTabla.setBounds(150, 140, 120, 34);

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Producto", "Categoría", "Stock actual", "Stock mínimo", "Estado"
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
        spInventario.setBounds(30, 190, 1020, 250);

        lblMovimiento.setText("Registrar movimiento");
        getContentPane().add(lblMovimiento);
        lblMovimiento.setBounds(30, 465, 1020, 30);

        lblProducto.setText("Producto");
        getContentPane().add(lblProducto);
        lblProducto.setBounds(30, 515, 80, 30);

        getContentPane().add(cboProducto);
        cboProducto.setBounds(110, 515, 300, 30);

        lblTipoMovimiento.setText("Tipo");
        getContentPane().add(lblTipoMovimiento);
        lblTipoMovimiento.setBounds(440, 515, 50, 30);

        getContentPane().add(cboTipoMovimiento);
        cboTipoMovimiento.setBounds(490, 515, 160, 30);

        lblCantidad.setText("Cantidad");
        getContentPane().add(lblCantidad);
        lblCantidad.setBounds(680, 515, 70, 30);

        getContentPane().add(txtCantidad);
        txtCantidad.setBounds(750, 515, 100, 30);

        lblMotivo.setText("Motivo");
        getContentPane().add(lblMotivo);
        lblMotivo.setBounds(30, 560, 80, 30);

        getContentPane().add(cboMotivo);
        cboMotivo.setBounds(110, 560, 300, 30);

        lblDetalle.setText("Detalle");
        getContentPane().add(lblDetalle);
        lblDetalle.setBounds(440, 560, 50, 30);

        getContentPane().add(txtDetalle);
        txtDetalle.setBounds(490, 560, 360, 30);

        btnRegistrarMovimiento.setText("Registrar");
        getContentPane().add(btnRegistrarMovimiento);
        btnRegistrarMovimiento.setBounds(890, 515, 160, 34);

        btnLimpiarMovimiento.setText("Limpiar");
        getContentPane().add(btnLimpiarMovimiento);
        btnLimpiarMovimiento.setBounds(890, 560, 160, 34);

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
    private javax.swing.JComboBox cboMotivo;
    private javax.swing.JComboBox cboProducto;
    private javax.swing.JComboBox cboTipoMovimiento;
    private javax.swing.JCheckBox chkBajoStock;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblMovimiento;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblTipoMovimiento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane spInventario;
    private javax.swing.JTable tblInventario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDetalle;
    // End of variables declaration//GEN-END:variables
}
