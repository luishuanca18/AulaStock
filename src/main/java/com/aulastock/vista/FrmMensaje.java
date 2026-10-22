package com.aulastock.vista;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class FrmMensaje extends javax.swing.JDialog {

    public FrmMensaje(java.awt.Frame padre, String titulo, String mensaje, String tipo) {
        super(padre, true);
        initComponents();
        setTitle(titulo);
        lblTitulo.setText(titulo);
        txtMensaje.setText(mensaje);
        txtMensaje.setCaretPosition(0);

        Color color = new Color(26, 48, 76);
        String imagen = "mensaje-aviso.png";
        lblTipo.setText("Revisa la informacion");
        if (tipo.equals("exito")) {
            color = new Color(18, 124, 104);
            imagen = "mensaje-exito.png";
            lblTipo.setText("Operacion completada");
        } else if (tipo.equals("error")) {
            color = new Color(181, 49, 65);
            imagen = "mensaje-error.png";
            lblTipo.setText("La operacion no se completo");
        }
        lblIcono.setIcon(new ImageIcon(getClass().getResource("/imagenes/interfaz/" + imagen)));
        btnAceptar.setBackground(color);
        btnAceptar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        btnCerrar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        getRootPane().setDefaultButton(btnAceptar);
        getRootPane().registerKeyboardAction(this::btnCerrarActionPerformed,
                KeyStroke.getKeyStroke("ESCAPE"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        setSize(500, 260);
        setLocationRelativeTo(padre);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlMensaje = new javax.swing.JPanel();
        lblSistema = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblIcono = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JTextArea();
        pnlAcciones = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setUndecorated(true);
        getContentPane().setLayout(null);
        pnlMensaje.setBackground(java.awt.Color.WHITE);
        pnlMensaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 225, 234)));
        pnlMensaje.setLayout(null);
        lblSistema.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSistema.setForeground(new java.awt.Color(99, 115, 134));
        lblSistema.setText("AulaStock");
        pnlMensaje.add(lblSistema);
        lblSistema.setBounds(24, 15, 200, 22);
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 0, 22));
        btnCerrar.setForeground(new java.awt.Color(99, 115, 134));
        btnCerrar.setText("\u00d7");
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setToolTipText("Cerrar mensaje");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);
        pnlMensaje.add(btnCerrar);
        btnCerrar.setBounds(446, 8, 36, 32);
        lblIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/interfaz/mensaje-aviso.png")));
        pnlMensaje.add(lblIcono);
        lblIcono.setBounds(28, 65, 56, 56);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 21));
        lblTitulo.setForeground(new java.awt.Color(26, 48, 76));
        lblTitulo.setText("Revisa los datos");
        pnlMensaje.add(lblTitulo);
        lblTitulo.setBounds(108, 64, 355, 32);
        txtMensaje.setEditable(false);
        txtMensaje.setFocusable(false);
        txtMensaje.setBackground(java.awt.Color.WHITE);
        txtMensaje.setForeground(new java.awt.Color(83, 99, 117));
        txtMensaje.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setText("Selecciona una categoria para continuar.");
        txtMensaje.setBorder(null);
        pnlMensaje.add(txtMensaje);
        txtMensaje.setBounds(108, 105, 350, 65);
        pnlAcciones.setBackground(new java.awt.Color(244, 247, 251));
        pnlAcciones.setLayout(null);
        lblTipo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblTipo.setForeground(new java.awt.Color(99, 115, 134));
        lblTipo.setText("Revisa la informacion");
        pnlAcciones.add(lblTipo);
        lblTipo.setBounds(23, 24, 290, 22);
        btnAceptar.setBackground(new java.awt.Color(26, 48, 76));
        btnAceptar.setForeground(java.awt.Color.WHITE);
        btnAceptar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 16, 6, 16));
        btnAceptar.setFocusPainted(false);
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);
        pnlAcciones.add(btnAceptar);
        btnAceptar.setBounds(344, 17, 126, 36);
        pnlMensaje.add(pnlAcciones);
        pnlAcciones.setBounds(1, 190, 498, 69);
        getContentPane().add(pnlMensaje);
        pnlMensaje.setBounds(0, 0, 500, 260);
        setSize(new java.awt.Dimension(500, 260));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblSistema;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTextArea txtMensaje;
    // End of variables declaration//GEN-END:variables
}
