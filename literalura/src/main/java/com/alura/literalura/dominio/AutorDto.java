package com.alura.literalura.dominio;


import com.alura.literalura.modelo.entidad.Autor;
import com.alura.literalura.modelo.entidad.Libro;
import java.util.stream.Collectors;

/**
 * Clase DTO (Data Transfer Object) para representar datos del autor de manera simplificada.
 * Esta clase facilita la transferencia de información del modelo hacia la capa de presentación.
 */
public record AutorDto(
        String nombre,                // Nombre del autor
        Integer anhioDeNacimiento,    // Año de nacimiento del autor
        Integer anhioDeFallecimiento, // Año de fallecimiento del autor (puede ser nulo si el autor está vivo)
        String librosEscritos         // Lista de títulos de libros escritos por el autor, separados por comas
) {
    /**
     * Constructor que convierte una entidad Autor en un AutorDto.
     *
     * @param autor La entidad Autor que se va a transformar.
     */
    public AutorDto(Autor autor) {
        this(
                autor.getNombre(), // Obtiene el nombre del autor.
                autor.getAnhioDeNacimiento(), // Obtiene el año de nacimiento del autor.
                autor.getAnhioDeFallecimiento(), // Obtiene el año de fallecimiento (puede ser nulo).
                autor.getLibros() // Obtiene la lista de libros escritos por el autor.
                        .stream() // Convierte la lista en un flujo (stream) para procesar sus elementos.
                        .map(Libro::getTitulo) // Transforma cada libro en su título.
                        .collect(Collectors.joining(", ")) // Une los títulos con comas como separador.
        );
    }

    /**
     * Método que genera una representación en texto del autor.
     *
     * @return Una cadena formateada con los datos del autor.
     */
    @Override
    public String toString() {
        return """
                Autor: %s
                Fecha de nacimiento: %d
                Fecha de fallecimiento: %d
                Libros: [%s]
                """.formatted(nombre, anhioDeNacimiento, anhioDeFallecimiento, librosEscritos);
    }
}
