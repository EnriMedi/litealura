package com.alura.literalura.servicio.provedor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase para convertir datos JSON a objetos Java utilizando Jackson.
 * Implementa el patrón Singleton para garantizar que solo haya una instancia de ConvierteDatos.
 */
public class ConvierteDatos implements IConvierteDatos {

    // Instancia única de la clase (patrón Singleton)
    private static ConvierteDatos instance;

    // Objeto ObjectMapper de Jackson para convertir JSON a objetos Java
    private ObjectMapper objectMapper = new ObjectMapper();

    // Constructor privado para evitar la creación de instancias fuera de esta clase
    private ConvierteDatos() {}

    /**
     * Obtiene la instancia única de ConvierteDatos (patrón Singleton).
     * Si la instancia aún no existe, la crea.
     *
     * @return La instancia única de ConvierteDatos.
     */
    public static synchronized ConvierteDatos getInstance() {
        if (instance == null) {
            instance = new ConvierteDatos();
        }
        return instance;
    }

    /**
     * Convierte un String JSON a un objeto Java de la clase especificada.
     *
     * @param json El String JSON que se desea convertir.
     * @param clase La clase del objeto al que se desea convertir el JSON.
     * @param <T> El tipo de clase que se desea obtener.
     * @return El objeto convertido desde el JSON.
     * @throws RuntimeException Si ocurre un error durante la conversión del JSON.
     */
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            // Utiliza ObjectMapper para convertir el JSON a un objeto de la clase especificada
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            // Captura la excepción si hay un error en la conversión de JSON y lanza una RuntimeException
            throw new RuntimeException("Error al procesar el JSON: " + e.getMessage(), e);
        }
    }
}
