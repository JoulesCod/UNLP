package dao;
import java.util.List; 
import modelo.Contenido; 

/**
 * Interfaz de acceso a datos para la entidad Contenido 
 * Administra la publicación, filtrados por género y búsquedas del catálogo.
 */
public interface ContenidoDAO {
    /**
     * Persiste un nuevo contenido publicado por un usuario creador
     * @param contenido Objeto contenido a guardar en el catálogo. 
     */
    void guardar(Contenido contenido); 
    
    /**
     * Busca un contenido específico por ID. 
     * @param id ID del contenido
     * @return El objeto Contenido o null si no existe.
     */
    Contenido buscarPorId(int id);
    
    /**
     * Busca si un creador ya tiene publicado un contenido con el mismo nombre.
     * Utilizado para validar que no existan contenidos duplicados. 
     * @param nombre Título del contenido
     * @param idCreador ID del usuario creador
     * @return El contenido coincidente o null si no hay duplicados. 
     */
    Contenido buscarPorNombreYCreador(String nombre, int idCreador); 
    
    
    /**
     * Obtiene el contenido con la mayor cantidad de reproducciones acumuladas
     * Responde a la funcionalidad obligatoria: "Consultar el contenido con más reproducciones"
     * @return El contenido con más reproducciones o null si el catálogo está vacío.
     */
    Contenido obtenerMasReproducido();

    /**
     * Recupera todos los contenidos que pertenecen a un género específico. 
     * Responde a la funcionalidad obligatoria: "Filtrar contenido por género". 
     * @param genero Género a buscar (ej. ROCK, TRAP, PODCAST, etc.)
     * @return Lista de contenidos que pertenecen a ese género.
     */
    List<Contenido> listarPorGenero(String genero);

    /**
     * Devuelve la lista completa de todo el catálogo publicado
     * @return Lista completa de contenidos
     */
    List<Contenido> listarTodos();
}
