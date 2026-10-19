package com.aulastock.modelo;

public class Productos {
    private int idProductos;
    private String nombre;

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

    @Override
    public String toString() {
        return  nombre;

    }
}
