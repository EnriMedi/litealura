package com.alura.literalura.repositorio;

import com.alura.literalura.modelo.entidad.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para la entidad Autor.
 * Extiende JpaRepository, lo que permite realizar operaciones CRUD automáticamente.
 * Además, incluye consultas personalizadas utilizando JPQL (Java Persistence Query Language).
 */
public interface AutorRepository extends JpaRepository<Autor, Long> {

    /**
     * Obtiene una lista de autores que estuvieron vivos en un año específico.
     * La consulta selecciona autores cuyo año de nacimiento es menor o igual al año proporcionado
     * y cuyo año de fallecimiento es mayor al año proporcionado.
     *
     * @param anio El año específico en el que los autores deben haber estado vivos.
     * @return Lista de autores que estuvieron vivos en el año indicado.
     */
    @Query("SELECT a FROM Autor a WHERE a.anhioDeNacimiento <= :anio AND a.anhioDeFallecimiento > :anio")
    List<Autor> obtenerAutoresVivosPorAnio(int anio);
}
