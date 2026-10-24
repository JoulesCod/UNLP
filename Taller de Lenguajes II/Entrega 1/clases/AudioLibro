package clases;

/**
 * Representa un audiolibro en la plataforma de streaming.
 * Hereda de {@link Contenido} y añade información específica sobre la narración, 
 * la sinopsis y la cantidad de capítulos.
 */
public class Audiolibro extends Contenido{
    private String narrador;
    private String sinopsis;
    private int cantCapitulos;

    /**
     * Instancia un nuevo audiolibro inicializando los atributos de la superclase y los propios. 
     * @param nombre Título del audiolibro.
     * @param duracionEnSegundos Duración total en segundos.
     * @param genero {@link Genero Género} o categoría temática.
     * @param creador {@link UsuarioCreador} autor de la obra (se asigna como creador).
     * @param narrador Nombre de la persona que narra el audiolibro.
     * @param sinopsis Resumen descriptivo de la obra.
     * @param cantCapitulos Número total de capítulos.
     */
    public Audiolibro(String nombre, int duracionEnSegundos, Genero genero, 
    UsuarioCreador creador, String narrador, String sinopsis, int cantCapitulos){
        super(nombre, duracionEnSegundos, genero, creador);
        this.narrador= narrador;
        this.cantCapitulos= cantCapitulos;
        this.sinopsis= sinopsis;
    }

    /**
     * Ejecuta la reproducción del audiolibro e incrementa sus métricas de escuchas.
     */
    @Override 
    public void reproducirContenido(){
        System.out.println("Reproduciendo el audiolibro "+toString()+", narrado por "
        +getNarrador()+" y con "+getCantCapitulos()+" capitulos.");
    }

    // ---Getters---
    public String getNarrador(){
        return this.narrador;
    }
    public void setNarrador(String narrador) { 
        if (narrador != null && !narrador.isBlank()) { 
            this.narrador = narrador; 
        } 
    }
    public String getSinopsis(){
        return this.sinopsis;
    }
    public void setSinopsis(String sinopsis) { 
        if (sinopsis != null && !sinopsis.isBlank()) { 
            this.sinopsis = sinopsis; 
        } 
    }
    public int getCantCapitulos(){
        return this.cantCapitulos;
    }
    public void setCantCapitulos(int cantCapitulos) { 
        if (cantCapitulos > 0) { 
            this.cantCapitulos = cantCapitulos; 
        } 
    }
}
