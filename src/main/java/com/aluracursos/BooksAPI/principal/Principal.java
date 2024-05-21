package com.aluracursos.BooksAPI.principal;

import com.aluracursos.BooksAPI.model.BookData;
import com.aluracursos.BooksAPI.model.Datos;
import com.aluracursos.BooksAPI.service.ConsumoAPI;
import com.aluracursos.BooksAPI.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final  String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void mostrarMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(json);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        //System.out.println(datos);
        Scanner teclado = new Scanner(System.in);

        //top 10 libros mas descargados
        System.out.println("TOP 10 LIBROS MAS DESCARGADOS");
        datos.resultados().stream()
                .sorted(Comparator.comparing(BookData::numeroDescargas).reversed())
                .map(l -> l.titulo().toUpperCase())
                .limit(10)
                .forEach(System.out::println);

        //Buscar libros por nombre
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var titulo = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search="+titulo.replace(" ", "+"));
        var buscarTitulo = convierteDatos.obtenerDatos(json, Datos.class);
        Optional<BookData> libroBuscado = buscarTitulo.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(titulo.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("No hubo coincidencias");
        }

        //Estadisticas
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(d -> d.numeroDescargas() > 0)
                .collect(Collectors.summarizingDouble(BookData::numeroDescargas));
        System.out.println("Promedio de descargas: "+est.getAverage());
        System.out.println("Cantidad maxima de descargaar: "+est.getMax());
        System.out.println("Cantidad minima de descargas: "+est.getMin());
        System.out.println("Total de registros evaluados: "+est.getCount());

    }
}
