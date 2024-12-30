package com.alura.literalura;

import com.alura.literalura.interfaz.AppConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación "Literatura".
 * Configura la aplicación Spring Boot y ejecuta las operaciones de la consola.
 */
@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	// Inyección de dependencias para la interfaz de consola
	@Autowired
	private AppConsole app;

	/**
	 * Punto de entrada de la aplicación.
	 *
	 * @param args Argumentos pasados desde la línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	/**
	 * Método que se ejecuta automáticamente después de iniciar la aplicación.
	 * Llama al método para ejecutar las operaciones principales de la aplicación.
	 *
	 * @param args Argumentos pasados desde la línea de comandos.
	 * @throws Exception Si ocurre algún error durante la ejecución.
	 */
	@Override
	public void run(String... args) throws Exception {
		app.ejecutarOperaciones();
	}
}
