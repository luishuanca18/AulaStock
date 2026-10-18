package com.aulastock.modelo;

/**
 * Representa una fila de la tabla movimientoInventario.
 */
public class MovimientoInventario {

    public static final String ENTRADA = "ENTRADA";
    public static final String SALIDA = "SALIDA";

    public static final String MOTIVO_OTRO = "Otro";
    public static final int LARGO_MAXIMO_MOTIVO = 150; // tamano de la columna motivo en la BD

    public static final String[] MOTIVOS_ENTRADA = {
        "Compra a proveedor",
        "Devolución de cliente",
        "Ajuste por inventario físico",
        MOTIVO_OTRO
    };

    public static final String[] MOTIVOS_SALIDA = {
        "Venta",
        "Producto dañado",
        "Pérdida o robo",
        "Uso interno",
        "Ajuste por inventario físico",
        MOTIVO_OTRO
    };

    private int idMovimiento;
    private int idProducto;
    private String tipo;
    private int cantidad;
    private String motivo;

    public MovimientoInventario() {
    }

    public MovimientoInventario(int idProducto, String tipo, int cantidad, String motivo) {
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
