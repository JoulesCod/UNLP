package dao.implJDBC;

import conexion.MyConnection;
import dao.DAOException;
import dao.PodcastDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Podcast;

/**
 * Implementación concreta en JDBC de la interfaz {@link PodcastDAO}
 * Administra la persistencia de los contenedores de podcasts en la BDD
 */
public class PodcastDAOjdbc implements PodcastDAO {

    /**
     * {@inheritDoc}
     * Ejecuta una sentencia INSERT INTO sobre la tabla PODCAST en SQLite
     * @throws DAOException Si ocurre un error al insertar el registro en la BDD
     */
    @Override
    public void guardar(Podcast podcast) {
        String sql = "INSERT INTO PODCAST (fecha_lanzamiento, nombre) VALUES (?, ?)";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, LocalDate.now().toString());
            ps.setString(2, podcast.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    podcast.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al guardar el podcast", e);
        }
    }

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT sobre la tabla PODCAST filtrando por su clave primaria id
     * @throws DAOException Si ocurre un error de lectura en la base de datos
     */
    @Override
    public Podcast buscarPorId(int id) {
        String sql = "SELECT * FROM PODCAST WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Podcast p = new Podcast(rs.getString("nombre"), "Podcast grabado");
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar podcast por ID", e);
        }
        return null;
    }

    /**
     * {@inheritDoc} 
     * Busca un podcast por nombre y creador para evitar duplicados, contemplando programas recién creados sin episodios
     * 
     * @param nombre Título del programa de podcast
     * @param idCreador ID del creador propietario
     * @return Instancia de {@link Podcast} si existe, o null en caso contrario
     * @throws DAOException Si ocurre un error de lectura en SQLite
     */
    @Override
    public Podcast buscarPorNombreYCreador(String nombre, int idCreador) {
        String sql = "SELECT p.* FROM PODCAST p LEFT JOIN CONTENIDO c ON p.id = c.id_podcast WHERE p.nombre = ? AND c.id_usuario = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, idCreador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Podcast p = new Podcast(rs.getString("nombre"), "Podcast grabado");
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar podcast por nombre y creador", e);
        }
        return null;
    }

    /**
     * {@inheritDoc} 
     * Ejecuta una consulta SELECT completa sobre la tabla PODCAST
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public List<Podcast> listarTodos() {
        String sql = "SELECT * FROM PODCAST";
        List<Podcast> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Podcast p = new Podcast(rs.getString("nombre"), "Descripción podcast");
                p.setId(rs.getInt("id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar podcasts", e);
        }
        return lista;
    }
}