/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aulastock.vista;

/**
 *
 * @author LuisHuanca
 */
public class FrmPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPrincipal.class.getName());

    public FrmPrincipal() {
        initComponents();
        btnSalir.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnInicio.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnProductos.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnCategorias.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnClientes.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnVentas.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnInventario.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnReportes.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        getContentPane().setBackground(new java.awt.Color(244, 247, 251));

        setSize(1000,650);
         setLocationRelativeTo(null);
         
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        lblNombreSistema = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        pnlEncabezado = new javax.swing.JPanel();
        lblTituloModulo = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        lblBienvenida = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas e Inventarios");
        setBackground(new java.awt.Color(30, 41, 59));
        setResizable(false);
        getContentPane().setLayout(null);
        pnlMenu.setLayout(null);
        lblNombreSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreSistema.setText("AulaStock");
        pnlMenu.add(lblNombreSistema);
        lblNombreSistema.setBounds(10, 30, 210, 60);
        btnSalir.addActionListener(this::btnSalirActionPerformed);
        pnlMenu.add(btnSalir);
        btnSalir.setBounds(20, 548, 190, 44);
        btnInicio.addActionListener(this::btnInicioActionPerformed);
        pnlMenu.add(btnInicio);
        btnInicio.setBounds(20, 130, 190, 44);
        btnProductos.addActionListener(this::btnProductosActionPerformed);
        pnlMenu.add(btnProductos);
        btnProductos.setBounds(20, 188, 190, 44);
        btnCategorias.addActionListener(this::btnCategoriasActionPerformed);
        pnlMenu.add(btnCategorias);
        btnCategorias.setBounds(20, 246, 190, 44);
        btnClientes.addActionListener(this::btnClientesActionPerformed);
        pnlMenu.add(btnClientes);
        btnClientes.setBounds(20, 304, 190, 44);
        btnVentas.addActionListener(this::btnVentasActionPerformed);
        pnlMenu.add(btnVentas);
        btnVentas.setBounds(20, 362, 190, 44);
        btnInventario.addActionListener(this::btnInventarioActionPerformed);
        pnlMenu.add(btnInventario);
        btnInventario.setBounds(20, 420, 190, 44);
        btnReportes.addActionListener(this::btnReportesActionPerformed);
        pnlMenu.add(btnReportes);
        btnReportes.setBounds(20, 478, 190, 44);

        getContentPane().add(pnlMenu);
        pnlMenu.setBounds(0, 0, 230, 650);
        pnlEncabezado.setLayout(null);
        lblTituloModulo.setText("INICIO");
        pnlEncabezado.add(lblTituloModulo);
        lblTituloModulo.setBounds(40, 30, 620, 60);

        getContentPane().add(pnlEncabezado);
        pnlEncabezado.setBounds(230, 0, 770, 118);
        pnlContenido.setInheritsPopupMenu(true);
        pnlContenido.setLayout(null);
        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlContenido.add(lblBienvenida);
        lblBienvenida.setBounds(35, 70, 680, 60);
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlContenido.add(lblDescripcion);
        lblDescripcion.setBounds(35, 140, 680, 70);

        lblUsuario.setText("Administrador");
        pnlContenido.add(lblUsuario);
        lblUsuario.setBounds(40, 425, 250, 25);

        getContentPane().add(pnlContenido);
        pnlContenido.setBounds(230, 118, 770, 532);

        lblNombreSistema.setFont(new java.awt.Font("Segoe UI", 1, 30));
        lblNombreSistema.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloModulo.setFont(new java.awt.Font("Segoe UI", 1, 25));
        lblTituloModulo.setForeground(new java.awt.Color(26, 48, 76));
        lblBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 29));
        lblBienvenida.setForeground(new java.awt.Color(26, 48, 76));
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18));
        lblDescripcion.setForeground(new java.awt.Color(99, 115, 134));
        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblUsuario.setForeground(new java.awt.Color(99, 115, 134));
        pnlMenu.setBackground(new java.awt.Color(26, 48, 76));
        pnlEncabezado.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenido.setBackground(new java.awt.Color(244, 247, 251));
        lblTituloModulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida.setText("Bienvenido a AulaStock");
        lblDescripcion.setText("Sistema de ventas e inventarios");
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnInicio.setBackground(new java.awt.Color(39, 64, 94));
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setOpaque(true);
        btnInicio.setFocusPainted(false);
        btnInicio.setIconTextGap(8);
        btnInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/inicio-claro.png")));
        btnInicio.setText("Inicio");
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProductos.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnProductos.setBackground(new java.awt.Color(39, 64, 94));
        btnProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos.setOpaque(true);
        btnProductos.setFocusPainted(false);
        btnProductos.setIconTextGap(8);
        btnProductos.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/productos-claro.png")));
        btnProductos.setText("Productos");
        btnProductos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCategorias.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnCategorias.setBackground(new java.awt.Color(39, 64, 94));
        btnCategorias.setForeground(new java.awt.Color(255, 255, 255));
        btnCategorias.setOpaque(true);
        btnCategorias.setFocusPainted(false);
        btnCategorias.setIconTextGap(8);
        btnCategorias.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/categorias-claro.png")));
        btnCategorias.setText("Categorías");
        btnCategorias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnClientes.setBackground(new java.awt.Color(39, 64, 94));
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setOpaque(true);
        btnClientes.setFocusPainted(false);
        btnClientes.setIconTextGap(8);
        btnClientes.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/clientes-claro.png")));
        btnClientes.setText("Clientes");
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentas.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnVentas.setBackground(new java.awt.Color(39, 64, 94));
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setOpaque(true);
        btnVentas.setFocusPainted(false);
        btnVentas.setIconTextGap(8);
        btnVentas.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/ventas-claro.png")));
        btnVentas.setText("Ventas");
        btnVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInventario.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnInventario.setBackground(new java.awt.Color(39, 64, 94));
        btnInventario.setForeground(new java.awt.Color(255, 255, 255));
        btnInventario.setOpaque(true);
        btnInventario.setFocusPainted(false);
        btnInventario.setIconTextGap(8);
        btnInventario.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/inventario-claro.png")));
        btnInventario.setText("Inventario");
        btnInventario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportes.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnReportes.setBackground(new java.awt.Color(39, 64, 94));
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setOpaque(true);
        btnReportes.setFocusPainted(false);
        btnReportes.setIconTextGap(8);
        btnReportes.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/reportes-claro.png")));
        btnReportes.setText("Reportes");
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSalir.setBackground(new java.awt.Color(39, 64, 94));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setOpaque(true);
        btnSalir.setFocusPainted(false);
        btnSalir.setIconTextGap(8);
        btnSalir.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 16, 7, 10));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/salir-claro.png")));
        btnSalir.setText("Salir");
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {                                         

        System.exit(0);

    }

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {                                          

    }

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed

    }

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed

    }

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
       
    }

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        
    }

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        
    }

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        
    }

    public static void main(String args[]) {


//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new FrmPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblNombreSistema;
    private javax.swing.JLabel lblTituloModulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}
