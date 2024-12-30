package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa los datos de un autor obtenidos de una fuente externa, como una API.
 * Incluye el nombre del autor, el año de nacimiento y el año de fallecimiento.
 *
 * Esta clase utiliza anotaciones para manejar la serialización y deserialización
 * de JSON, mapeando propiedades específicas de la estructura JSON a sus equivalentes
 * en Java.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora cualquier propiedad desconocida al deserializar JSON.
public record DatosAutor(
        @JsonAlias("name") String nombre, // Mapea la propiedad JSON "name" al campo "nombre".
        @JsonAlias("birth_year") Integer anhioDeNacimiento, // Mapea la propiedad JSON "birth_year" al campo "anhioDeNacimiento".
        @JsonAlias("death_year") Integer anhioDeFallecimiento // Mapea la propiedad JSON "death_year" al campo "anhioDeFallecimiento".
) {}
