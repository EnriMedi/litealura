package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Representa los datos obtenidos de una fuente externa, como una API.
 * Incluye la cantidad de resultados, una lista de libros y un enlace
 * a la página siguiente de resultados (si aplica).
 *
 * Esta clase utiliza anotaciones para manejar la serialización y deserialización
 * de JSON, con propiedades específicas para adaptarse a la estructura recibida.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propiedades desconocidas al deserializar JSON.
public record Datos(
        @JsonAlias("count") Integer cantidad, // Cantidad total de resultados (alias para "count" en JSON).
        @JsonAlias("results") List<DatosLibro> libros, // Lista de libros obtenidos (alias para "results").
        @JsonAlias("next") String siguiente // Enlace a la página siguiente de resultados (alias para "next").
) {}
