package com.aluracursos.BooksAPI.principal;

import com.aluracursos.BooksAPI.model.Datos;
import com.aluracursos.BooksAPI.service.ConsumoAPI;
import com.aluracursos.BooksAPI.service.ConvierteDatos;

public class Principal {
    private static final  String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void mostrarMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        System.out.println(datos);

    }
}
