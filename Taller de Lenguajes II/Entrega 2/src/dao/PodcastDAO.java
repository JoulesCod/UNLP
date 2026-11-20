package dao; 
import java.util.List; 
import modelo.Podcast; 
/**
 * Interfaz de acceso a datos para la entidad Podcast (contenedores de episodios).
 */
public interface PodcastDAO {
    /**
     * Persiste un nuevo podcast en la base de datos. 
     * @param podcast Objeto podcast a guardar
     */ 
    void guardar(Podcast podcast); 

    /**Busca un podcast por su ID
     * @param id ID del podcast
     * @return El objeto Podcast o null
     */
    Podcast buscarPorId(int id); 

    /**
     * Busca un podcast por nombre y creador para evitar duplicados.
     * @param nombre Nombre del podcast
     * @param idCreador ID del usuario creador
     * @return El podcast si ya existe o null
     */
    Podcast buscarPorNombreYCreador(String nombre, int idCreador); 
    
    /**
     * Devuelve todos los podcasts registrados en la plataforma.
     * @return Lista de podcasts
     */
    List<Podcast> listarTodos();
}
