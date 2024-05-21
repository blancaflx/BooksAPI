package com.aluracursos.BooksAPI.principal;

import com.aluracursos.BooksAPI.model.BookData;
import com.aluracursos.BooksAPI.model.Datos;
import com.aluracursos.BooksAPI.service.ConsumoAPI;
import com.aluracursos.BooksAPI.service.ConvierteDatos;

import java.util.Comparator;

public class Principal {
    private static final  String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void mostrarMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(json);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        //System.out.println(datos);

        //top 10 libros mas descargados
        System.out.println("TOP 10 LIBROS MAS DESCARGADOS");
        datos.resultados().stream()
                .sorted(Comparator.comparing(BookData::numeroDescargas).reversed())
                .map(l -> l.titulo().toUpperCase())
                .limit(10)
                .forEach(System.out::println);

    }
}
