package com.alura.literalura.servicio.provedor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Clase para el consumo de una API REST utilizando HTTP.
 * Implementa el patrón Singleton para garantizar que solo haya una instancia de ConsumoAPI.
 */
public class ConsumoAPI {

    // Instancia única de la clase (patrón Singleton)
    private static ConsumoAPI instance;

    // Constructor privado para evitar la creación de instancias fuera de esta clase
    private ConsumoAPI() {}

    /**
     * Obtiene la instancia única de ConsumoAPI (patrón Singleton).
     * Si la instancia aún no existe, la crea.
     *
     * @return La instancia única de ConsumoAPI.
     */
    public static synchronized ConsumoAPI getInstance() {
        if (instance == null) {
            instance = new ConsumoAPI();
        }
        return instance;
    }

    /**
     * Realiza una solicitud HTTP GET a la URL proporcionada y obtiene los datos en formato JSON.
     * Utiliza HttpClient para realizar la solicitud y HttpResponse para manejar la respuesta.
     *
     * @param url La URL de la API a la cual se realizará la solicitud.
     * @return El cuerpo de la respuesta en formato JSON.
     * @throws RuntimeException Si ocurre un error durante la solicitud HTTP.
     */
    public String obtenerDatos(String url) {
        // Crear un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Construir la solicitud HTTP GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) // Establecer la URI de la solicitud
                .build();

        HttpResponse<String> response;
        try {
            // Enviar la solicitud y obtener la respuesta
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Captura de excepciones por error de entrada/salida
            throw new RuntimeException("Error en la solicitud HTTP: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            // Captura de interrupción en el proceso de la solicitud
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
            throw new RuntimeException("La solicitud fue interrumpida: " + e.getMessage(), e);
        }

        // Obtener el cuerpo de la respuesta y retornarlo
        return response.body();
    }
}
