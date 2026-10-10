package com.aulastock.app;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import com.aulastock.dao.CategoriaDAO;
import com.aulastock.modelo.Categoria;
import com.aulastock.vista.FrmPrincipal;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {


        CategoriaDAO categoriaDAO = new CategoriaDAO();

        ArrayList prueba =  categoriaDAO.listar();

        System.out.println(prueba);



    }
}
