package com.aulastock.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=AulaStock;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "aulaStock_user";
    private static final String CLAVE = "proyecto2026#sql";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException error) {
            System.out.println("Error al conectar: " + error.getMessage());
            return null;
        }
    }
}
