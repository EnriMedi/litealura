package com.alura.literalura.servicio;

import com.alura.literalura.dominio.AutorDto;
import com.alura.literalura.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los autores.
 * Proporciona métodos para obtener autores desde la base de datos y convertirlos en DTOs.
 */
@Service
public class AutorService {

    // Inyección de dependencias para el repositorio de autores
    @Autowired
    private AutorRepository repository;

    /**
     * Obtiene todos los autores desde el repositorio y los convierte en objetos DTO.
     *
     * @return Una lista de AutorDto con la información de los autores.
     */
    public List<AutorDto> obtenerAutores() {
        // Llamada al repositorio para obtener todos los autores y mapearlos a DTOs
        return repository.findAll().stream()
                .map(autor -> new AutorDto(autor))  // Convierte cada Autor en un AutorDto
                .toList();  // Recoge los resultados en una lista
    }

    /**
     * Obtiene los autores que estuvieron vivos en un año específico.
     *
     * @param anio El año en el que se desea saber qué autores estaban vivos.
     * @return Una lista de AutorDto con los autores vivos en el año proporcionado.
     */
    public List<AutorDto> obtenerAutoresVivosPorAnio(int anio) {
        // Llamada al repositorio para obtener los autores vivos en el año especificado
        return repository.obtenerAutoresVivosPorAnio(anio).stream()
                .map(autor -> new AutorDto(autor))  // Convierte cada Autor en un AutorDto
                .toList();  // Recoge los resultados en una lista
    }
}
