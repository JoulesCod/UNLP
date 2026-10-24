package clases;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Clase principal que representa la Plataforma de Streaming.
 * Administra los usuarios registrados mediante {@link UsuarioConsumidor} 
 * y el {@link Catalogo} global de contenidos. 
 */
public class PlataformaStreaming{
    private String nombrePlataforma;
    private LocalDate fechaLanzamiento;
    private List<UsuarioConsumidor> usuarios;
    private Catalogo catalogo;
    
    /**
     * Constructor que inicializa la plataforma con su {@link Catalogo} 
     * y la lista de usuarios.
     * @param nombre Nombre comercial de la plataforma. 
     * @param fecha Fecha de inicio de operaciones. 
     */
    public PlataformaStreaming(String nombre){ 
        this.fechaLanzamiento= LocalDate.now();
        this.nombrePlataforma= nombre;
        this.usuarios= new ArrayList<>(); 
        this.catalogo= new Catalogo(); 
    }

    /**
     * Registra e incorpora un nuevo {@link UsuarioConsumidor} a la plataforma. 
     * @param u Usuario a registrar en el sistema. 
     */
    public void agregarUsuario(UsuarioConsumidor u){
        if (u!=null){
            if (usuarios.contains(u)){
                System.out.println("Ya existe en la plataforma");
            } else {
                this.usuarios.add(u);
                System.out.println("Bienvenido, "+u.getNombreUsuario()+"!");
            }
        }
    }

    /**
     * Publica un nuevo {@link Contenido} en el {@link Catalogo} global de la plataforma.
     * @param c Contenido a incorporar al catálogo. 
     */
    public void publicarContenido(Contenido c){
        if (c!=null){
            this.catalogo.publicarContenido(c);
        }
    }

    /**
     * Muestra por consola todo el {@link Catalogo} de contenidos disponibles. 
    */
    public void mostrarCatalogo(){
        this.catalogo.mostrarCatalogo();
    }

    /**
     * Imprime por consola la lista de todos los {@link UsuarioConsumidor} registrados en la plataforma. 
     */
    public void mostrarUsuarios(){
        System.out.println("Usuarios registrados en "+getNombrePlataforma()+": ");
        if (usuarios.isEmpty()){
            System.out.println("No hay usuarios registrados. ");
        }
        for (UsuarioConsumidor u: usuarios){
            System.out.println(u); 
        }
    }

    // --- Getters y setters---
    public Catalogo getCatalogo(){
        return this.catalogo;
    }

    public String getNombrePlataforma(){
        return this.nombrePlataforma;
    }

    public List<UsuarioConsumidor> getUsuarios(){
        return this.usuarios;
    }
    
    public LocalDate getFechaLanzamiento(){
        return this.fechaLanzamiento;
    }
    public void setNombrePlataforma(String nombrePlataforma) { 
        if (nombrePlataforma != null && !nombrePlataforma.isBlank()) {
             this.nombrePlataforma = nombrePlataforma; 
        } 
    } 
    public void setCatalogo(Catalogo catalogo) { 
        if (catalogo != null) { 
            this.catalogo = catalogo; 
        } 
    } 
    public void setUsuarios(List<UsuarioConsumidor> usuarios){
        if (usuarios != null){
            this.usuarios= usuarios;
        }
    }
}
