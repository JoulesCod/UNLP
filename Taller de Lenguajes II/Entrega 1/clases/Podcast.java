package clases;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Representa un programa o canal de podcast dentro de la plataforma de streaming.
 * Funciona como contenedor que agrupa una colección de {@link EpisodioPodcast}.
 */
public class Podcast {
    private String id; 
    private String nombre; 
    private String descripcion; 
    private List<EpisodioPodcast> episodios;

    /**
     * Instancia un nuevo podcast generando su identificador único y su lista de episodios.
     * @param nombre Título o nombre del programa.
     * @param descripcion Breve reseña o sinopsis del podcast.
     */
    public Podcast(String nombre, String descripcion){
        this.nombre= nombre;
        this.descripcion= descripcion;
        this.id= "pod-"+UUID.randomUUID().toString().substring(0, 8);
        this.episodios= new ArrayList<>();
    }

    /**
     * Incorpora un nuevo {@link EpisodioPodcast} al programa.
     * @param e {@link EpisodioPodcast} a agregar.
     */
    public void agregarEpisodio(EpisodioPodcast e){
        if (e!=null){
            this.episodios.add(e);
        }
    }

    // --- Getters y setters ---
    public int getCantEpisodios(){
        return this.episodios.size();
    }
    public List<EpisodioPodcast> getEpisodios(){
        return this.episodios;
    }
    public String getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public void setNombre(String nombre) { 
        if (nombre != null && !nombre.isBlank()) { 
            this.nombre = nombre; 
        } 
    } 
    public void setDescripcion(String descripcion) { 
        if (descripcion != null && !descripcion.isBlank()) { 
            this.descripcion = descripcion; 
        } 
    } 
}
