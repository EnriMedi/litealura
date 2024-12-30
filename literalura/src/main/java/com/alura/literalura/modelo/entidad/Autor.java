package com.alura.literalura.modelo.entidad;

import com.alura.literalura.modelo.DatosAutor;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representa la entidad Autor en el sistema.
 * Utiliza JPA para mapear la clase con una tabla de base de datos.
 */
@Entity
@Table(name = "autores")
public class Autor {

    /**
     * Identificador único del autor. Es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del autor.
     */
    private String nombre;

    /**
     * Año de nacimiento del autor.
     */
    private Integer anhioDeNacimiento;

    /**
     * Año de fallecimiento del autor. Si es null, el autor sigue vivo.
     */
    private Integer anhioDeFallecimiento;

    /**
     * Relación muchos a muchos con la entidad Libro.
     * Representa los libros escritos por el autor.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libros_autores", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "autor_id"), // Columna que referencia a Autor
            inverseJoinColumns = @JoinColumn(name = "libro_id") // Columna que referencia a Libro
    )
    private Set<Libro> libros;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Autor() {
    }

    /**
     * Constructor que permite crear un Autor a partir de un objeto DatosAutor.
     * @param autor Objeto que contiene los datos del autor.
     */
    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.anhioDeNacimiento = autor.anhioDeNacimiento();
        this.anhioDeFallecimiento = autor.anhioDeFallecimiento();
    }

    // Métodos getter y setter para acceder y modificar las propiedades del autor.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnhioDeNacimiento() {
        return anhioDeNacimiento;
    }

    public void setAnhioDeNacimiento(Integer anhioDeNacimiento) {
        this.anhioDeNacimiento = anhioDeNacimiento;
    }

    public Integer getAnhioDeFallecimiento() {
        return anhioDeFallecimiento;
    }

    public void setAnhioDeFallecimiento(Integer anhioDeFallecimiento) {
        this.anhioDeFallecimiento = anhioDeFallecimiento;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Representación en forma de texto del autor.
     * Incluye su nombre, fechas de nacimiento y fallecimiento, y los libros escritos.
     * @return Cadena con los detalles del autor.
     */
    @Override
    public String toString() {
        // Obtiene los títulos de los libros escritos por el autor y los concatena en una sola cadena.
        String librosEscritos = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));

        return """
                Autor: %s
                Fecha de nacimiento: %d
                Fecha de fallecimiento: %d
                Libros: [%s]
                """.formatted(nombre, anhioDeNacimiento, anhioDeFallecimiento, librosEscritos);
    }
}
