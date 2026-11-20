package dao; 
import java.util.List; 
import modelo.Contenido; 
import modelo.Playlist; 

/**
 * Interfaz de acceso a datos para las Playlists y sus contenidos vinculados
 */
public interface PlaylistDAO { 
    /**
     * Crea una nueva playlist asociada a un usuario consumidor.
     * @param playlist Objeto Playlist a guardar.
     */
    void guardar(Playlist playlist); 
    
    /**
     * Inserta un contenido dentro de una playlist en una posición determinada
     * Mapea la tabla intermedia PLAYLIST\_X\_CONTENIDO
     * @param idPlaylist ID de la lista
     * @param idContenido ID del audio
     * @param posicion Orden dentro de la lista (1, 2, 3...)
     */
    void agregarContenido(int idPlaylist, int idContenido, int posicion); 
    
    /**
     * Obtiene todas las playlists creadas por un usuario específico.
     * @param idUsuario ID del usuario consumidor
     * @return Lista de playlists pertenecientes a ese usuario
     */
    List<Playlist> obtenerPorUsuario(int idUsuario);

    /**
     * Recupera todos los contenidos (canciones, episodios, etc.) que pertenecen a una playlist
     * @param idPlaylist ID de la playlist.
     * @return Lista de contenidos ordenados por su posición
     */
    List<Contenido> obtenerContenidosDePlaylist(int idPlaylist);

    /**
     * Busca una Playlist específica por ID. 
     * @param id ID de la Playlist
     * @return El objeto Playlist o null si no existe.
     */
    Playlist buscarPorId(int id);

    /**
     * Elimina una playlist de la base de datos.
     * 
     * @param idPlaylist ID de la lista a borrar.
     */
    void eliminar(int idPlaylist);
}
