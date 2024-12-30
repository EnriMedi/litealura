package com.alura.literalura.modelo.entidad;

/**
 * Representa un conjunto de idiomas disponibles en el sistema.
 * Cada idioma tiene una abreviatura y un nombre descriptivo.
 */
public enum Idioma {
    INGLES("en", "Inglés"),       // Idioma inglés con su abreviatura "en"
    ESPANIOL("es", "Español"),   // Idioma español con su abreviatura "es"
    FRANCES("fr", "Francés"),    // Idioma francés con su abreviatura "fr"
    PORTUGUES("pt", "Portugués"); // Idioma portugués con su abreviatura "pt"

    /**
     * Abreviatura del idioma (código ISO 639-1).
     */
    private String abreviatura;

    /**
     * Nombre del idioma en su forma completa.
     */
    private String nombre;

    /**
     * Constructor privado para inicializar los valores del enum.
     *
     * @param abreviatura Código abreviado del idioma.
     * @param nombre Nombre descriptivo del idioma.
     */
    Idioma(String abreviatura, String nombre) {
        this.abreviatura = abreviatura;
        this.nombre = nombre;
    }

    /**
     * Busca un idioma por su abreviatura (insensible a mayúsculas/minúsculas).
     *
     * @param text Abreviatura del idioma.
     * @return Instancia del enum que coincide con la abreviatura.
     * @throws IllegalArgumentException Si no se encuentra ningún idioma con esa abreviatura.
     */
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) { // Itera por todos los valores del enum.
            if (idioma.abreviatura.equalsIgnoreCase(text)) { // Compara ignorando mayúsculas/minúsculas.
                return idioma;
            }
        }
        // Lanza una excepción si no se encuentra un idioma correspondiente.
        throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
    }

    /**
     * Obtiene el nombre descriptivo del idioma.
     *
     * @return Nombre completo del idioma.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve una representación legible del idioma.
     * Incluye la abreviatura y el nombre descriptivo.
     *
     * @return Una cadena en el formato "abreviatura - nombre".
     */
    public String mostrar() {
        return abreviatura + " - " + nombre;
    }

    /**
     * Devuelve la abreviatura del idioma como representación en texto.
     *
     * @return Abreviatura del idioma.
     */
    @Override
    public String toString() {
        return abreviatura;
    }
}
