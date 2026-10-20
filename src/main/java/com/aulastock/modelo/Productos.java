package com.aulastock.modelo;

public class Productos {
    private int idProductos;
    private String nombre;
    private String codigo;
    private double precio;
    private int stock;

    public Productos(int idProductos, String nombre) {
        this.idProductos = idProductos;
        this.nombre = nombre;
    }

    public Productos() {

    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return  nombre;

    }
}
