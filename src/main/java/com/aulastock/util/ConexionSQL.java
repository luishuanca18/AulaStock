package com.aulastock.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    private static final String URL = "jdbc:sqlserver://DESKTOP-8E0O1ER\\SQLEXPRESS;databaseName=AulaStock;encrypt=true;trustServerCertificate=true;integratedSecurity=false;instanceName=SQLEXPRESS;";
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
