package com.aulastock.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionSQL {

    private static final Properties CONFIGURACION = cargarConfiguracion();
    private static final String URL = CONFIGURACION.getProperty("db.url");
    private static final String USUARIO = CONFIGURACION.getProperty("db.usuario");
    private static final String CLAVE = CONFIGURACION.getProperty("db.clave");

    private static Properties cargarConfiguracion() {
        Properties propiedades = new Properties();

        try (InputStream archivo = ConexionSQL.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (archivo == null) {
                throw new IllegalStateException(
                        "No se encontro database.properties. Copia database.properties.example y completa tus datos locales."
                );
            }

            propiedades.load(archivo);
            return propiedades;

        } catch (IOException error) {
            throw new IllegalStateException(
                    "No se pudo leer la configuracion de la base de datos.", error
            );
        }
    }

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException error) {
            System.out.println("Error al conectar: " + error.getMessage());
            return null;
        }
    }
}
