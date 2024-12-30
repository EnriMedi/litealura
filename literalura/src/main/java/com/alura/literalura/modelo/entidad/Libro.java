package com.alura.literalura.modelo.entidad;

import com.alura.literalura.modelo.DatosLibro;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representa un libro con sus propiedades, incluyendo su título, temas,
 * idioma, contador de descargas y autores relacionados.
 */
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del libro (autogenerado).

    @Column(unique = true)
    private String titulo; // Título único del libro.

    private String temas; // Temas relacionados con el libro, almacenados como una cadena separada por ";".

    @Enumerated(EnumType.STRING)
    private Idioma idioma; // Idioma del libro representado por el enum Idioma.

    private Integer contadorDeDescargas; // Número de veces que se ha descargado el libro.

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libros_autores", // Nombre de la tabla intermedia para la relación muchos a muchos.
            joinColumns = @JoinColumn(name = "libro_id"), // Clave foránea hacia el libro.
            inverseJoinColumns = @JoinColumn(name = "autor_id") // Clave foránea hacia el autor.
    )
    private Set<Autor> autores; // Conjunto de autores asociados al libro.

    /**
     * Constructor vacío necesario para JPA.
     */
    public Libro() {}

    /**
     * Constructor que inicializa un libro a partir de un objeto DatosLibro.
     *
     * @param libro Datos del libro proporcionados por el modelo.
     */
    public Libro(DatosLibro libro) {
        this.titulo = libro.titulo(); // Asigna el título.
        this.temas = libro.temas().stream()
                .collect(Collectors.joining(";")); // Convierte la lista de temas en una cadena separada por ";".

        // Obtiene el primer idioma de la lista y lo convierte al enum Idioma.
        this.idioma = Idioma.fromString(libro.idiomas().get(0));

        this.contadorDeDescargas = libro.contadorDeDescargas(); // Asigna el contador de descargas.

        // Convierte los datos de los autores en entidades Autor y los guarda en el conjunto.
        this.autores = libro.autores().stream()
                .map(da -> new Autor(da))
                .collect(Collectors.toSet());
    }

    // Métodos getter y setter para las propiedades del libro.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getContadorDeDescargas() {
        return contadorDeDescargas;
    }

    public void setContadorDeDescargas(Integer contadorDeDescargas) {
        this.contadorDeDescargas = contadorDeDescargas;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    /**
     * Devuelve una representación en texto del libro, incluyendo su título,
     * autores, idioma y número de descargas.
     *
     * @return Una cadena formateada con la información del libro.
     */
    @Override
    public String toString() {
        // Crea una lista de nombres de los autores separados por comas.
        String listaAutores = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));

        // Devuelve la representación formateada del libro.
        return """
                --------- LIBRO ---------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %d
                -------------------------
                """.formatted(
                titulo,
                listaAutores,
                idioma,
                contadorDeDescargas);
    }
}
