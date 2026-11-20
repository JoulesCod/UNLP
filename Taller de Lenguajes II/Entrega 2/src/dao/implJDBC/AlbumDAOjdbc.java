package dao.implJDBC;

import conexion.MyConnection;
import dao.AlbumDAO;
import dao.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Album;

/**
 * Interfaz de acceso a datos para la entidad Album (contenedores exclusivos de música)
 */
public class AlbumDAOjdbc implements AlbumDAO {

    /**
     * Persiste un nuevo álbum en la base de datos
     * @param album Objeto álbum a guardar.
     */
    @Override
    public void guardar(Album album) {
        String sql = "INSERT INTO ALBUM (fecha_lanzamiento, nombre) VALUES (?, ?)";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, album.getFechaLanzamiento().toString());
            ps.setString(2, album.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    album.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al guardar el álbum", e);
        }
    }

    /**
     * Busca un álbum por su ID. 
     * @param id ID del álbum
     * @return El objeto Album o null
     */
    @Override
    public Album buscarPorId(int id) {
        String sql = "SELECT * FROM ALBUM WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Album a = new Album(rs.getString("nombre"));
                    a.setId(rs.getInt("id"));
                    return a;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar álbum por ID", e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * Busca un álbum por nombre y creador para evitar duplicados, incluso si el álbum aún no contiene canciones
     * 
     * @param nombre Nombre del álbum a buscar
     * @param idCreador ID del usuario creador
     * @return Instancia de {@link Album} si existe, o null en caso contrario
     * @throws DAOException Si ocurre un error de lectura en SQLite
     */
    @Override
    public Album buscarPorNombreYCreador(String nombre, int idCreador) {
        String sql = "SELECT a.* FROM ALBUM a LEFT JOIN CONTENIDO c ON a.id = c.id_album WHERE a.nombre = ? AND c.id_usuario = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, idCreador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Album a = new Album(rs.getString("nombre"));
                    a.setId(rs.getInt("id"));
                    return a;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar álbum por nombre y creador", e);
        }
        return null;
    }

    /**Devuelve todos los álbumes registrados en la plataforma
     * @return Lista de álbumes
     */
    @Override
    public List<Album> listarTodos() {
        String sql = "SELECT * FROM ALBUM";
        List<Album> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Album a = new Album(rs.getString("nombre"));
                a.setId(rs.getInt("id"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar los álbumes", e);
        }
        return lista;
    }
}