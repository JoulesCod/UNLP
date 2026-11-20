package controlador;

import dao.ContenidoDAO;
import dao.Factory;
import dao.PlaylistDAO;
import dao.UsuarioDAO;
import modelo.Contenido;
import modelo.Playlist;
import modelo.UsuarioConsumidor;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Controlador para la administración de Playlists de los usuarios consumidores.
 */
public class PlaylistController {

    private PlaylistDAO playlistDAO;
    private UsuarioDAO usuarioDAO;
    private ContenidoDAO contenidoDAO;

    /**
     * Constructor que inicializa los DAOs de Playlist, Usuario y Contenido
     */
    public PlaylistController() {
        this.playlistDAO = Factory.getPlaylistDAO();
        this.usuarioDAO = Factory.getUsuarioDAO();
        this.contenidoDAO = Factory.getContenidoDAO();
    }

    /**
     * Crea una nueva playlist asociada a un usuario consumidor
     * 
     * @param nombre Nombre descriptivo de la lista
     * @param idUsuario ID del consumidor propietario
     * @return Objeto Playlist instanciado y guardado
     */
    public Playlist crearPlaylist(String nombre, int idUsuario) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la playlist no puede estar vacío.");
        }

        UsuarioConsumidor u = usuarioDAO.buscarPorId(idUsuario);
        if (u == null) {
            throw new IllegalArgumentException("No existe un usuario con el ID: " + idUsuario);
        }

        Playlist nueva = new Playlist(nombre, u);
        playlistDAO.guardar(nueva);
        return nueva;
    }

    /**
     * Agrega un contenido a una playlist en una posición ordinal específica
     * 
     * @param idPlaylist ID de la playlist
     * @param idContenido ID del contenido a agregar
     * @param posicion Posición ordinal en la lista
     */
    public void agregarContenido(int idPlaylist, int idContenido, int posicion) {
        if (posicion <= 0) {
            throw new IllegalArgumentException("La posición en la playlist debe ser mayor a 0.");
        }

        if (playlistDAO.buscarPorId(idPlaylist) == null) {
            throw new IllegalArgumentException("No existe la playlist con ID: " + idPlaylist);
        }

        if (contenidoDAO.buscarPorId(idContenido) == null) {
            throw new IllegalArgumentException("No existe el contenido con ID: " + idContenido);
        }

        playlistDAO.agregarContenido(idPlaylist, idContenido, posicion);
    }

    /**
     * Obtiene todas las playlists creadas por un usuario consumidor
     * 
     * @param idUsuario ID del consumidor
     * @return Lista de playlists del usuario
     */
    public List<Playlist> obtenerPorUsuario(int idUsuario) {
        if (idUsuario <= 0) throw new IllegalArgumentException("ID de usuario inválido.");
        return playlistDAO.obtenerPorUsuario(idUsuario);
    }

    /**
     * Obtiene los contenidos dentro de una playlist ordenados por su posición
     * 
     * @param idPlaylist ID de la playlist
     * @return Lista de contenidos incluidos
     */
    public List<Contenido> obtenerContenidosDePlaylist(int idPlaylist) {
        if (idPlaylist <= 0) throw new IllegalArgumentException("ID de playlist inválido.");
        return playlistDAO.obtenerContenidosDePlaylist(idPlaylist);
    }

    /**
     * Elimina una playlist del sistema
     * @param idPlaylist ID de la playlist a eliminar
     */
    public void eliminar(int idPlaylist) {
        if (idPlaylist <= 0) throw new IllegalArgumentException("ID de playlist inválido.");
        playlistDAO.eliminar(idPlaylist);
    }
}
