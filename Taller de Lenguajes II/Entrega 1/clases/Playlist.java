package clases;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Representa una lista de reproducción en la plataforma de streaming.
 * Agrupa distintos {@link Contenido} y pertenece obligatoriamente a un {@link UsuarioConsumidor}.
 */
public class Playlist {
    private String nombre;
    private String id;
    private UsuarioConsumidor creador;
    private List<Contenido> contenidos;

    /**
     * Instancia una nueva playlist vinculada de forma obligatoria a su {@link UsuarioConsumidor creador}.
     * @param nombre Nombre descriptivo de la lista de reproducción.
     * @param creador {@link UsuarioConsumidor} propietario de la lista. 
     */
    public Playlist(String nombre, UsuarioConsumidor creador){
        this.nombre= nombre;
        this.contenidos= new ArrayList<>();
        this.creador= creador;
        this.id= "plt-"+UUID.randomUUID().toString().substring(0, 8);

    }
    
    /**
     * Agrega un {@link Contenido} a la lista de reproducción. 
     * @param c {@link Contenido} a incorporar. 
     */
    public void agregarContenido(Contenido c){
        if (c!=null && !contenidos.contains(c)){
            this.contenidos.add(c);
        }
    }

    /**
     * Remueve un {@link Contenido} de la lista de reproducción. 
     * @param c {@link Contenido} a eliminar. 
     */
    public void eliminarContenido(Contenido c){
        if (c!=null){
            this.contenidos.remove(c);
        }
    }

    /**Calcula la duración total acumulada sumando la duración de cada {@link Contenido} guardado. 
     * @return Duración total expresada en segundos.
     */
    public int getDuracionTotal(){
        int tot=0;
        for (Contenido c: contenidos){
            if (c!=null){
                tot+=c.getDuracionEnSegundos();
            }
        }
        return tot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist '").append(nombre).append("' (").append(contenidos.size()).append(" ítems):\n");
        if (contenidos.isEmpty()) {
            sb.append("   (Lista vacía)\n");
        } else {
            for (Contenido c : contenidos) {
                sb.append("   - ").append(c.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // --- Getters y setters ---
    public String getNombre(){
        return this.nombre;
    }
    public List<Contenido> getContenidos(){
        return this.contenidos;
    }
    public UsuarioConsumidor getCreador(){
        return this.creador;
    }
    public String getId(){
        return this.id;
    }
    public void setNombre(String nombre) { 
        if (nombre != null && !nombre.isBlank()) { 
            this.nombre = nombre; 
        } 
    } 
    public void setCreador(UsuarioConsumidor creador) { 
        if (creador != null) { 
            this.creador = creador; 
        } 
    }
}
