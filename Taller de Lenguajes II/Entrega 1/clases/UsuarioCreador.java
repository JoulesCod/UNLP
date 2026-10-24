package clases;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un usuario creador de contenido (artista, autor, podcaster) en la plataforma.
 * Hereda de {@link UsuarioConsumidor} y gestiona sus obras publicadas junto con sus métricas acumuladas.
 */
public class UsuarioCreador extends UsuarioConsumidor {
    private List<Contenido> contenidosPublicados;

    /**
     * Instancia un nuevo creador de contenido con su lista de publicaciones vacía.
     * @param nombre Nombre de usuario o nombre artístico.
     * @param email Correo electrónico de registro.
     */
    public UsuarioCreador(String nombre, String email){
        super(nombre,email);
        this.contenidosPublicados= new ArrayList<>();
    }

    /**
     * Publica una nueva obra agregándola a la lista personal del creador y al catálogo de la plataforma.
     * @param c {@link Contenido} a publicar. 
     * @param p Instancia de la {@link PlataformaStreaming}.
     */
    public void publicarContenido(Contenido c, PlataformaStreaming p){
        if (c!=null && p!=null){
            this.contenidosPublicados.add(c);
            p.publicarContenido(c);
        }
    }

    /**
     * Genera un resumen conformado por las estadísticas generales del creador.
     * @return Cadena formateada con el reporte.
     */
    public String obtenerEstadisticas(){
        Contenido masEscuchado = getContenidoMasEscuchado(); 
        String nombreMasEscuchado = (masEscuchado != null) ? masEscuchado.getNombre() : "Ninguno";  
        return "--- ESTADÍSTICAS DEL CREADOR: " + getNombreUsuario() + " ---\n" 
        + "Publicaciones totales: " + contenidosPublicados.size() + "\n" 
        + "Reproducciones acumuladas: " + getReproduccionesTotales() + "\n" 
        + "Me gusta totales: " + getCantidadMeGustaTotales() + "\n" 
        + "Recaudación acumulada: $" + String.format("%.2f", getRecaudacionTotal()) + "\n" 
        + "Contenido más escuchado: " + nombreMasEscuchado;
    }

    /**
     * Calcula cuántas reproducciones acumulan en total todas las obras del creador. 
     * @return Suma total de reproducciones.
     */
    public int getReproduccionesTotales(){
        int i=0;
        for (Contenido cont: contenidosPublicados){
            if (cont!=null){
                i+=cont.getReproducciones();
            }
        }
        return i;
    }

    /**
     * Identifica la obra más escuchada del creador. 
     * @return El {@link Contenido} con mayor número de reproducciones o {@code null} si no tiene publicaciones.
     */
    public Contenido getContenidoMasEscuchado(){
        Contenido cMax= null;
        int max= -1;
        for (Contenido c: contenidosPublicados){
            if (c!=null){
                if (c.getReproducciones()> max){
                    max=c.getReproducciones();
                    cMax= c;
                }
            }
        }
        return cMax;
    }
    
    /**
     * Calcula la cantidad acumulada de "me gusta" recibidos en todas sus obras. 
     * @return Cantidad total de "me gusta". 
     */
    public int getCantidadMeGustaTotales(){
        int i=0;
        for (Contenido c: contenidosPublicados){
            if (c!=null){
                i+= c.getCantMeGusta();
            }
        }
        return i;
    }

    /**
     * Calcula los ingresos totales generados por la reproducción de sus obras.
     * @return Monto total recaudado en moneda local. 
     */
    public double getRecaudacionTotal(){
        double i=0;
        for (Contenido c: contenidosPublicados){
            if (c!=null){
                i+= c.getRecaudadoTotal();
            }
        }
        return i;
    }

    // ----Getters ---
    public List<Contenido> getContenidosPublicados(){
        return this.contenidosPublicados;
    }
}
