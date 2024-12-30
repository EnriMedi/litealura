package com.alura.literalura.servicio.provedor;

/**
 * Interfaz para convertir datos JSON a objetos Java.
 * Esta interfaz define el contrato que debe seguir cualquier clase que implemente la conversión de JSON a objetos.
 */
public interface IConvierteDatos {

    /**
     * Convierte un String JSON a un objeto Java de la clase especificada.
     *
     * @param json El String JSON que se desea convertir.
     * @param clase La clase del objeto al que se desea convertir el JSON.
     * @param <T> El tipo de clase que se desea obtener.
     * @return El objeto convertido desde el JSON.
     */
    <T> T obtenerDatos(String json, Class<T> clase);
}
