package controlador;

import dao.AlbumDAO;
import dao.ContenidoDAO;
import dao.Factory;
import dao.UsuarioDAO;
import modelo.Album;
import modelo.Cancion;
import modelo.Contenido;
import modelo.UsuarioConsumidor;
import modelo.UsuarioCreador;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Controlador encargado de la gestión de álbumes musicales.
 * Garantiza que solo los creadores puedan publicar álbumes y que estos
 * contengan únicamente canciones.
 */
public class AlbumController {

    private AlbumDAO albumDAO;
    private UsuarioDAO usuarioDAO;
    private ContenidoDAO contenidoDAO;

    /**
     * Constructor que inicializa las dependencias DAO necesarias
     */
    public AlbumController() {
        this.albumDAO = Factory.getAlbumDAO();
        this.usuarioDAO = Factory.getUsuarioDAO();
        this.contenidoDAO = Factory.getContenidoDAO();
    }

    /**
     * Crea un nuevo álbum para un usuario creador
     * 
     * @param nombre Título del álbum
     * @param idCreador ID del creador propietario
     * @return Objeto Album creado
     * @throws IllegalArgumentException Si el creador no existe o no es un Creador
     */
    public Album crearAlbum(String nombre, int idCreador) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del álbum no puede estar vacío.");
        }

        UsuarioConsumidor u = usuarioDAO.buscarPorId(idCreador);
        if (u == null) {
            throw new IllegalArgumentException("No existe el usuario con ID: " + idCreador);
        }
        if (!(u instanceof UsuarioCreador)) {
            throw new IllegalArgumentException("El usuario debe ser un Creador para publicar un álbum.");
        }

        if (albumDAO.buscarPorNombreYCreador(nombre, idCreador) != null) {
            throw new IllegalArgumentException("El creador ya posee un álbum titulado '" + nombre + "'.");
        }

        Album nuevo = new Album(nombre);
        albumDAO.guardar(nuevo);
        return nuevo;
    }

    /**
     * Vincula una canción a un álbum verificando que sea de tipo Canción
     * 
     * @param idAlbum ID del álbum destino
     * @param idContenido ID de la canción a vincular
     * @throws IllegalArgumentException Si el contenido no es una instancia de Cancion
     */
    public void agregarCancionAAlbum(int idAlbum, int idContenido) {
        Album album = albumDAO.buscarPorId(idAlbum);
        if (album == null) {
            throw new IllegalArgumentException("No existe el álbum con ID: " + idAlbum);
        }

        Contenido contenido = contenidoDAO.buscarPorId(idContenido);
        if (contenido == null) {
            throw new IllegalArgumentException("No existe el contenido con ID: " + idContenido);
        }

        // Validación estricta del enunciado: Álbumes solo admiten música (Cancion)
        if (!(contenido instanceof Cancion)) {
            throw new IllegalArgumentException("Un álbum solo puede contener Canciones (Música). "
                    + "El contenido '" + contenido.getNombre() + "' no es una canción.");
        }

        album.agregarCancion((Cancion) contenido);
    }

    /**
     * Consulta un álbum por su clave primaria ID
     * 
     * @param id Identificador numérico del álbum
     * @return Objeto Album o null si no se encuentra
     */
    public Album buscarPorId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID de álbum inválido.");
        return albumDAO.buscarPorId(id);
    }

    /**
     * Devuelve el listado completo de álbumes
     * 
     * @return Lista de álbumes registrados
     */
    public List<Album> listarTodos() {
        return albumDAO.listarTodos();
    }
}