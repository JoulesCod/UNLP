package clases;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Representa el catálogo global de contenidos de la {@link PlataformaStreaming}.
 * Permite la publicación, filtrado por género, búsquedas por artista y cálculo de métricas. 
 */
public class Catalogo {
    private List<Contenido> contenidos; 

    /**
     * Inicializa un catálogo vacío. 
    */
    public Catalogo(){
        this.contenidos= new ArrayList<>();
    }

    /**
     * Incorpora un nuevo {@link Contenido} al catálogo público.
     * @param c {@link Contenido} a publicar. 
     */
    public void publicarContenido(Contenido c){
        if (c!=null && !contenidos.contains(c)){
            contenidos.add(c);
        }
    }
    
    /**
     * Busca y devuelve un {@link Contenido} según su título o nombre. 
     * @param nombre Título o nombre del contenido buscado. 
     * @return El {@link Contenido} encontrado, o {@code null} si no existe. 
     */
    public Contenido getContenido(String nombre){
        if (nombre != null){
            for (Contenido c: contenidos){
                if (c.getNombre().equalsIgnoreCase(nombre)){
                    return c;
                }
            }
        }
        return null; 
    }

    /**
     * Obtiene el {@link Contenido} con mayor número de reproducciones en la plataforma.
     * @return El {@link Contenido} más escuchado, o {@code null} si el catálogo está vacío. 
     */
    public Contenido getContenidoMasReproducido(){
        if (this.contenidos.isEmpty()) { 
            return null; 
        } 
        Contenido contenidoMaxRep = this.contenidos.get(0); 
        for (Contenido c : contenidos) { 
            if (c.getReproducciones() > contenidoMaxRep.getReproducciones()) { 
                contenidoMaxRep = c; 
            } 
        } 
        return contenidoMaxRep; 
    }

    /**
     * Filtra los contenidos pertenecientes a un determinado creador o artista.
     * @param creador Nombre del creador/artista a consultar. 
     * @return Lista de {@link Contenido} pertenecientes al creador.
     */
    public List<Contenido> getContenidosArtista(String creador){
        List<Contenido> lista = new ArrayList<>();
        if (creador!=null){
            for (Contenido c: contenidos){
                if (c.getArtista()!=null && c.getArtista().getNombreUsuario().equalsIgnoreCase(creador)){
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    /**
     * Filtra la lista de contenidos según un género específico.
     * @param genero Género musical o temático a buscar.
     * @return Lista de {@link Contenido} que coinciden con el género. 
     */
    public List<Contenido> filtrarPorGenero(Genero genero){
        List<Contenido> lista = new ArrayList<>();
        if (genero!=null){
            for (Contenido c: contenidos){
                if (c.getGenero() == genero){
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    /**
     * Imprime por consola el listado completo de contenidos del catálogo. 
     */
    public void mostrarCatalogo(){
        System.out.println("CATALOGO DE CONTENIDOS");
        if (this.contenidos.isEmpty()){
            System.out.println("Catalogo vacio.");
        } else {
            for (Contenido c: contenidos){
                System.out.println(c);
            }
        }
    }

    public List<Contenido> buscarContenido(String criterio) {
        List<Contenido> lista= new ArrayList<>();
        if (criterio != null && !criterio.isBlank()) {
            String crit = criterio.toLowerCase(); 
            for (Contenido c : contenidos) { 
                boolean coincideNombre = c.getNombre() != null && c.getNombre().toLowerCase().contains(crit); 
                boolean coincideGenero = c.getGenero() != null && c.getGenero().name().toLowerCase().contains(crit); 
                if (coincideNombre || coincideGenero) { 
                    lista.add(c); 
                } 
            }
        }
        return lista;
    }


    // --- Getters y setters---
    public List<Contenido> getContenidos(){
        return this.contenidos;
    }
    public void setContenidos(List<Contenido> contenidos){
        if (contenidos!=null){
            this.contenidos= contenidos;
        }
    }
}
