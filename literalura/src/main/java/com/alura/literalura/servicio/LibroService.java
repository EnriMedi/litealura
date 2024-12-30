package com.alura.literalura.servicio;

import com.alura.literalura.dominio.LibroDto;
import com.alura.literalura.modelo.DatosLibro;
import com.alura.literalura.modelo.entidad.Idioma;
import com.alura.literalura.modelo.entidad.Libro;
import com.alura.literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los libros.
 * Proporciona métodos para guardar, consultar y filtrar libros en la base de datos.
 */
@Service
public class LibroService {

    // Inyección de dependencias para el repositorio de libros
    @Autowired
    private LibroRepository repository;

    /**
     * Guarda un nuevo libro en la base de datos si no existe previamente.
     *
     * @param datos Objeto de tipo DatosLibro con la información del libro a guardar.
     * @return Un objeto LibroDto con los datos del libro guardado.
     */
    public LibroDto guardarLibro(DatosLibro datos) {
        // Verifica si el libro ya existe en la base de datos por su título
        Optional<Libro> nuevo = repository.findByTitulo(datos.titulo());

        if (nuevo.isEmpty()) { // Si no existe, lo guarda
            Libro libro = repository.save(new Libro(datos));
            return new LibroDto(libro);
        } else { // Si ya existe, muestra un mensaje de aviso
            System.out.println("El libro ya existe en la base de datos");
        }

        // Retorna el DTO del libro existente
        return new LibroDto(nuevo.get());
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id Identificador único del libro.
     * @return Un objeto LibroDto con los datos del libro si se encuentra, o null si no existe.
     */
    public LibroDto obtenerLibroPorId(Long id) {
        // Busca el libro por ID y lo convierte a DTO si está presente
        return repository.findById(id).map(LibroDto::new).orElse(null);
    }

    /**
     * Obtiene todos los libros almacenados en la base de datos.
     *
     * @return Una lista de objetos LibroDto con los datos de los libros.
     */
    public List<LibroDto> obtenerLibros() {
        // Obtiene todos los libros y los convierte a DTOs
        return repository.findAll().stream()
                .map(LibroDto::new)
                .toList();
    }

    /**
     * Obtiene libros filtrados por idioma.
     *
     * @param idioma Objeto Idioma para filtrar los libros.
     * @return Una lista de objetos LibroDto con los libros que coinciden con el idioma especificado.
     */
    public List<LibroDto> obtenerLibrosPorIdioma(Idioma idioma) {
        // Busca los libros por idioma y los convierte a DTOs
        return repository.obtenerLibrosPorIdioma(idioma).stream()
                .map(LibroDto::new)
                .toList();
    }
}
