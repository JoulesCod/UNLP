package dao.implJDBC;

import conexion.MyConnection;
import dao.ContenidoDAO;
import dao.DAOException;
import dao.Factory;
import dao.PlaylistDAO;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

/**
 * Implementación concreta en JDBC de la interfaz {@link PlaylistDAO}
 * Administra la persistencia de las Playlists y la relación en la tabla intermedia PLAYLIST_X_CONTENIDO
 */
public class PlaylistDAOjdbc implements PlaylistDAO {

    /**
     * {@inheritDoc} 
     * Ejecuta una sentencia INSERT INTO sobre la tabla PLAYLIST 
     * @throws DAOException Si ocurre un error al guardar la playlist
     */ 
    @Override
    public void guardar(Playlist playlist) {
        String sql = "INSERT INTO PLAYLIST (nombre, id_usuario) VALUES (?, ?)";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, playlist.getNombre());
            ps.setInt(2, playlist.getCreador() != null ? playlist.getCreador().getId() : 1);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    playlist.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al guardar la playlist", e);
        }
    }

    /**
     * {@inheritDoc} 
     * Inserta una fila en la tabla intermedia PLAYLIST_X_CONTENIDO asociando la lista con el contenido
     * @throws DAOException Si ocurre un error al vincular el contenido con la playlist
     */
    @Override
    public void agregarContenido(int idPlaylist, int idContenido, int posicion) {
        String sql = "INSERT INTO PLAYLIST_X_CONTENIDO (id_playlist, id_contenido, posicion) VALUES (?, ?, ?)";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPlaylist);
            ps.setInt(2, idContenido);
            ps.setInt(3, posicion);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al vincular contenido a la playlist", e);
        }
    }

    /**
     * {@inheritDoc} 
     * Consulta y devuelve la lista de playlists pertenecientes a un usuario consumidor específico
     * 
     * @param idUsuario Identificador único del usuario
     * @return Lista de playlists del usuario.
     * @throws DAOException Si ocurre un error de lectura en SQLite
     */
    @Override
    public List<Playlist> obtenerPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM PLAYLIST WHERE id_usuario = ?";
        List<Playlist> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) { 
                UsuarioDAO usuarioDAO = Factory.getUsuarioDAO(); 
                UsuarioConsumidor u = usuarioDAO.buscarPorId(idUsuario); 
                while (rs.next()) { 
                    Playlist p = new Playlist(rs.getString("nombre"), u); 
                    p.setId(rs.getInt("id")); 
                    lista.add(p); 
                } 
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener las playlists del usuario", e);
        }
        return lista;
    }

    /**{@inheritDoc}
     * Ejecuta una consulta SELECT sobre la tabla PLAYLIST filtrando por id_usuario
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public List<Contenido> obtenerContenidosDePlaylist(int idPlaylist) {
        String sql = "SELECT pc.id_contenido FROM PLAYLIST_X_CONTENIDO pc WHERE pc.id_playlist = ? ORDER BY pc.posicion ASC";
        List<Contenido> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPlaylist);
            try (ResultSet rs = ps.executeQuery()) {
                ContenidoDAO contenidoDAO = Factory.getContenidoDAO();
                while (rs.next()) {
                    int idContenido = rs.getInt("id_contenido");
                    Contenido c = contenidoDAO.buscarPorId(idContenido); 
                    if (c != null) { 
                        lista.add(c); 
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener contenidos de la playlist", e);
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT sobre la tabla PLAYLIST filtrando por su clave primaria id.
     * @throws DAOException Si ocurre un error de lectura en la base de datos.
     */
    @Override
    public Playlist buscarPorId(int id) {
        String sql = "SELECT * FROM PLAYLIST WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UsuarioDAO usuarioDAO= Factory.getUsuarioDAO();
                    int id_usuario= rs.getInt("id_usuario");
                    UsuarioConsumidor u = usuarioDAO.buscarPorId(id_usuario);

                    Playlist p = new Playlist(rs.getString("nombre"), u);
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar playlist por ID", e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * Elimina el registro de la tabla PLAYLIST y sus asociaciones en la tabla intermedia.
     * 
     * @throws DAOException Si ocurre un error al eliminar la playlist de la base de datos.
     */
    @Override
    public void eliminar(int idPlaylist) {
        String sql = "DELETE FROM PLAYLIST WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPlaylist);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la playlist", e);
        }
    }
}