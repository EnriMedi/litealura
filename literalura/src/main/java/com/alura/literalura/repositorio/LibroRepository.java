package com.alura.literalura.repositorio;
import com.alura.literalura.modelo.entidad.Idioma;
import com.alura.literalura.modelo.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Libro.
 * Extiende JpaRepository, lo que permite realizar operaciones CRUD automáticamente.
 * Además, incluye consultas personalizadas utilizando JPQL (Java Persistence Query Language).
 */
public interface LibroRepository extends JpaRepository<Libro, Long> {

    /**
     * Obtiene una lista de libros que están en un idioma específico.
     * La consulta selecciona libros cuyo campo de idioma coincida con el idioma proporcionado.
     *
     * @param idioma El idioma por el cual se filtran los libros.
     * @return Lista de libros que están en el idioma especificado.
     */
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> obtenerLibrosPorIdioma(Idioma idioma);

    /**
     * Busca un libro por su título.
     * Utiliza el método estándar de Spring Data JPA para realizar la búsqueda de manera eficiente.
     *
     * @param titulo El título del libro a buscar.
     * @return Un {@link Optional} que contiene el libro si se encuentra, o vacío si no se encuentra.
     */
    Optional<Libro> findByTitulo(String titulo);
}
