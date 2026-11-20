package dao.implJDBC;

import conexion.MyConnection;
import dao.DAOException;
import dao.PlanDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

/**
 * Implementación concreta en JDBC de la interfaz {@link PlanDAO}
 * Administra la consulta y persistencia de los planes de suscripción 
 */
public class PlanDAOjdbc implements PlanDAO {

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT sobre la tabla PLAN filtrando por su clave primaria id.
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public Plan buscarPorId(int id) {
        String sql = "SELECT * FROM PLAN WHERE id = ?";
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo"); 
                    double precioMensual = rs.getDouble("precio_mensual"); 
                    if (id == 1 || "GRATUITO".equalsIgnoreCase(tipo)) { 
                        return new PlanGratuito(); 
                    } else { 
                        return new PlanPago(tipo, precioMensual); 
                    } 
                } 
            } 
        } catch (SQLException e) { 
            throw new DAOException("Error al buscar plan por ID", e); 
        } 
        return new PlanGratuito(); // Plan por defecto si el ID no existe en la BD 
    }

    /**
     * {@inheritDoc}
     * Ejecuta una consulta SELECT completa sobre la tabla PLAN. 
     * @throws DAOException Si ocurre un error de lectura en la BDD
     */
    @Override
    public List<Plan> listarTodos() {
        String sql = "SELECT * FROM PLAN";
        List<Plan> lista = new ArrayList<>();
        try (Connection con = MyConnection.getCon();
             PreparedStatement ps = con.prepareStatement(sql))  {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tipo = rs.getString("tipo"); 
                    double precioMensual = rs.getDouble("precio_mensual"); // Si el ID es 1 o el tipo es Gratuito, devuelve PlanGratuito 
                    if ( "GRATUITO".equalsIgnoreCase(tipo)) { 
                        lista.add(new PlanGratuito()); 
                    } else { // Si es pago, usa el constructor de PlanPago con los datos de la BD 
                        lista.add(new PlanPago(tipo, precioMensual)); 
                    } 
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar los planes", e);
        }
        if (lista.isEmpty()) {
            lista.add(new PlanGratuito());
        }
        return lista;
    }
}