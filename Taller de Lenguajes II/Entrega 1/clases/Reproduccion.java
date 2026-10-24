package clases;
import java.time.LocalDateTime;

/**
 * Representa un evento individual de reproducción dentro del historial del usuario. 
 * Almacena una marca temporal y la referencia al {@link Contenido} escuchado.
 */
public class Reproduccion {

    private LocalDateTime fechaReproduccion;
    private Contenido contenidoAsociado;

    /**
     * Instancia un nuevo registro de reproducción asignando el instante actual.
     * @param c {@link Contenido} reproducido.
     */
    public Reproduccion(Contenido c){
        this.contenidoAsociado= c;
        this.fechaReproduccion= LocalDateTime.now();
    }

    // --- Getters ---
    public LocalDateTime getFechaHora(){
        return this.fechaReproduccion;
    }
    public Contenido getContenido(){
        return this.contenidoAsociado;
    }
}
