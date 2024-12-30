package com.alura.literalura.interfaz;

import com.alura.literalura.dominio.AutorDto;
import com.alura.literalura.dominio.LibroDto;
import com.alura.literalura.modelo.Datos;
import com.alura.literalura.modelo.DatosLibro;
import com.alura.literalura.modelo.entidad.Idioma;
import com.alura.literalura.servicio.AutorService;
import com.alura.literalura.servicio.provedor.ConsumoAPI;
import com.alura.literalura.servicio.provedor.ConvierteDatos;
import com.alura.literalura.servicio.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para la interacción del usuario en la consola.
 * Proporciona un menú con opciones para interactuar con libros y autores.
 */
@Component
public class AppConsole {

    private static final String URL_BASE = "https://gutendex.com/books/"; // URL base de la API de libros.

    @Autowired
    private LibroService libroService; // Servicio para gestionar libros.
    @Autowired
    private AutorService autorService; // Servicio para gestionar autores.

    private final Scanner teclado = new Scanner(System.in); // Objeto Scanner para capturar entrada del usuario.
    private final ConsumoAPI consumoAPI = ConsumoAPI.getInstance(); // Singleton para consumo de API.
    private final ConvierteDatos conversor = ConvierteDatos.getInstance(); // Singleton para conversión de datos.

    /**
     * Método principal que ejecuta el flujo de operaciones del programa.
     * Muestra un menú y gestiona las acciones del usuario.
     */
    public void ejecutarOperaciones() {
        int operacion = -1;
        while (operacion != 0) {
            mostrarMenu(); // Muestra el menú de opciones.
            operacion = obtenerEntradaNumerica("Elija la opción a través de su número: ");
            if (operacion == -1) continue; // En caso de error, vuelve al menú.

            // Gestiona la opción seleccionada por el usuario.
            switch (operacion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAnio();
                case 5 -> {
                    mostrarIdiomas(); // Muestra los idiomas disponibles.
                    listarLibrosPorIdioma();
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Muestra el menú de opciones al usuario.
     */
    private void mostrarMenu() {
        System.out.println("""
                Menu de opciones:
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                """);
    }

    /**
     * Solicita un número al usuario con un mensaje personalizado.
     * Maneja errores de entrada no numérica.
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return El número ingresado o -1 si ocurre un error.
     */
    private int obtenerEntradaNumerica(String mensaje) {
        System.out.print(mensaje);
        try {
            return teclado.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ingreso inválido, por favor ingrese un número.");
            return -1;
        } finally {
            teclado.nextLine(); // Limpia el buffer de entrada.
        }
    }

    /**
     * Muestra los idiomas disponibles para la búsqueda.
     */
    public void mostrarIdiomas() {
        for (Idioma idioma : Idioma.values()) {
            System.out.println(idioma.mostrar());
        }
    }

    /**
     * Busca un libro por su título usando la API y guarda el resultado.
     */
    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        String titulo = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(crearUrlBusqueda(titulo)); // Realiza la solicitud a la API.
        DatosLibro libroBuscado = conversor.obtenerDatos(json, Datos.class).libros().get(0); // Convierte los datos JSON a un objeto.
        LibroDto libroNuevo = libroService.guardarLibro(libroBuscado); // Guarda el libro en el sistema.
        System.out.println(libroNuevo); // Muestra el libro encontrado.
    }

    /**
     * Crea la URL para buscar un libro por título en la API.
     *
     * @param titulo El título del libro.
     * @return La URL con el formato adecuado.
     */
    private String crearUrlBusqueda(String titulo) {
        return URL_BASE + "?search=" + titulo.replace(" ", "+");
    }

    /**
     * Lista todos los libros registrados en el sistema.
     */
    private void listarLibrosRegistrados() {
        listarElementos("Libros", libroService.obtenerLibros());
    }

    /**
     * Lista todos los autores registrados en el sistema.
     */
    private void listarAutoresRegistrados() {
        listarElementos("Autores", autorService.obtenerAutores());
    }

    /**
     * Lista elementos de un tipo dado (libros o autores).
     *
     * @param tipo      El tipo de elementos a listar (por ejemplo, "Libros" o "Autores").
     * @param elementos La lista de elementos a mostrar.
     */
    private void listarElementos(String tipo, List<?> elementos) {
        if (elementos.isEmpty()) {
            System.out.println("No se encontraron " + tipo.toLowerCase());
        } else {
            elementos.forEach(System.out::println);
        }
    }

    /**
     * Lista autores vivos en un año determinado ingresado por el usuario.
     */
    private void listarAutoresVivosPorAnio() {
        int anio = obtenerEntradaNumerica("Ingrese el año de los autor(es) vivos que desea buscar: ");
        if (anio != -1) {
            List<AutorDto> autoresVivos = autorService.obtenerAutoresVivosPorAnio(anio);
            autoresVivos.forEach(System.out::println);
        }
    }

    /**
     * Lista libros registrados en un idioma específico.
     */
    private void listarLibrosPorIdioma() {
        System.out.println("Escriba el idioma deseado: ");
        String abreviatura = teclado.nextLine();
        Idioma idioma = Idioma.fromString(abreviatura); // Convierte la entrada del usuario al enum Idioma.
        if (idioma != null) {
            List<LibroDto> librosPorIdioma = libroService.obtenerLibrosPorIdioma(idioma);
            librosPorIdioma.forEach(System.out::println);
        } else {
            System.out.println("Idioma no válido.");
        }
    }
}
