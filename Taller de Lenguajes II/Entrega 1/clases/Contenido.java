package clases;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Clase abstracta base que representa cualquier contenido de audio publicable en la plataforma.
 * Define la estructura común para obras como {@link Cancion}, {@link EpisodioPodcast} y {@link Audiolibro},
 * almacenando sus métricas de interacción, reproducción y recaudación. 
 */
public abstract class Contenido {
    private String id;
    private int duracionEnSegundos;
    private int cantReproducciones;
    private String nombre;
    private LocalDate fechaLanzamiento;
    private Genero genero; 
    private UsuarioCreador creador;
    private int cantMeGusta;
    private double recaudadoTotal;
    private int reproduccionesMujeres;
    private int reproduccionesHombres;
    
    /**
     * Constructor base para inicializar un nuevo contenido con sus métricas en cero. 
     * @param nombre Título o nombre del contenido.
     * @param duracionEnSegundos Duración total expresada en segundos. 
     * @param genero {@link Genero Género} temático o musical del contenido.
     * @param creador {@link UsuarioCreador} autor y dueño de la obra. 
     */
    public Contenido(String nombre, int duracionEnSegundos, Genero genero, UsuarioCreador creador){
        this.nombre= nombre;
        this.duracionEnSegundos= duracionEnSegundos;
        this.genero= genero;
        this.creador= creador;
        this.id= "cnt-"+UUID.randomUUID().toString().substring(0, 8);
        this.fechaLanzamiento= LocalDate.now();
        this.cantReproducciones= 0;
        this.cantMeGusta= 0;
        this.recaudadoTotal=0;
        this.reproduccionesMujeres=0;
        this.reproduccionesHombres=0;
    }

    /**
     * Método abstracto que ejecutará la reproducción específica de cada tipo de contenido. 
     */
    public abstract void reproducirContenido();

    /**
     * Incrementa en una unidad el contador global de reproducciones del contenido.
     */
    public void incrementarReproducciones(){
        this.cantReproducciones++;
        this.recaudadoTotal+= 0.005;
    }

    /**
     * Incrementa en una unidad el contador total de me gusta recibidos. 
     */
    public void incrementarMeGusta(){ //se pueden ver  normal los me gusta??
        this.cantMeGusta++;
    }

    /**
     * Determina la tendencia demográfica de los oyentes según el conteo por género.
     * @return Cadena que indica el género predominante ("Femenino", "Masculino" o "Equitativo"). 
     */
    public String getGeneroPredominaOyentes(){
        if (this.reproduccionesMujeres> this.reproduccionesHombres){
            return "Femenino";
        }else{
            if (this.reproduccionesHombres > this.reproduccionesMujeres){
                return "Masculino";
            }
        }
        return "Equitativo";
    }
    
    @Override
    public String toString() {
        int min = getDuracionEnSegundos() / 60;
        int seg = getDuracionEnSegundos() % 60;
        String autorNombre = (creador != null) ? creador.getNombreUsuario() : "Desconocido";
        return String.format("%s - %s (%02d:%02d) | Género: %s | Reproducciones: %d | Likes: %d",
                getNombre(), getArtista(), min, seg, getGenero(), getReproducciones(), getCantMeGusta());
    }

    // --Getters --
    public int getDuracionEnSegundos(){
        return this.duracionEnSegundos;
    }
    public Genero getGenero(){
        return this.genero;
    }
    public int getCantMeGusta() { 
        return this.cantMeGusta; 
    } 
    public double getRecaudadoTotal() { 
        return this.recaudadoTotal; 
    }
    public int getReproduccionesMujeres() { 
        return this.reproduccionesMujeres; 
    } 
    public int getReproduccionesHombres() { 
        return this.reproduccionesHombres; 
    } 
    public LocalDate getFechaLanzamiento() { 
        return this.fechaLanzamiento; 
    }
    public UsuarioCreador getArtista() { 
        return this.creador; 
    } 
    public String getId() {
        return id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public int getReproducciones() { 
        return this.cantReproducciones; 
    }

    // --- Setters --- 
    public void setNombre(String nombre) { 
        if (nombre != null && !nombre.isBlank()) { 
            this.nombre = nombre; 
        } 
    } 
    public void setDuracionEnSegundos(int duracionEnSegundos) { 
        if (duracionEnSegundos > 0) { 
            this.duracionEnSegundos = duracionEnSegundos; 
        } 
    } 
    public void setGenero(Genero genero) { 
        if (genero != null) { 
            this.genero = genero; 
        } 
    } 
    public void setCreador(UsuarioCreador creador) { 
        if (creador != null) { 
            this.creador = creador; 
        } 
    } 
    public void setCantMeGusta(int cantMeGusta) { 
        if (cantMeGusta > 0) { 
            this.cantMeGusta = cantMeGusta; 
        } 
    } 
    public void setRecaudadoTotal(double recaudadoTotal) { 
        if (recaudadoTotal > 0) { 
            this.recaudadoTotal = recaudadoTotal; 
        } 
    } 
    public void setReproduccionesMujeres(int reproduccionesMujeres) { 
        if (reproduccionesMujeres >= 0) { 
            this.reproduccionesMujeres = reproduccionesMujeres; 
        } 
    } 
    public void setReproduccionesHombres(int reproduccionesHombres) { 
        if (reproduccionesHombres >= 0) { 
            this.reproduccionesHombres = reproduccionesHombres; 
        } 
    } 
}
