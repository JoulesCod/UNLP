package conexion; 

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de gestionar la conexión única a la base de datos SQLite 
 * mediante el patrón de diseño Singleton 
 * 
 * Mantiene una única instancia activa de {@link Connection} durante todo 
 * el ciclo de vida de la aplicación para optimizar el acceso a los datos 
 */
public class MyConnection { 

    private static final String URL = "jdbc:sqlite:plataforma.db"; 
    private static Connection con = null; 
    
    /**
     * Constructor privado para evitar la instanciación de la clase 'new'
     * Garantiza el cumplimiento del patrón Singleton 
     */
    private MyConnection() { } 

    /**
     * Devuelve la conexión única y activa a la base de datos SQLite, activando la verificación de claves foráneas
     *
     * @return Instancia activa de {@link Connection}
     * @throws RuntimeException Si ocurre un error al establecer la conexión con SQLite
     */
    public static Connection getCon() { 
        try { 
            if (con == null || con.isClosed()) { 
                con = DriverManager.getConnection(URL); 
                try (Statement stmt = con.createStatement()) { 
                    stmt.execute("PRAGMA foreign_keys = ON;"); 
                }
            } 
        } catch (SQLException e) { 
            throw new RuntimeException("No se pudo conectar a BDD: " + e.getMessage()); 
        } 
        return con; 
    } 

    /**
     * Cierra la conexión global a la base de datos al finalizar la ejecución del sistema 
     * Debe ser invocado antes de salir de la aplicación para liberar los recursos
     */
    public static void cerrarConexion() { 
        if (con != null) { 
            try { 
                con.close(); 
            } catch (SQLException e) { 
                System.err.println("Error al cerrar la conexión: " + e.getMessage()); 
            } 
        } 
    } 
}
