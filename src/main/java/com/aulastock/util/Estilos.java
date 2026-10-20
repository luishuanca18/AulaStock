package com.aulastock.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/** jhoana
 * Estilo visual compartido de AulaStock.
 * Cada ventana llama a estos metodos en su constructor, despues de initComponents().
 * Para cambiar un color o una fuente en todo el sistema, se cambia solo aqui.
 */
public final class Estilos {

    // ===================== PALETA =====================
    public static final Color PRIMARIO        = new Color(0x1F3A5F); // azul marino: encabezado, boton principal, cabecera de tabla
    public static final Color PRIMARIO_HOVER  = new Color(0x2B4C7E); // boton principal al pasar el mouse
    public static final Color FONDO           = new Color(0xF4F6F8); // fondo de la ventana
    public static final Color SUPERFICIE      = Color.WHITE;         // campos, combos, filas de tabla
    public static final Color FILA_ALTERNA    = new Color(0xF8FAFC); // filas pares de la tabla
    public static final Color SELECCION       = new Color(0xDCE6F2); // fila seleccionada
    public static final Color BORDE           = new Color(0xCBD2D9); // bordes de campos y tabla
    public static final Color TEXTO           = new Color(0x1F2933); // texto principal
    public static final Color TEXTO_SECUNDARIO = new Color(0x52606D); // etiquetas
    public static final Color ALERTA_FONDO    = new Color(0xFDECEA); // fila con problema (ej. bajo stock)
    public static final Color ALERTA_TEXTO    = new Color(0x9B2C2C); // texto de alerta
    public static final Color EXITO_TEXTO     = new Color(0x2F6B47); // texto de estado correcto

    // ===================== TIPOGRAFIA =====================
    private static final String FAMILIA = "Segoe UI";
    public static final Font FUENTE_TITULO   = new Font(FAMILIA, Font.BOLD, 22);
    public static final Font FUENTE_SECCION  = new Font(FAMILIA, Font.BOLD, 15);
    public static final Font FUENTE_ETIQUETA = new Font(FAMILIA, Font.PLAIN, 13);
    public static final Font FUENTE_NORMAL   = new Font(FAMILIA, Font.PLAIN, 13);
    public static final Font FUENTE_NEGRITA  = new Font(FAMILIA, Font.BOLD, 13);

    // ===================== MEDIDAS =====================
    public static final int ALTO_ENCABEZADO = 70;
    public static final int ALTO_FILA_TABLA = 28;

    private Estilos() {
    }

    // ===================== VENTANA =====================

    public static void ventana(JFrame ventana) {
        ventana.getContentPane().setBackground(FONDO);
    }

    /** Banda azul superior con el titulo de la ventana. El label debe ocupar todo el ancho. */
    public static void encabezado(JLabel titulo) {
        titulo.setOpaque(true);
        titulo.setBackground(PRIMARIO);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(FUENTE_TITULO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        titulo.setHorizontalAlignment(SwingConstants.LEFT);
    }

    /** Titulo de una seccion dentro de la ventana, con linea inferior. */
    public static void seccion(JLabel seccion) {
        seccion.setFont(FUENTE_SECCION);
        seccion.setForeground(PRIMARIO);
        seccion.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARIO));
    }

    // ===================== CONTROLES =====================

    public static void etiquetas(JLabel... etiquetas) {
        for (JLabel etiqueta : etiquetas) {
            etiqueta.setFont(FUENTE_ETIQUETA);
            etiqueta.setForeground(TEXTO_SECUNDARIO);
        }
    }

    /** Campos de texto (JTextField) y areas de texto (JTextArea). */
    public static void campos(JTextComponent... campos) {
        for (JTextComponent campo : campos) {
            campo.setFont(FUENTE_NORMAL);
            campo.setForeground(TEXTO);
            campo.setBackground(SUPERFICIE);
            campo.setBorder(bordeCampo(BORDE));
            campo.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    campo.setBorder(bordeCampo(PRIMARIO));
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    campo.setBorder(bordeCampo(BORDE));
                }
            });
        }
    }

    public static void combos(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            combo.setFont(FUENTE_NORMAL);
            combo.setForeground(TEXTO);
            combo.setBackground(SUPERFICIE);
            combo.setBorder(BorderFactory.createLineBorder(BORDE));
        }
    }

    public static void casillas(JCheckBox... casillas) {
        for (JCheckBox casilla : casillas) {
            casilla.setFont(FUENTE_ETIQUETA);
            casilla.setForeground(TEXTO);
            casilla.setOpaque(false);
            casilla.setFocusPainted(false);
        }
    }

    /** Boton de la accion principal (Guardar, Registrar, Buscar): fondo azul marino, texto blanco. */
    public static void botonPrimario(JButton boton) {
        prepararBoton(boton, PRIMARIO, Color.WHITE, PRIMARIO);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(PRIMARIO_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(PRIMARIO);
            }
        });
    }

    /** Boton de accion secundaria (Limpiar, Actualizar, Cancelar): fondo blanco, borde y texto azul. */
    public static void botonSecundario(JButton boton) {
        prepararBoton(boton, SUPERFICIE, PRIMARIO, PRIMARIO);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(SELECCION);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(SUPERFICIE);
            }
        });
    }

    // ===================== TABLAS =====================

    /** Cabecera azul, filas alternadas, sin lineas verticales. */
    public static void tabla(JTable tabla, JScrollPane contenedor) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.setForeground(TEXTO);
        tabla.setRowHeight(ALTO_FILA_TABLA);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(BORDE);
        tabla.setSelectionBackground(SELECCION);
        tabla.setSelectionForeground(TEXTO);
        tabla.setFillsViewportHeight(true);
        tabla.setBackground(SUPERFICIE);
        tabla.setDefaultRenderer(Object.class, new RendererFilas());

        JTableHeader cabecera = tabla.getTableHeader();
        cabecera.setReorderingAllowed(false);
        cabecera.setPreferredSize(new java.awt.Dimension(cabecera.getPreferredSize().width, 32));
        cabecera.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor,
                    boolean seleccionado, boolean foco, int fila, int columna) {
                super.getTableCellRendererComponent(t, valor, false, false, fila, columna);
                setBackground(PRIMARIO);
                setForeground(Color.WHITE);
                setFont(FUENTE_NEGRITA);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });

        contenedor.setBorder(BorderFactory.createLineBorder(BORDE));
        contenedor.getViewport().setBackground(SUPERFICIE);
    }

    /**
     * Renderer base de filas: alterna colores y deja margen interno.
     * Para resaltar filas especiales, extiende esta clase y sobreescribe colorDeFila().
     */
    public static class RendererFilas extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                boolean seleccionado, boolean foco, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, seleccionado, false, fila, columna);
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            setFont(FUENTE_NORMAL);
            if (!seleccionado) {
                setBackground(colorDeFila(tabla, fila));
                setForeground(TEXTO);
            }
            return this;
        }

        protected Color colorDeFila(JTable tabla, int fila) {
            return (fila % 2 == 0) ? SUPERFICIE : FILA_ALTERNA;
        }
    }

    // ===================== AUXILIARES =====================

    private static void prepararBoton(JButton boton, Color fondo, Color texto, Color borde) {
        boton.setUI(new BasicButtonUI()); // mismo aspecto con cualquier Look and Feel
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(FUENTE_NEGRITA);
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createLineBorder(borde, 1));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private static Border bordeCampo(Color color) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color),
                BorderFactory.createEmptyBorder(0, 8, 0, 8));
    }
}
