package clases;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para la ejecución interactiva por teclado de la Plataforma de Streaming de Audio (TFI - Entrega 1).
 * Permite ingresar usuarios, publicar contenidos, crear playlists y consultar la información por consola usando Scanner.
 */
public class ProgramaPrincipal {

    private static Scanner scanner = new Scanner(System.in);
    private static PlataformaStreaming plataforma = new PlataformaStreaming("SoundStream UNLP");

    // Planes predefinidos para asignar
    private static Plan planGratis = new PlanGratuito();
    private static Plan planPremium = new PlanPago("Premium Individual", 1500.0);
    private static Plan planFamiliar = new PlanPago("Premium Familiar", 2800.0);

    public static void main(String[] args) {
        System.out.println("   TALLER DE LENGUAJES II - TRABAJO FINAL INTEGRADOR (ENTREGA 1)   ");
        System.out.println("   PLATAFORMA DE STREAMING DE AUDIO - INGRESO POR TECLADO          ");
        System.out.println("---------------------------------------------------------------------\n");

        int opcion = -1;

        while (opcion != 0) {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarUsuarioInteractivo();
                    break;
                case 2:
                    publicarContenidoInteractivo();
                    break;
                case 3:
                    crearPlaylistInteractivo();
                    break;
                case 4:
                    plataforma.mostrarCatalogo();
                    break;
                case 5:
                    plataforma.mostrarUsuarios();
                    break;
                case 6:
                    mostrarPlaylistsConsumidores();
                    break;
                case 7:
                    cargarDatosDePrueba();
                    break;
                case 0:
                    System.out.println("\n¡Gracias por utilizar SoundStream UNLP! Hasta luego.");
                    break;
                default:
                    System.out.println(" Opción no válida. Intente nuevamente.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n");
        System.out.println("                      MENÚ PRINCIPAL                            ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("1. Registrar nuevo Usuario (Consumidor o Creador)");
        System.out.println("2. Publicar nuevo Contenido al Catálogo (Canción, Podcast, Audiolibro)");
        System.out.println("3. Crear Playlist e incorporar contenido para un Consumidor");
        System.out.println("4. Mostrar Catálogo General");
        System.out.println("5. Mostrar Usuarios Registrados");
        System.out.println("6. Mostrar Playlists de los Consumidores");
        System.out.println("7. Cargar datos de prueba predefinidos");
        System.out.println("0. Salir");
        System.out.println("-----------------------------------------------------------------");
    }

    // --- 1. REGISTRO DE USUARIOS ---
    private static void registrarUsuarioInteractivo() {
        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");
        System.out.println("1. Usuario Consumidor (Oyente)");
        System.out.println("2. Usuario Creador (Artista/Podcaster)");
        int tipo = leerEntero("Seleccione el tipo de usuario: ");

        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese correo electrónico: ");
        String email = scanner.nextLine().trim();

        if (nombre.isBlank() || email.isBlank()) {
            System.out.println(" Error: El nombre y el email no pueden estar vacíos.");
            return;
        }

        System.out.println("Seleccione el Plan de Suscripción:");
        System.out.println("1. Plan Gratuito (con anuncios)");
        System.out.println("2. Plan Premium Individual ($1500/mes)");
        System.out.println("3. Plan Premium Familiar ($2800/mes)");
        int opPlan = leerEntero("Opción de plan: ");

        Plan planElegido = planGratis;
        if (opPlan == 2) planElegido = planPremium;
        else if (opPlan == 3) planElegido = planFamiliar;

        if (tipo == 1) {
            UsuarioConsumidor u = new UsuarioConsumidor(nombre, email);
            u.setPlan(planElegido);
            plataforma.agregarUsuario(u);
        } else if (tipo == 2) {
            UsuarioCreador c = new UsuarioCreador(nombre, email);
            c.setPlan(planElegido);
            plataforma.agregarUsuario(c);
        } else {
            System.out.println(" Tipo de usuario no válido.");
        }
    }

    // --- 2. PUBLICACIÓN DE CONTENIDOS ---
    private static void publicarContenidoInteractivo() {
        System.out.println("\n--- PUBLICAR NUEVO CONTENIDO ---");

        UsuarioCreador creador = seleccionarCreador();
        if (creador == null) {
            System.out.println(" Debe registrar al menos un Usuario Creador antes de publicar contenido.");
            return;
        }

        System.out.println("Tipo de contenido:");
        System.out.println("1. Canción");
        System.out.println("2. Episodio de Podcast");
        System.out.println("3. Audiolibro");
        int tipo = leerEntero("Seleccione el tipo: ");

        System.out.print("Título/Nombre: ");
        String titulo = scanner.nextLine().trim();

        int duracion = leerEntero("Duración en segundos: ");

        Genero genero = seleccionarGenero();

        if (tipo == 1) { // Canción
            System.out.print("Letra de la canción: ");
            String letra = scanner.nextLine().trim();
            System.out.print("Artista invitado / Feat (Presione ENTER si no hay): ");
            String invitado = scanner.nextLine().trim();
            if (invitado.isBlank()) invitado = null;

            System.out.print("¿Es un sencillo/single? (s/n): ");
            boolean esSingle = scanner.nextLine().trim().equalsIgnoreCase("s");

            Contenido cancion = new Cancion(titulo, duracion, genero, creador, letra, invitado, esSingle);
            creador.publicarContenido(cancion, plataforma);
            System.out.println(" Canción publicada exitosamente en el catálogo.");

        } else if (tipo == 2) { // Episodio Podcast
            System.out.print("Resumen del episodio: ");
            String resumen = scanner.nextLine().trim();
            System.out.print("Invitado especial (Presione ENTER si no hay): ");
            String invitado = scanner.nextLine().trim();
            if (invitado.isBlank()) invitado = null;

            int nroEpisodio = leerEntero("Número de episodio: ");

            Contenido episodio = new EpisodioPodcast(titulo, duracion, genero, creador, resumen, invitado, nroEpisodio);
            creador.publicarContenido(episodio, plataforma);
            System.out.println(" Episodio de podcast publicado exitosamente en el catálogo.");

        } else if (tipo == 3) { // Audiolibro
            System.out.print("Nombre del narrador: ");
            String narrador = scanner.nextLine().trim();
            System.out.print("Sinopsis de la obra: ");
            String sinopsis = scanner.nextLine().trim();
            int capitulos = leerEntero("Cantidad de capítulos: ");

            Contenido audiolibro = new Audiolibro(titulo, duracion, genero, creador, narrador, sinopsis, capitulos);
            creador.publicarContenido(audiolibro, plataforma);
            System.out.println(" Audiolibro publicado exitosamente en el catálogo.");

        } else {
            System.out.println(" Tipo de contenido inválido.");
        }
    }

    // --- 3. CREAR PLAYLIST ---
    private static void crearPlaylistInteractivo() {
        System.out.println("\n--- CREAR PLAYLIST DE CONSUMIDOR ---");
        UsuarioConsumidor consumidor = seleccionarConsumidor();
        if (consumidor == null) {
            System.out.println(" No hay usuarios consumidores registrados.");
            return;
        }

        System.out.print("Ingrese el nombre de la nueva playlist: ");
        String nombrePl = scanner.nextLine().trim();
        if (nombrePl.isBlank()) {
            System.out.println(" El nombre de la playlist no puede estar vacío.");
            return;
        }

        Playlist playlist = consumidor.crearPlaylist(nombrePl);
        System.out.println(" Playlist '" + playlist.getNombre() + "' creada.");

        System.out.print("¿Desea agregar contenidos a la playlist ahora? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            boolean agregarMas = true;
            while (agregarMas) {
                System.out.print("Ingrese el nombre exacto o parcial del contenido a agregar: ");
                String busqueda = scanner.nextLine().trim();

                List<Contenido> resultados = plataforma.getCatalogo().buscarContenido(busqueda);
                if (resultados.isEmpty()) {
                    System.out.println(" No se encontraron contenidos con esa búsqueda.");
                } else {
                    System.out.println("Resultados encontrados:");
                    for (int i = 0; i < resultados.size(); i++) {
                        System.out.println((i + 1) + ". " + resultados.get(i).getNombre() + " (" + resultados.get(i).getGenero() + ")");
                    }
                    int seleccion = leerEntero("Seleccione el número de contenido a agregar (0 para cancelar): ");
                    if (seleccion > 0 && seleccion <= resultados.size()) {
                        Contenido c = resultados.get(seleccion - 1);
                        consumidor.agregarAPlaylist(playlist, c);
                        System.out.println(" Se agregó '" + c.getNombre() + "' a la playlist.");
                    }
                }

                System.out.print("¿Agregar otro contenido a esta playlist? (s/n): ");
                agregarMas = scanner.nextLine().trim().equalsIgnoreCase("s");
            }
        }
    }

    // --- MÉTODOS AUXILIARES ---
    private static UsuarioCreador seleccionarCreador() {
        List<UsuarioCreador> creadores = new java.util.ArrayList<>();
        for (UsuarioConsumidor u : plataforma.getUsuarios()) {
            if (u instanceof UsuarioCreador) {
                creadores.add((UsuarioCreador) u);
            }
        }
        if (creadores.isEmpty()) return null;

        System.out.println("Creadores disponibles:");
        for (int i = 0; i < creadores.size(); i++) {
            System.out.println((i + 1) + ". " + creadores.get(i).getNombreUsuario());
        }
        int sel = leerEntero("Seleccione creador (1-" + creadores.size() + "): ");
        if (sel >= 1 && sel <= creadores.size()) {
            return creadores.get(sel - 1);
        }
        return null;
    }

    private static UsuarioConsumidor seleccionarConsumidor() {
        List<UsuarioConsumidor> lista = plataforma.getUsuarios();
        if (lista.isEmpty()) return null;

        System.out.println("Usuarios consumidores disponibles:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNombreUsuario() + " (" + lista.get(i).getPlan().getNombre() + ")");
        }
        int sel = leerEntero("Seleccione usuario (1-" + lista.size() + "): ");
        if (sel >= 1 && sel <= lista.size()) {
            return lista.get(sel - 1);
        }
        return null;
    }

    private static Genero seleccionarGenero() {
        System.out.println("Géneros disponibles:");
        Genero[] generos = Genero.values();
        for (int i = 0; i < generos.length; i++) {
            System.out.println((i + 1) + ". " + generos[i]);
        }
        int sel = leerEntero("Seleccione género (1-" + generos.length + "): ");
        if (sel >= 1 && sel <= generos.length) {
            return generos[sel - 1];
        }
        return Genero.ROCK;
    }

    private static void mostrarPlaylistsConsumidores() {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("PLAYLISTS DE LOS USUARIOS CONSUMIDORES:");
        boolean hayListas = false;

        for (UsuarioConsumidor u : plataforma.getUsuarios()) {
            List<Playlist> listas = u.getBiblioteca().getPlaylists();
            if (!listas.isEmpty()) {
                hayListas = true;
                System.out.println("\nUsuario: " + u.getNombreUsuario() + " | Plan: " + u.getPlan().getNombre());
                for (Playlist p : listas) {
                    System.out.print(p.toString());
                }
            }
        }
        if (!hayListas) {
            System.out.println("No hay playlists creadas aún.");
        }
    }

    private static void cargarDatosDePrueba() {
        System.out.println("\n Datos iniciales de prueba...");

        UsuarioConsumidor u1 = new UsuarioConsumidor("Juliana_Moroni", "juli@gmail.com");
        u1.setPlan(planPremium);

        UsuarioConsumidor u2 = new UsuarioConsumidor("Olivia_Iriarte", "olivia@gmail.com");
        u2.setPlan(planGratis);

        UsuarioCreador creador1 = new UsuarioCreador("SodaStereo_Official", "contacto@sodastereo.com");
        creador1.setPlan(planFamiliar);

        UsuarioCreador creador2 = new UsuarioCreador("Luzu_Podcast", "podcast@luzu.io");
        creador2.setPlan(planPremium);

        UsuarioCreador creador3 = new UsuarioCreador("Editorial_AudioLibros", "info@audiolibros.org");
        creador3.setPlan(planGratis);

        plataforma.agregarUsuario(u1);
        plataforma.agregarUsuario(u2);
        plataforma.agregarUsuario(creador1);
        plataforma.agregarUsuario(creador2);
        plataforma.agregarUsuario(creador3);

        Contenido c1 = new Cancion("De Música Ligera", 212, Genero.ROCK, creador1, "Ella durmió al calor de las masas...", null, false);
        Contenido c3 = new Cancion("Sebastian Villena", 201, Genero.CLASICOS, creador1, "Soy el mejor profe, no lo voy a negar YEAH...", null, true);

        Contenido c4 = new EpisodioPodcast("IA y el futuro del gurpo 4", 1850, Genero.CIENCIA, creador2, "Analizamos las nuevas características de Java y el impacto de la IA en alumnos que hacen bitecoding.", "Prof. Claudia Queiruga", 1);

        Contenido c6 = new Audiolibro("La importancia de los profes de ser buenos profes (Cap. 1)", 3600, Genero.HISTORIA, creador3, "Olivia Iriarte", "Obra cumbre de la literatura Argentina.", 1);

        creador1.publicarContenido(c1, plataforma);
        creador1.publicarContenido(c3, plataforma);
        creador2.publicarContenido(c4, plataforma);
        creador3.publicarContenido(c6, plataforma);

        Playlist pl1 = u1.crearPlaylist("Rock Nacional");
        u1.agregarAPlaylist(pl1, c1);

        Playlist pl2 = u2.crearPlaylist("Favoritos de Oli");
        u2.agregarAPlaylist(pl2, c6);

        System.out.println(" Datos de prueba cargados correctamente en la plataforma.");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println(" Entrada inválida. Ingrese un número entero.");
            scanner.next();
            System.out.print(mensaje);
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer de salto de línea
        return num;
    }
}
