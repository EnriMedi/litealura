package com.alura.literalura.dominio;

import com.alura.literalura.modelo.entidad.Autor;
import com.alura.literalura.modelo.entidad.Libro;

import java.util.stream.Collectors;

/**
 * Clase DTO (Data Transfer Object) para representar datos de un libro de forma simplificada.
 * Esta clase permite transferir información de libros a la capa de presentación de manera eficiente.
 */
public record LibroDto(
        String titulo,       // Título del libro
        String tema,         // Tema principal del libro
        String idioma,       // Idioma del libro
        Integer descargas,   // Número de veces que el libro ha sido descargado
        String autores       // Lista de nombres de los autores, separados por comas
) {
    /**
     * Constructor que convierte una entidad Libro en un LibroDto.
     *
     * @param libro La entidad Libro que se va a transformar.
     */
    public LibroDto(Libro libro) {
        this(
                libro.getTitulo(), // Obtiene el título del libro.
                libro.getTemas(), // Obtiene el tema del libro.
                libro.getIdioma().getNombre(), // Obtiene el nombre del idioma del libro.
                libro.getContadorDeDescargas(), // Obtiene el número de descargas del libro.
                libro.getAutores() // Obtiene la lista de autores del libro.
                        .stream() // Convierte la lista de autores en un flujo (stream) para procesar los elementos.
                        .map(Autor::getNombre) // Transforma cada autor en su nombre.
                        .collect(Collectors.joining(", ")) // Une los nombres con comas como separador.
        );
    }

    /**
     * Método que genera una representación en texto del libro.
     *
     * @return Una cadena formateada con los datos del libro.
     */
    @Override
    public String toString() {
        return """
                --------- LIBRO ---------
                Título: %s
                Autor(es): %s
                Idioma: %s
                Número de descargas: %d
                -------------------------
                """.formatted(
                titulo,    // Título del libro
                autores,   // Lista de autores
                idioma,    // Idioma del libro
                descargas  // Número de descargas
        );
    }
}
