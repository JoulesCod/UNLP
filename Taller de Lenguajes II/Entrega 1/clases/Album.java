package clases;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** 
 * Representa un álbum musical que agrupa una colección de {@link Cancion}. 
 * Mantiene la información del lanzamiento y permite calcular métricas globales como la duración total.
 */
public class Album {
    private String nombre; 
    private String id;
    private LocalDate fechaLanzamiento;
    private List<Cancion> canciones;

    /**
     * Instancia un nuevo álbum generando su identificador único y su lista de canciones.
     * @param nombre Título del álbum.
     */
    public Album(String nombre){
        this.nombre= nombre;
        this.fechaLanzamiento= LocalDate.now();
        this.canciones= new ArrayList<>();
        this.id= "alb-"+UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * Incorpora una nueva {@link Cancion} a la lista de canciones del álbum. 
     * @param c {@link Cancion} a agregar.
     */
    public void agregarCancion(Cancion c){
        if (c!=null){
            this.canciones.add(c);
        }
    }
    
    /**
     * Calcula la duración total del álbum sumando la duración individual de cada {@link Cancion}.
     * @return Duración total en segundos.
     */
    public int getDuracionTotal(){
        int total=0;
        for (Cancion c: canciones){
            if (c!=null){
                total+= c.getDuracionEnSegundos();
            }
        }
        return total;
    }

    @Override
    public String toString() { 
        int totalSeg = getDuracionTotal(); 
        int min = totalSeg / 60; 
        int seg = totalSeg % 60; 
        return String.format("Álbum: %s (%d canciones, %02d:%02d)", getNombre(), getCantidadCanciones(), min, seg); 
    }
 
    // --- Getters ---
    public int getCantidadCanciones(){
        return this.canciones.size();
    }
    public String getId(){
        return this.id;
    }
    public List<Cancion> getCanciones(){
        return this.canciones;
    }
    public void setCanciones(List<Cancion> canciones){
        if (canciones!=null){
            this.canciones= canciones;
        }
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre) { 
        if (nombre != null && !nombre.isBlank()) { 
            this.nombre = nombre; 
        } 
    }
    public LocalDate getFechaLanzamiento(){
        return this.fechaLanzamiento;
    }
}

