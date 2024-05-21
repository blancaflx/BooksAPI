package com.aluracursos.BooksAPI.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase); //la t representa un tipo de dato generico para almanecenar valores desconocidos
}