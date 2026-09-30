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

        pnlMenu.setBackground(new java.awt.Color(39, 41, 59));
        pnlMenu.setLayout(null);

        lblNombreSistema.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblNombreSistema.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreSistema.setText("AulaStock");
        pnlMenu.add(lblNombreSistema);
        lblNombreSistema.setBounds(10, 30, 210, 60);

        btnSalir.setText("salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);
        pnlMenu.add(btnSalir);
        btnSalir.setBounds(50, 560, 120, 40);

        btnInicio.setText("inicio");
        btnInicio.addActionListener(this::btnInicioActionPerformed);
        pnlMenu.add(btnInicio);
        btnInicio.setBounds(50, 150, 120, 40);

        btnProductos.setText("productos");
        btnProductos.addActionListener(this::btnProductosActionPerformed);
        pnlMenu.add(btnProductos);
        btnProductos.setBounds(50, 200, 120, 40);

        btnCategorias.setText("categorias");
        btnCategorias.addActionListener(this::btnCategoriasActionPerformed);
        pnlMenu.add(btnCategorias);
        btnCategorias.setBounds(50, 260, 120, 40);

        btnClientes.setText("clientes");
        btnClientes.addActionListener(this::btnClientesActionPerformed);
        pnlMenu.add(btnClientes);
        btnClientes.setBounds(50, 320, 120, 40);

        btnVentas.setText("ventas");
        btnVentas.addActionListener(this::btnVentasActionPerformed);
        pnlMenu.add(btnVentas);
        btnVentas.setBounds(50, 380, 120, 40);

        btnInventario.setText("inventarios");
        btnInventario.addActionListener(this::btnInventarioActionPerformed);
        pnlMenu.add(btnInventario);
        btnInventario.setBounds(50, 440, 120, 40);

        btnReportes.setText("reportes");
        btnReportes.addActionListener(this::btnReportesActionPerformed);
        pnlMenu.add(btnReportes);
        btnReportes.setBounds(50, 500, 120, 40);

        getContentPane().add(pnlMenu);
        pnlMenu.setBounds(0, 0, 230, 650);

        pnlEncabezado.setBackground(new java.awt.Color(255, 255, 255));
        pnlEncabezado.setLayout(null);

        lblTituloModulo.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblTituloModulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloModulo.setText("INICIO");
        pnlEncabezado.add(lblTituloModulo);
        lblTituloModulo.setBounds(270, 50, 200, 60);

        getContentPane().add(pnlEncabezado);
        pnlEncabezado.setBounds(230, 0, 770, 160);

        pnlContenido.setBackground(new java.awt.Color(0, 153, 153));
        pnlContenido.setInheritsPopupMenu(true);
        pnlContenido.setLayout(null);

        lblBienvenida.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBienvenida.setText("bienvenido a aulastock");
        pnlContenido.add(lblBienvenida);
        lblBienvenida.setBounds(140, 50, 450, 60);

        lblDescripcion.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcion.setText("sistema de ventas e inventarios");
        pnlContenido.add(lblDescripcion);
        lblDescripcion.setBounds(90, 160, 590, 110);

        lblUsuario.setText("Administrador");
        pnlContenido.add(lblUsuario);
        lblUsuario.setBounds(640, 430, 110, 16);

        getContentPane().add(pnlContenido);
        pnlContenido.setBounds(230, 160, 770, 490);

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
