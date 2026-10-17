package com.aulastock.app;
import java.sql.Connection;
import java.sql.SQLException;
import com.aulastock.util.ConexionSQL;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import com.aulastock.dao.CategoriaDAO;
import com.aulastock.modelo.Categoria;
import com.aulastock.vista.FrmPrincipal;


public class Main {
    public static void main(String[] args) {
Connection cn = ConexionSQL.conectar();

if (cn != null) {
    System.out.println("Conexion exitosa con AulaStock.");

    try {
        cn.close();
    } catch (SQLException error) {
        System.out.println("Error al cerrar: " + error.getMessage());
    }
} else {
    System.out.println("No se pudo conectar con AulaStock.");
 }
}
}
