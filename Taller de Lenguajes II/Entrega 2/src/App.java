import conexion.DataBaseInitializer;
import conexion.MyConnection;
import java.sql.SQLException;
import vista.MenuPrincipal; 

/**
 * Clase principal y punto de entrada de la aplicación de streaming.
 * Inicializa la BDD SQLite, lanza la interfaz de usuario "Vista"
 * y garantiza el cierre seguro de la conexión Singleton al finalizar la ejecución.
 */
public class App { 

    /**
     * Método principal que controla el flujo de inicio de la aplicación.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) { 
        System.out.println(" Iniciando la aplicación..."); 

        try { 
            DataBaseInitializer.creacionDeTablasEnBD(MyConnection.getCon()); 
            System.out.println(" Base de datos y tablas creadas y verificadas con éxito."); 
        
        } catch (SQLException e) { 
            System.err.println(" Error al inicializar la base de datos: " + e.getMessage()); 
            e.printStackTrace(); // Imprime en la consola radiografía completa del error
            return; 
        }

        try {
            System.out.println("El sistema está listo para recibir operaciones."); 

            MenuPrincipal menu = new MenuPrincipal(); 
            menu.iniciar();

        } finally {
            MyConnection.cerrarConexion(); 
            System.out.println(" Conexión a la base de datos cerrada correctamente."); 
        }
    } 
}
