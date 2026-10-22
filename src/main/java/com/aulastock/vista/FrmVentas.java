/*
 * Formulario generado para el proyecto AulaStock.
 */

///cesarrrrrrrrrrrrrrrrrrrrr
package com.aulastock.vista;

import com.aulastock.dao.ventasDAO;
import com.aulastock.modelo.Clientes;
import com.aulastock.modelo.Productos;
import com.aulastock.modelo.Ventas;
import com.aulastock.modelo.DetalleVenta;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmVentas extends javax.swing.JFrame {

    private ventasDAO ventaDAO = new ventasDAO();
    private ArrayList<DetalleVenta> detallesVenta = new ArrayList<>();

    public FrmVentas() {
        initComponents();
        btnAgregarProducto.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnQuitarProducto.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnRegistrarVenta.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnCancelarVenta.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        javax.swing.table.DefaultTableCellRenderer encabezadoTabla =
                new javax.swing.table.DefaultTableCellRenderer();
        encabezadoTabla.setBackground(new java.awt.Color(26, 48, 76));
        encabezadoTabla.setForeground(java.awt.Color.WHITE);
        encabezadoTabla.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        encabezadoTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        encabezadoTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 10, 8, 10));
        tblDetalleVenta.getTableHeader().setDefaultRenderer(encabezadoTabla);
        getContentPane().setBackground(new java.awt.Color(244, 247, 251));
        spDetalleVenta.getViewport().setBackground(java.awt.Color.WHITE);
        tblDetalleVenta.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblDetalleVenta.getTableHeader().setBackground(new java.awt.Color(26, 48, 76));
        tblDetalleVenta.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblDetalleVenta.getTableHeader().setPreferredSize(new java.awt.Dimension(800, 36));
        tblDetalleVenta.getTableHeader().setReorderingAllowed(false);
        setSize(1180, 760);
        setLocationRelativeTo(null);
        prepararNuevaVenta();
        cargarClientes();
        cargarProductos();
    }

    private void cargarClientes() {
        cboCliente.removeAllItems();
        ArrayList<Clientes> listaClientes = ventaDAO.cargarClientes();

        for (Clientes cargarCliente : listaClientes) {
            cboCliente.addItem(cargarCliente);
        }

    }

    private void cargarProductos() {
        cboProducto.removeAllItems();
        ArrayList<Productos> listaProductos;
        listaProductos= ventaDAO.cargarProductos();

        for (Productos producto : listaProductos) {
            cboProducto.addItem(producto);
        }

        mostrarPrecioProducto();
    }

    private void mostrarPrecioProducto() {
        Productos producto = (Productos) cboProducto.getSelectedItem();

        if (producto == null) {
            txtPrecio.setText("");
        } else {
            txtPrecio.setText(String.format(Locale.US, "%.2f", producto.getPrecio()));
        }
    }

    private void prepararNuevaVenta() {
        detallesVenta.clear();
        mostrarDetalleVenta();
        txtNumeroVenta.setText("AUTOMATICO");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         txtFecha.setText(LocalDate.now().format(formato));
        txtNumeroDocumento.setText("");
        txtCantidad.setText("");
        cboTipoDocumento.setSelectedIndex(0);
    }

    private void mostrarDetalleVenta() {
        DefaultTableModel tablaDetalle = (DefaultTableModel) tblDetalleVenta.getModel();
        tablaDetalle.setRowCount(0);
        BigDecimal total = BigDecimal.ZERO;

        for (DetalleVenta detalle : detallesVenta) {
            BigDecimal subtotal = BigDecimal.valueOf(detalle.getPrecioUnitario())
                    .setScale(2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(detalle.getCantidad()));
            detalle.setSubtotal(subtotal.doubleValue());

            Object[] filaDetalle = new Object[5];
            filaDetalle[0] = detalle.getCodigo();
            filaDetalle[1] = detalle.getNombre();
            filaDetalle[2] = detalle.getCantidad();
            filaDetalle[3] = String.format(Locale.US, "%.2f", detalle.getPrecioUnitario());
            filaDetalle[4] = subtotal.toPlainString();
            tablaDetalle.addRow(filaDetalle);

            total = total.add(subtotal);
        }

        lblTotal.setText("S/ " + total.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    private void cboProductoActionPerformed(java.awt.event.ActionEvent evt) {
        mostrarPrecioProducto();
    }

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        Productos producto = (Productos) cboProducto.getSelectedItem();
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException errorNumero) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad entera valida.");
            txtCantidad.requestFocus();
            return;
        }

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.");
            return;
        }
        if (!Double.isFinite(producto.getPrecio()) || producto.getPrecio() <= 0) {
            JOptionPane.showMessageDialog(this, "El producto debe tener un precio mayor que cero.");
            return;
        }

        DetalleVenta detalleEncontrado = null;
        int cantidadAgregada = 0;
        for (DetalleVenta detalle : detallesVenta) {
            if (detalle.getIdProducto() == producto.getIdProductos()) {
                detalleEncontrado = detalle;
                cantidadAgregada = detalle.getCantidad();
                break;
            }
        }

        if (cantidad > producto.getStock() - cantidadAgregada) {
            JOptionPane.showMessageDialog(this,
                    "Stock disponible: " + producto.getStock()
                    + ". Ya agregados a esta venta: " + cantidadAgregada);
            return;
        }

        if (detalleEncontrado != null) {
            detalleEncontrado.setCantidad(cantidadAgregada + cantidad);
        } else {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdProducto(producto.getIdProductos());
            detalle.setCodigo(producto.getCodigo());
            detalle.setNombre(producto.getNombre());
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(producto.getPrecio());
            detallesVenta.add(detalle);
        }

        txtNumeroVenta.setText("AUTOMATICO");
        mostrarDetalleVenta();
        txtCantidad.setText("");
        txtCantidad.requestFocus();
    }

    private void btnQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        int filaSeleccionada = tblDetalleVenta.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione el producto que desea quitar.");
            return;
        }

        int posicionDetalle = tblDetalleVenta.convertRowIndexToModel(filaSeleccionada);
        detallesVenta.remove(posicionDetalle);
        mostrarDetalleVenta();
    }

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {
        if (!detallesVenta.isEmpty() || !txtNumeroDocumento.getText().trim().isEmpty()) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Desea limpiar la venta que todavia no ha registrado?",
                    "Cancelar venta", JOptionPane.YES_NO_OPTION);
            if (respuesta != JOptionPane.YES_OPTION) {
                return;
            }
        }

        prepararNuevaVenta();
        mostrarPrecioProducto();
        txtNumeroDocumento.requestFocus();
    }

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {
        Clientes cliente = (Clientes) cboCliente.getSelectedItem();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente.");
            return;
        }
        if (detallesVenta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregue al menos un producto.");
            return;
        }

        String numeroDocumento = txtNumeroDocumento.getText().trim();
        if (numeroDocumento.isEmpty() || numeroDocumento.length() > 30) {
            JOptionPane.showMessageDialog(this, "Ingrese un numero de documento de hasta 30 caracteres.");
            txtNumeroDocumento.requestFocus();
            return;
        }

        Ventas venta = new Ventas();
        venta.setFecha(txtFecha.getText().trim());
        venta.setIdCliente(cliente.getIdCliente());
        venta.setTipoDocumento(cboTipoDocumento.getSelectedItem().toString());
        venta.setNumeroDocumento(numeroDocumento);

        boolean registroExitoso = ventaDAO.registrar(venta, detallesVenta);
        if (registroExitoso) {
            JOptionPane.showMessageDialog(this,
                    "Venta registrada correctamente. Numero de venta: " + venta.getIdVenta());
            prepararNuevaVenta();
            txtNumeroVenta.setText(String.valueOf(venta.getIdVenta()));
            cargarProductos();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se guardo la venta. Revise el detalle del error en la consola.");
        }
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
        lblTipoDocumento = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox<>();
        lblNumeroDocumento = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
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
        lblTitulo.setText("REGISTRO DE VENTAS");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 0, 1180, 72);

        lblNumeroVenta.setText("Nro. venta");
        getContentPane().add(lblNumeroVenta);
        lblNumeroVenta.setBounds(30, 90, 100, 25);

        txtNumeroVenta.setEditable(false);
        getContentPane().add(txtNumeroVenta);
        txtNumeroVenta.setBounds(130, 90, 150, 34);

        lblFecha.setText("Fecha");
        getContentPane().add(lblFecha);
        lblFecha.setBounds(310, 90, 70, 25);
        getContentPane().add(txtFecha);
        txtFecha.setEditable(false);
        txtFecha.setBounds(380, 90, 150, 34);

        lblCliente.setText("Cliente");
        getContentPane().add(lblCliente);
        lblCliente.setBounds(560, 90, 70, 25);
        getContentPane().add(cboCliente);
        cboCliente.setBounds(630, 90, 420, 34);

        lblTipoDocumento.setText("Tipo de documento");
        getContentPane().add(lblTipoDocumento);
        lblTipoDocumento.setBounds(30, 140, 130, 25);

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "Boleta", "Factura", "Otro"
        }));
        getContentPane().add(cboTipoDocumento);
        cboTipoDocumento.setBounds(170, 140, 180, 34);

        lblNumeroDocumento.setText("Nro. documento");
        getContentPane().add(lblNumeroDocumento);
        lblNumeroDocumento.setBounds(390, 140, 120, 25);

        getContentPane().add(txtNumeroDocumento);
        txtNumeroDocumento.setBounds(520, 140, 250, 34);

        lblProducto.setText("Producto");
        getContentPane().add(lblProducto);
        lblProducto.setBounds(30, 195, 80, 25);
        getContentPane().add(cboProducto);
        cboProducto.addActionListener(this::cboProductoActionPerformed);
        cboProducto.setBounds(110, 195, 360, 34);

        lblCantidad.setText("Cantidad");
        getContentPane().add(lblCantidad);
        lblCantidad.setBounds(500, 195, 80, 25);
        getContentPane().add(txtCantidad);
        txtCantidad.setBounds(580, 195, 100, 34);

        lblPrecio.setText("Precio");
        getContentPane().add(lblPrecio);
        lblPrecio.setBounds(710, 195, 70, 25);

        txtPrecio.setEditable(false);
        getContentPane().add(txtPrecio);
        txtPrecio.setBounds(780, 195, 120, 34);

        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(this::btnAgregarProductoActionPerformed);
        getContentPane().add(btnAgregarProducto);
        btnAgregarProducto.setBounds(925, 192, 116, 38);

        btnQuitarProducto.setText("Quitar");
        btnQuitarProducto.addActionListener(this::btnQuitarProductoActionPerformed);
        getContentPane().add(btnQuitarProducto);
        btnQuitarProducto.setBounds(1048, 192, 92, 38);

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
        spDetalleVenta.setBounds(30, 250, 1110, 310);
        lblTotalTexto.setText("TOTAL");
        getContentPane().add(lblTotalTexto);
        lblTotalTexto.setBounds(780, 590, 100, 35);
        lblTotal.setText("S/ 0.00");
        getContentPane().add(lblTotal);
        lblTotal.setBounds(900, 590, 200, 35);

        btnRegistrarVenta.setText("Registrar venta");
        btnRegistrarVenta.addActionListener(this::btnRegistrarVentaActionPerformed);
        getContentPane().add(btnRegistrarVenta);
        btnRegistrarVenta.setBounds(735, 645, 190, 42);

        btnCancelarVenta.setText("Cancelar");
        btnCancelarVenta.addActionListener(this::btnCancelarVentaActionPerformed);
        getContentPane().add(btnCancelarVenta);
        btnCancelarVenta.setBounds(945, 645, 155, 42);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 25));
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroVenta.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblNumeroVenta.setForeground(new java.awt.Color(99, 115, 134));
        txtNumeroVenta.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNumeroVenta.setForeground(new java.awt.Color(39, 56, 77));
        txtNumeroVenta.setBackground(new java.awt.Color(231, 237, 245));
        txtNumeroVenta.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblFecha.setForeground(new java.awt.Color(99, 115, 134));
        txtFecha.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFecha.setForeground(new java.awt.Color(39, 56, 77));
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCliente.setForeground(new java.awt.Color(99, 115, 134));
        cboCliente.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboCliente.setForeground(new java.awt.Color(39, 56, 77));
        cboCliente.setBackground(new java.awt.Color(255, 255, 255));
        cboCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        lblTipoDocumento.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTipoDocumento.setForeground(new java.awt.Color(99, 115, 134));
        cboTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboTipoDocumento.setForeground(new java.awt.Color(39, 56, 77));
        cboTipoDocumento.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoDocumento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        lblNumeroDocumento.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblNumeroDocumento.setForeground(new java.awt.Color(99, 115, 134));
        txtNumeroDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNumeroDocumento.setForeground(new java.awt.Color(39, 56, 77));
        txtNumeroDocumento.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblProducto.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblProducto.setForeground(new java.awt.Color(99, 115, 134));
        cboProducto.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cboProducto.setForeground(new java.awt.Color(39, 56, 77));
        cboProducto.setBackground(new java.awt.Color(255, 255, 255));
        cboProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        lblCantidad.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblCantidad.setForeground(new java.awt.Color(99, 115, 134));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtCantidad.setForeground(new java.awt.Color(39, 56, 77));
        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblPrecio.setForeground(new java.awt.Color(99, 115, 134));
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPrecio.setForeground(new java.awt.Color(39, 56, 77));
        txtPrecio.setBackground(new java.awt.Color(231, 237, 245));
        txtPrecio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)), javax.swing.BorderFactory.createEmptyBorder(5, 9, 5, 9)));
        spDetalleVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 220, 231)));
        tblDetalleVenta.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblDetalleVenta.setForeground(new java.awt.Color(39, 56, 77));
        tblDetalleVenta.setBackground(new java.awt.Color(255, 255, 255));
        tblDetalleVenta.setRowHeight(30);
        tblDetalleVenta.setShowVerticalLines(false);
        tblDetalleVenta.setGridColor(new java.awt.Color(232, 237, 244));
        tblDetalleVenta.setSelectionBackground(new java.awt.Color(218, 234, 248));
        tblDetalleVenta.setSelectionForeground(new java.awt.Color(26, 48, 76));
        lblTotalTexto.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblTotalTexto.setForeground(new java.awt.Color(26, 48, 76));
        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTotal.setForeground(new java.awt.Color(20, 126, 103));
        lblTitulo.setBackground(new java.awt.Color(26, 48, 76));
        lblTitulo.setOpaque(true);
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0));
        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnAgregarProducto.setBackground(new java.awt.Color(226, 238, 252));
        btnAgregarProducto.setForeground(new java.awt.Color(26, 48, 76));
        btnAgregarProducto.setOpaque(true);
        btnAgregarProducto.setFocusPainted(false);
        btnAgregarProducto.setIconTextGap(8);
        btnAgregarProducto.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/agregar.png")));
        btnQuitarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnQuitarProducto.setBackground(new java.awt.Color(253, 232, 235));
        btnQuitarProducto.setForeground(new java.awt.Color(143, 32, 47));
        btnQuitarProducto.setOpaque(true);
        btnQuitarProducto.setFocusPainted(false);
        btnQuitarProducto.setIconTextGap(8);
        btnQuitarProducto.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnQuitarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/quitar.png")));
        btnRegistrarVenta.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnRegistrarVenta.setBackground(new java.awt.Color(20, 126, 103));
        btnRegistrarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVenta.setOpaque(true);
        btnRegistrarVenta.setFocusPainted(false);
        btnRegistrarVenta.setIconTextGap(8);
        btnRegistrarVenta.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnRegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/guardar-claro.png")));
        btnCancelarVenta.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnCancelarVenta.setBackground(new java.awt.Color(225, 232, 240));
        btnCancelarVenta.setForeground(new java.awt.Color(39, 56, 77));
        btnCancelarVenta.setOpaque(true);
        btnCancelarVenta.setFocusPainted(false);
        btnCancelarVenta.setIconTextGap(8);
        btnCancelarVenta.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 10, 7, 10));
        btnCancelarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/cancelar.png")));

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
    private javax.swing.JComboBox<String> cboTipoDocumento;
    private javax.swing.JLabel lblTipoDocumento;
    private javax.swing.JLabel lblNumeroDocumento;
    private javax.swing.JTextField txtNumeroDocumento;
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
