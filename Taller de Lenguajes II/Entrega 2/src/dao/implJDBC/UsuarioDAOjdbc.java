package dao.implJDBC;

import conexion.MyConnection;
import dao.DAOException;
import dao.Factory;
import dao.PlanDAO;
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
 * Implementación concreta en JDBC de la interfaz UsuarioDAO para SQLite {@link UsuarioDAO}
 * Administra la persistencia de usuarios en la base de datos SQLite
 */
public class UsuarioDAOjdbc implements UsuarioDAO {

    /**
     * Persiste un nuevo usuario (Consumidor o Creador) en la tabla USUARIO de SQLite
     * 
     * @param u Objeto UsuarioConsumidor a guardar
     * @throws DAOException Si ocurre un error al insertar en la base de datos
     */
    @Override
    public void guardar(UsuarioConsumidor u) {
        String sql = "INSERT INTO USUARIO (email, nombre, apellido, nombre_usuario, fecha_inicio, tipo, plan_id) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MyConnection.getCon();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getEmail()); 
            ps.setString(2, u.getNombre()); 
            ps.setString(3, u.getApellido()); 
            ps.setString(4, u.getNombreUsuario()); 
            ps.setString(5, u.getFechaInicioCuenta().toString()); 
            String tipo = (u instanceof UsuarioCreador) ? "CREADOR" : "CONSUMIDOR"; 
            ps.setString(6, tipo); 

            // Guardamos ID 2 si es PlanPago, o ID 1 si es PlanGratuito / default 
            int planId = (u.getPlan() != null && u.getPlan() instanceof PlanPago) ? 2 : 1;
            ps.setInt(7, planId); //se asume que siempre viene PlanGratuito default
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al guardar el usuario en la base de datos", e);
        }
    }

    /**
     * {@inheritDoc} 
     * Ejecuta una consulta SELECT filtrando por la columna email en tabla USUARIO. 
     * 
     * @throws DAOException Si ocurre un error de lectura en la base de datos.
     */
    @Override
    public UsuarioConsumidor buscarPorEmail(String email) {
        String sql = "SELECT * FROM USUARIO WHERE email = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar usuario por email", e);
        }
        return null;
    }

    /**
     * {@inheritDoc} 
     * Ejecuta una consulta SELECT filtrando por la clave primaria id en la tabla USUARIO
     * 
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public UsuarioConsumidor buscarPorId(int id) {
        String sql = "SELECT * FROM USUARIO WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar usuario por ID", e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT filtrando por la columna nombre_usuario en la tabla USUARIO
     * 
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public UsuarioConsumidor buscarPorNombreUsuario(String nombreUsuario) {
        String sql = "SELECT * FROM USUARIO WHERE nombre_usuario = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar usuario por nombre de usuario", e);
        }
        return null;
    }

    /**{@inheritDoc}
     * Ejecuta una consulta SELECT completa sobre la tabla USUARIO.
     * 
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public List<UsuarioConsumidor> listarTodos() {
        String sql = "SELECT * FROM USUARIO";
        List<UsuarioConsumidor> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar todos los usuarios", e);
        }
        return lista;
    }

    /**
     * Traduce una fila relacional del ResultSet a una instancia concreta de UsuarioConsumidor o UsuarioCreador
     * 
     * @param rs Objeto {@link ResultSet} posicionado en la fila actual a mapear
     * @return Instancia mapeada de {@link UsuarioConsumidor} o {@link UsuarioCreador} 
     * @throws SQLException Si ocurre un error al extraer los datos del ResultSet
     */
    private UsuarioConsumidor mapearUsuario(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String nombreUsuario = rs.getString("nombre_usuario");
        String nombre = rs.getString("nombre"); 
        String apellido = rs.getString("apellido");
        String tipo = rs.getString("tipo");
        int planId = rs.getInt("plan_id");

        // Instanciamos usando los 4 parámetros 
        UsuarioConsumidor u = "CREADOR".equalsIgnoreCase(tipo)
                ? new UsuarioCreador(nombreUsuario, nombre, apellido, email)
                : new UsuarioConsumidor(nombreUsuario, nombre, apellido, email);
        u.setId(id);
        PlanDAO planDAO = Factory.getPlanDAO(); 
        u.setPlan(planDAO.buscarPorId(planId));

        return u;
    }
}