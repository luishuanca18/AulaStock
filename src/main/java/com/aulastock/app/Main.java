package com.aulastock.app;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import com.aulastock.dao.CategoriaDAO;
import com.aulastock.modelo.Categoria;
import com.aulastock.util.ConexionSQL;
import com.aulastock.vista.FrmPrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

    double suma = 0.1+0.2;
        System.out.println(suma);
        System.out.println(Math.round(suma));

    double suma2 = 0;

    suma2 = Math.round(suma * 100.0)/100.0;
        System.out.println(suma2);


    }
}
