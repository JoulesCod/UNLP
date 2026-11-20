package controlador;

import dao.ContenidoDAO;
import dao.Factory;
import dao.UsuarioDAO;
import java.util.List;
import modelo.*;

/**
 * Controlador que gestiona la publicación y consultas del catálogo de contenidos.
 */
public class ContenidoController {

    private ContenidoDAO contenidoDAO;
    private UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa las instancias DAO de Contenido y Usuario
     */
    public ContenidoController() {
        this.contenidoDAO = Factory.getContenidoDAO();
        this.usuarioDAO = Factory.getUsuarioDAO();
    }

    /**
     * Publica un nuevo contenido (Canción, Podcast u Audiolibro) asociado a un Creador
     * @param tipo 1 para Canción, 2 para Podcast, 3 para Audiolibro
     * @param userName Username del usuario creador
     * @param titulo Título de la obra
     * @param nombreGenero Género temático o musical
     * @throws IllegalArgumentException Si algún parámetro es nulo, inválido o está duplicado
     */
    public void publicar(int tipo, String userName, String titulo, String nombreGenero) {
        // 1. Validar que los campos de texto no estén vacíos
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título del contenido no puede estar vacío.");
        }
        if (nombreGenero == null || nombreGenero.isBlank()) {
            throw new IllegalArgumentException("Debe especificar un género.");
        }

        // 2. Validar que el género exista dentro del Enum Genero
        Genero genero;
        try {
            genero = Genero.valueOf(nombreGenero.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El género '" + nombreGenero + "' no es válido en el sistema.");
        }

        // 3. Validar que el creador exista y sea efectivamente un UsuarioCreador
        if (userName == null || userName.isBlank()) { 
            throw new IllegalArgumentException("El nombre de usuario del creador no puede estar vacío."); 
        }
        UsuarioConsumidor usuario = usuarioDAO.buscarPorNombreUsuario(userName.trim());
        if (usuario == null) {
            throw new IllegalArgumentException("No existe ningún usuario registrado con el username: " + userName);
        }
        if (!(usuario instanceof UsuarioCreador)) {
            throw new IllegalArgumentException("El usuario '" + userName + "' es Consumidor y no tiene perfil de Creador para publicar.");
        }
        UsuarioCreador creador = (UsuarioCreador) usuario;

        // 4. Validar que no exista un contenido duplicado para ese mismo creador
        if (contenidoDAO.buscarPorNombreYCreador(titulo.trim(), creador.getId()) != null) {
            throw new IllegalArgumentException("El creador '" + userName + "' ya tiene una obra publicada con el título '" + titulo + "'.");
        }

        // 5. Instanciar el tipo concreto según la selección
        Contenido nuevoContenido;
        int duracionEstimada = 180; // Segundos por defecto para la carga rápida

        if (tipo == 1) {
            nuevoContenido = new Cancion(titulo.trim(), duracionEstimada, genero, creador, "Letra no disponible", true);
        } else if (tipo == 2) {
            nuevoContenido = new EpisodioPodcast(titulo.trim(), duracionEstimada, genero, creador, "Resumen del episodio", 1);
        } else if (tipo == 3) {
            nuevoContenido = new Audiolibro(titulo.trim(), duracionEstimada, genero, creador, "Narrador no especificado", "Sinopsis no disponible", 1);
        } else {
            throw new IllegalArgumentException("Tipo de contenido no reconocido: " + tipo);
        }

        // 6. Persistir mediante el DAO
        contenidoDAO.guardar(nuevoContenido);
    }

    /**
     * Recupera la lista de contenidos filtrados por género.
     * 
     * @param nombreGenero Nombre del género a consultar
     * @return Lista de contenidos pertenecientes al género
     * @throws IllegalArgumentException Si el género está vacío
     */
    public List<Contenido> listarPorGenero(String nombreGenero) {
        if (nombreGenero == null || nombreGenero.isBlank()) {
            throw new IllegalArgumentException("El género a consultar no puede estar vacío.");
        }
        return contenidoDAO.listarPorGenero(nombreGenero.trim().toUpperCase());
    }

    /**
     * Obtiene el contenido con mayor número de reproducciones acumuladas.
     * 
     * @return Objeto Contenido más escuchado, o null si el catálogo está vacío
     */
    public Contenido obtenerMasReproducido() {
        return contenidoDAO.obtenerMasReproducido();
    }
}
