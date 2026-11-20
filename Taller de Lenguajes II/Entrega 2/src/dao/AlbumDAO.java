package dao;
import java.util.List; 
import modelo.Album;

/**
 * Interfaz de acceso a datos para la entidad Album (contenedores exclusivos de música)
 */
public interface AlbumDAO { 
    /**
     * Persiste un nuevo álbum en la base de datos
     * @param album Objeto álbum a guardar.
     */
    void guardar(Album album); 
    
    /**
     * Busca un álbum por su ID. 
     * @param id ID del álbum
     * @return El objeto Album o null
     */
    Album buscarPorId(int id); 

    /**
     * Busca un álbum por nombre y creador para evitar duplicados
     * @param nombre Nombre del álbum
     * @param idCreador ID del usuario creador
     * @return El álbum si ya existe o null
     */
    Album buscarPorNombreYCreador(String nombre, int idCreador); 

    /**Devuelve todos los álbumes registrados en la plataforma
     * @return Lista de álbumes
     */
    List<Album> listarTodos();
}
