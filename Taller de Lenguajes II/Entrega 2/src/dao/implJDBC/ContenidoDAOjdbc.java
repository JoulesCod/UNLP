package dao.implJDBC;

import conexion.MyConnection;
import dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

/**
 * Implementación concreta en JDBC de la interfaz ContenidoDAO para SQLite {@link ContenidoDAO}
 * Administra la publicación, filtrados por género y búsquedas del catálogo en la base de datos SQLite.
 */
public class ContenidoDAOjdbc implements ContenidoDAO {

    /**
     * {@inheritDoc} 
     * Registra un nuevo contenido (Canción, Episodio de Podcast o Audiolibro) en la tabla CONTENIDO 
     * 
     * @param contenido Objeto {@link Contenido} a guardar en la base de datos. 
     * @throws DAOException Si ocurre un error al insertar el registro en SQLite
     */
    @Override
    public void guardar(Contenido contenido) {
        String sql = "INSERT INTO CONTENIDO (nombre, genero, tipo, duracion_segundos, "
            + "cant_reproducciones, cant_meGusta, fecha_lanzamiento, reproducciones_mujeres, "
            + "reproducciones_hombres, recaudado, id_usuario, letra, es_single, id_album, "
            + "invitado, numero_episodio, resumen, id_podcast, narrador, sinopsis, cant_capitulos)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = MyConnection.getCon();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, contenido.getNombre());
            ps.setString(2, contenido.getGenero().name());

            String tipo = "CANCION";
            if (contenido instanceof EpisodioPodcast) 
                tipo = "PODCAST";
            else if (contenido instanceof Audiolibro) 
                tipo = "AUDIOLIBRO";
            ps.setString(3, tipo);
            ps.setInt(4, contenido.getDuracionEnSegundos());
            ps.setInt(5, contenido.getReproducciones());
            ps.setInt(6, contenido.getCantMeGusta());
            ps.setString(7, contenido.getFechaLanzamiento().toString());
            ps.setInt(8, contenido.getReproduccionesMujeres());
            ps.setInt(9, contenido.getReproduccionesHombres());
            ps.setDouble(10, contenido.getRecaudadoTotal());
            ps.setInt(11, contenido.getArtista().getId());

            if (contenido instanceof Cancion) {
                Cancion c = (Cancion) contenido; 
                ps.setString(12, c.getLetra()); 
                ps.setInt(13, c.isEsSingle() ? 1 : 0); 
                ps.setNull(14, java.sql.Types.INTEGER); 
                ps.setString(15, c.getInvitado()); 
                ps.setNull(16, java.sql.Types.INTEGER); 
                ps.setNull(17, java.sql.Types.VARCHAR); 
                ps.setNull(18, java.sql.Types.INTEGER); 
                ps.setNull(19, java.sql.Types.VARCHAR); 
                ps.setNull(20, java.sql.Types.VARCHAR); 
                ps.setNull(21, java.sql.Types.INTEGER);
            } else if (contenido instanceof EpisodioPodcast) {
                EpisodioPodcast ep = (EpisodioPodcast) contenido; 
                ps.setNull(12, java.sql.Types.VARCHAR); 
                ps.setNull(13, java.sql.Types.INTEGER); 
                ps.setNull(14, java.sql.Types.INTEGER); 
                ps.setString(15, ep.getInvitado()); 
                ps.setInt(16, ep.getNumeroEpisodio()); 
                ps.setString(17, ep.getResumen()); 
                ps.setNull(18, java.sql.Types.INTEGER); 
                ps.setNull(19, java.sql.Types.VARCHAR); 
                ps.setNull(20, java.sql.Types.VARCHAR); 
                ps.setNull(21, java.sql.Types.INTEGER);
            } else if (contenido instanceof Audiolibro) {
                Audiolibro a = (Audiolibro) contenido; 
                ps.setNull(12, java.sql.Types.VARCHAR); 
                ps.setNull(13, java.sql.Types.INTEGER); 
                ps.setNull(14, java.sql.Types.INTEGER); 
                ps.setNull(15, java.sql.Types.VARCHAR); 
                ps.setNull(16, java.sql.Types.INTEGER); 
                ps.setNull(17, java.sql.Types.VARCHAR); 
                ps.setNull(18, java.sql.Types.INTEGER); 
                ps.setString(19, a.getNarrador()); 
                ps.setString(20, a.getSinopsis());
                ps.setInt(21, a.getCantCapitulos());
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contenido.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al guardar el contenido en la base de datos", e);
        }
    }

