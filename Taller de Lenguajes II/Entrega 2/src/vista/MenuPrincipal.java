package vista;

import controlador.ContenidoController;
import controlador.UsuarioController;
import modelo.Contenido;

import java.util.List;
import java.util.Scanner;

/**
 * Clase de la vista principal de la aplicacion.
 * Administra el menu interactivo por consola y delega las operaciones a los controladores correspondientes.
 */
public class MenuPrincipal {

    // Declarados como atributos para evitar instanciaciones redundantes y simplificar el pasaje de parámetros 
    // entre los métodos de la vista
    private Scanner scanner = new Scanner(System.in);
    private UsuarioController usuarioController = new UsuarioController();
    private ContenidoController contenidoController = new ContenidoController();

    /**
     * Inicia el bucle principal del menu de la aplicacion.
     */
    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    publicarContenido();
                    break;
                case 3:
                    filtrarContenidoPorGenero();
                    break;
                case 4:
                    consultarMasReproducido();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo de la app...");
                    break;
                default:
                    System.out.println("Error: Opcion no valida. Intente nuevamente.");
            }
        }
    }

    /**
     * Muestra por consola las opciones del menu principal.
     */
    private void mostrarMenu() {
        System.out.println("\n--- Sistema de Streaming - MENU ---");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Publicar Contenido en el Catalogo");
        System.out.println("3. Filtrar Contenido por Genero");
        System.out.println("4. Consultar Contenido mas Reproducido");
        System.out.println("0. Salir");
    }

    /**
     * Gestiona el registro de un nuevo Usuario (Consumidor o Creador).
     */
    private void registrarUsuario() {
        System.out.println("\n--- Registro de Usuario ---");
        System.out.println("1. Usuario Consumidor");
        System.out.println("2. Usuario Creador");
        int tipo = leerEntero("Seleccione el tipo de usuario: ");

        String nombre = leerTextoSinNumeros("Ingrese nombre: ");
        String apellido = leerTextoSinNumeros("Ingrese apellido: ");
        String nombreUsuario = leerCadenaNoVacia("Ingrese nombre de usuario (username): ");
        String email = leerCadenaNoVacia("Ingrese email: ");

        try {
            if (tipo == 1) {
                usuarioController.registrarConsumidor(nombre, apellido, nombreUsuario, email);
                System.out.println("Usuario consumidor registrado con exito.");
            } else if (tipo == 2) {
                usuarioController.registrarCreador(nombre, apellido, nombreUsuario, email);
                System.out.println("Usuario creador registrado con exito.");
            } else {
                System.out.println("Error: Tipo de usuario invalido.");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Gestiona el flujo interactivo para publicar Canciones, Podcasts u Audiolibros
     */
    private void publicarContenido() { 
        System.out.println("\n--- Publicar Contenido en el Catálogo ---"); 
        System.out.println("1. Publicar Canción / Música"); 
        System.out.println("2. Publicar Episodio de Podcast"); 
        System.out.println("3. Publicar Audiolibro"); 
        int tipo = leerEntero("Seleccione tipo de contenido: ");

        if (tipo < 1 || tipo > 3) { 
            System.out.println("Error: Tipo de contenido inválido."); 
            return; 
        } 
        String usernameCreador = leerCadenaNoVacia("Ingrese nombre de usuario creador (username): "); 
        String titulo = leerCadenaNoVacia("Ingrese título del contenido: "); 
        String genero = leerCadenaNoVacia("Ingrese género (ej: ROCK, POP, HISTORIA): "); 
        try { 
            contenidoController.publicar(tipo, usernameCreador, titulo, genero); 
            System.out.println("¡Contenido publicado con éxito!"); 
        } catch (Exception e) { 
            System.out.println("Error al publicar contenido: " + e.getMessage()); 
        } 
    }

    /**
     * Muestra el catalogo de contenidos filtrado por un genero especifico.
     */
    private void filtrarContenidoPorGenero() {
        System.out.println("\n--- Filtrar Contenido por Genero ---");
        String genero = leerCadenaNoVacia("Ingrese el genero a consultar: ");

        try {
            List<Contenido> resultados = contenidoController.listarPorGenero(genero);
            if (resultados == null || resultados.isEmpty()) {
                System.out.println("No se encontraron contenidos para el genero: " + genero);
            } else {
                System.out.println("Contenidos encontrados:");
                for (Contenido c : resultados) {
                    System.out.println("- " + c.getNombre() + " (" + c.getGenero() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar contenidos: " + e.getMessage());
        }
    }

    /**
     * Muestra la informacion del contenido con mayor cantidad de reproducciones acumuladas.
     */
    private void consultarMasReproducido() {
        System.out.println("\n--- Contenido Mas Reproducido ---");

        try {
            Contenido masVisto = contenidoController.obtenerMasReproducido();
            if (masVisto != null) {
                System.out.println("Contenido mas reproducido: " + masVisto.getNombre() + " (" + masVisto.getGenero() + ")");
            } else {
                System.out.println("El catalogo se encuentra vacio.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el contenido mas reproducido: " + e.getMessage());
        }
    }

    /**
     * Valida y lee una cadena de texto asegurando que no contenga digitos numericos ni este vacia.
     * 
     * @param mensaje Mensaje a mostrar al usuario solicitando la entrada.
     * @return Cadena de texto validada sin numeros.
     */
    private String leerTextoSinNumeros(String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Error: El campo no puede estar vacio.");
            } else if (texto.matches(".*\\d.*")) {
                System.out.println("Error: No se permite el ingreso de numeros en nombres y apellidos.");
            } else {
                return texto;
            }
        }
    }

    /**
     * Valida y lee una cadena de texto asegurando que no este vacia.
     * 
     * @param mensaje Mensaje a mostrar al usuario solicitando la entrada.
     * @return Cadena de texto no vacia.
     */
    private String leerCadenaNoVacia(String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Error: Todos los datos son obligatorios y no pueden estar vacios.");
            } else {
                return texto;
            }
        }
    }

    /**
     * Valida y lee un numero entero ingresado por consola.
     * 
     * @param mensaje Mensaje a mostrar al usuario solicitando el numero.
     * @return Valor entero ingresado.
     */
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero valido.");
            }
        }
    }
}
