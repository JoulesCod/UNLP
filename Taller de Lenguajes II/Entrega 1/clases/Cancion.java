package clases;

/**
 * Representa una canción dentro de la plataforma de streaming. 
 * Hereda de {@link Contenido} y añade información sobre la letra, artista invitado y si es un single.
 */
public class Cancion extends Contenido{
    private String letra;
    private String invitado; 
    private boolean esSingle;

    /**
     * Constructor completo para una canción con artista invitado.
     * @param nombre Título de la canción. 
     * @param duracionEnSegundos Duración total en segundos.
     * @param genero {@link Genero Género} musical.
     * @param creador {@link UsuarioCreador} autor de la obra.
     * @param letra Letra completa de la canción. 
     * @param invitado Nombre del artista invitado / feat (puede ser {@code null}). 
     * @param esSingle {@code true} si se lanzó como sencillo individual, {@code false} en caso contrario
     */
    public Cancion(String nombre, int duracionEnSegundos, Genero genero, 
    UsuarioCreador creador, String letra, String invitado, boolean esSingle){
        super(nombre, duracionEnSegundos, genero, creador);
        this.letra= letra;
        this.invitado= invitado;
        this.esSingle= esSingle;
    }

    /**
     * Constructor sobrecargado para una canción sin artista invitado. 
     * @param nombre Título de la canción.
     * @param duracionEnSegundos Duración total en segundos.
     * @param genero {@link Genero Género} musical.  
     * @param creador {@link UsuarioCreador} autor de la obra.
     * @param letra Letra de la canción.
     * @param esSingle {@code true} si es un sencillo individual.
     */
    public Cancion(String nombre, int duracionEnSegundos, Genero genero, 
    UsuarioCreador creador, String letra, boolean esSingle){
        this(nombre, duracionEnSegundos, genero, creador, letra, null, esSingle);
    }

    /**
     * Ejecuta la reproducción de la canción e incrementa sus métricas.
     */
    @Override 

    public void reproducirContenido(){
        String textoInvitado = (invitado != null && !invitado.isBlank()) ? " (feat. " + getInvitado() + ")" : ""; 
        System.out.println("Reproduciendo la canción: " + getNombre() + textoInvitado + " - " + toString());
    }

    // --- getters ---
    public String getLetra() { 
        return this.letra; 
    } 
    public void setLetra(String letra) { 
        if (letra != null && !letra.isBlank()) { 
            this.letra = letra; 
        } 
    } 
    public String getInvitado() { 
        return this.invitado; 
    } 
    public void setInvitado(String invitado) { 
        this.invitado = invitado; 
    } 
    public boolean isEsSingle() {
        return this.esSingle; 
    } 
    public boolean getEsSingle() { 
        return this.esSingle; 
    } 
    public void setEsSingle(boolean esSingle) { 
        this.esSingle = esSingle; 
    }
}