    /**{@inheritDoc}
     *Ejecuta una consulta SELECT sobre la tabla CONTENIDO filtrando por su clave primaria id
     @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public Contenido buscarPorId(int id) {
        String sql = "SELECT * FROM CONTENIDO WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearContenido(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar contenido por ID", e);
        }
        return null;
    }

    /**
     * {@inheritDoc} 
     * Ejecuta una consulta SELECT sobre la tabla CONTENIDO filtrando por nombre e id_creador
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public Contenido buscarPorNombreYCreador(String nombre, int idCreador) {
        String sql = "SELECT * FROM CONTENIDO WHERE nombre = ? AND id_usuario = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, idCreador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearContenido(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar contenido por nombre y creador", e);
        }
        return null;
    }

    /**
     * {@inheritDoc} 
     * Ejecuta una consulta SELECT con JOIN o subconsulta sobre la tabla REPRODUCCION para obtener el contenido más reproducido
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public Contenido obtenerMasReproducido() {
        String sql = "SELECT * FROM CONTENIDO ORDER BY cant_reproducciones DESC LIMIT 1";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return mapearContenido(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el contenido más reproducido", e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT sobre la tabla CONTENIDO filtrando por la columna genero
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public List<Contenido> listarPorGenero(String genero) {
        String sql = "SELECT * FROM CONTENIDO WHERE UPPER(genero) = UPPER(?)";
        List<Contenido> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, genero);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearContenido(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar contenidos por género", e);
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT completa sobre la tabla CONTENIDO.
     * 
     * @throws DAOException Si ocurre un error de lectura en la base de datos.
     */
    @Override
    public List<Contenido> listarTodos() {
        String sql = "SELECT * FROM CONTENIDO";
        List<Contenido> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearContenido(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar todo el catálogo", e);
        }
        return lista;
    }

    /**
     * Traduce una fila relacional del ResultSet a una subclase concreta de Contenido (Cancion, EpisodioPodcast o Audiolibro)
     * 
     * @param rs Objeto {@link ResultSet} posicionado en la fila actual a mapear
     * @return Instancia concreta de {@link Contenido} con todos sus atributos reconstruidos
     * @throws SQLException Si ocurre un error al leer las columnas de SQLite
     */
    private Contenido mapearContenido(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        int duracion = rs.getInt("duracion_segundos");
        String tipo = rs.getString("tipo"); 

        // Convertir el texto de la BD ("ROCK", "POP") a tu Enum de Java
        String generoStr = rs.getString("genero"); 
        Genero genero = Genero.valueOf(generoStr.toUpperCase());

        int idUsuario = rs.getInt("id_usuario"); 
        UsuarioDAO usuarioDAO = Factory.getUsuarioDAO(); 
        UsuarioCreador creadorReal = (UsuarioCreador) usuarioDAO.buscarPorId(idUsuario);
        
        Contenido c; 
        if ("PODCAST".equalsIgnoreCase(tipo)) { 
            c = new EpisodioPodcast( nombre, duracion, genero, creadorReal, 
                rs.getString("resumen"), rs.getString("invitado"), rs.getInt("numero_episodio") ); 
        } else if ("AUDIOLIBRO".equalsIgnoreCase(tipo)) { 
            c = new Audiolibro( nombre, duracion, genero, creadorReal, rs.getString("narrador"), 
            rs.getString("sinopsis"), rs.getInt("cant_capitulos") ); 
        } else { 
            c = new Cancion( nombre, duracion, genero, creadorReal, rs.getString("letra"), 
            rs.getString("invitado"), rs.getInt("es_single") == 1 ); 
        }

        c.setId(id);
        c.setCantMeGusta(rs.getInt("cant_meGusta"));
        c.setRecaudadoTotal(rs.getDouble("recaudado"));
        c.setReproduccionesMujeres(rs.getInt("reproducciones_mujeres")); 
        c.setReproduccionesHombres(rs.getInt("reproducciones_hombres"));
        c.setReproducciones(rs.getInt("cant_reproducciones"));
        c.setFechaLanzamiento(LocalDate.parse(rs.getString("fecha_lanzamiento")));
        return c;
    }
}