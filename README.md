Características Principales

Gestión de Libros:

Registro de libros provenientes de la API Gutendex.

Listado de todos los libros almacenados.

Búsqueda de libros por título.

Listado de libros filtrados por idioma.

Gestión de Autores:

Listado de todos los autores registrados.

Consulta de autores vivos en un año específico.

Consumo de API:

Integración con la API externa Gutendex para obtener datos de libros y autores en formato JSON.

Persistencia:

Uso de JPA para la gestión de datos en una base de datos relacional.

Verificación de duplicados antes de almacenar nuevos registros.

Interfaz de Usuario:

Menú interactivo en consola para ejecutar las operaciones disponibles.

Tecnologías Utilizadas

Java 17

Spring Boot 3.x

JPA/Hibernate

H2 Database (Base de datos embebida para desarrollo)

Gutendex API (Fuente de datos externa)

Jackson (Para procesamiento de JSON)

Maven (Gestor de dependencias)

Estructura del Proyecto

com.alura.literalura.modelo:

Contiene las clases que representan los datos obtenidos de la API (Datos, DatosAutor, DatosLibro) y las entidades de base de datos (Autor, Libro, Idioma).

com.alura.literalura.dominio:

Incluye los DTOs (AutorDto, LibroDto) para encapsular y exponer los datos en la capa de servicios.

com.alura.literalura.repositorio:

Contiene los repositorios JPA (AutorRepository, LibroRepository) para manejar la persistencia.

com.alura.literalura.servicio:

Proporciona los servicios (AutorService, LibroService) que implementan la lógica de negocio.

com.alura.literalura.servicio.provedor:

Clases para el consumo de la API externa (ConsumoAPI, ConvierteDatos).

com.alura.literalura.interfaz:

Define la lógica de la interfaz de usuario en consola (AppConsole).

Clase Principal:

LiteraturaApplication inicializa la aplicación y ejecuta el menú principal.

Requisitos Previos

Java 17 o superior.

Maven configurado en el sistema.

Uso

Una vez ejecutada la aplicación, se mostrará un menú interactivo en consola con las siguientes opciones:

Buscar un libro por título.

Listar todos los libros.

Listar libros por idioma.

Listar todos los autores.

Consultar autores vivos en un año específico.

El usuario debe ingresar el número correspondiente a la opción deseada.

Notas Adicionales

El proyecto utiliza una base de datos H2 embebida para pruebas y desarrollo. Los datos se reinician en cada ejecución.

El sistema evita duplicados verificando la existencia de libros por título antes de registrarlos.

Los datos de la API se obtienen en tiempo real mediante peticiones HTTP.

Futuras Mejoras

Implementar una interfaz gráfica para facilitar el uso.

Integrar autenticación y autorización para proteger los datos.

Migrar la base de datos a un sistema de producción como MySQL o PostgreSQL.

Permitir operaciones CRUD completas para libros y autores desde la interfaz.

Agregar manejo de errores más robusto para peticiones fallidas a la API externa.
