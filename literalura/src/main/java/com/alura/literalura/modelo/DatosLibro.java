package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Representa los datos de un libro obtenidos de una fuente externa, como una API.
 * Incluye el título del libro, los autores, los temas, los idiomas y el contador de descargas.
 *
 * Esta clase está diseñada para manejar la deserialización de un objeto JSON que
 * contiene información relacionada con un libro, mapeando las propiedades
 * específicas del JSON a las propiedades correspondientes en Java.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora cualquier propiedad desconocida al deserializar JSON.
public record DatosLibro(
        @JsonAlias("title") String titulo, // Mapea la propiedad JSON "title" al campo "titulo".
        @JsonAlias("authors") List<DatosAutor> autores, // Mapea la propiedad JSON "authors" al campo "autores".
        @JsonAlias("subjects") List<String> temas, // Mapea la propiedad JSON "subjects" al campo "temas".
        @JsonAlias("languages") List<String> idiomas, // Mapea la propiedad JSON "languages" al campo "idiomas".
        @JsonAlias("download_count") Integer contadorDeDescargas // Mapea la propiedad JSON "download_count" al campo "contadorDeDescargas".
) {}
