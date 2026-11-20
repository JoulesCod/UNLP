package dao;
import modelo.UsuarioConsumidor;
import java.util.List;

/**
 * Interfaz de acceso a datos para la entidad Usuario. 
 * Administra la persistencia de usuarios consumidores y creadores. 
 */
public interface UsuarioDAO {
    
    /**
     * Inserta un nuevo usuario (consumidor o creador) en la base de datos. 
     * @param usuario Objeto usuario a guardar.
     */
    void guardar(UsuarioConsumidor u);

    /**
     * Busca un usuario por su dirección de correo 
     * Utilizado para validar la regla de negocio de email único 
     * @param email Dirección de email a verificar
     * @return El usuario con ese email o null si está disponible
     */ 
    UsuarioConsumidor buscarPorEmail(String email); 

    /**
     * Busca un usuario por su id
     * @param id ID del usuario
     * @return El objeto UsuarioConsumidor encontrado o null si no existe. 
     */
    UsuarioConsumidor buscarPorId(int id); 
    
    /**
     * Busca un usuario por su nombre de usuario
     * Utilizado para validar la regla de negocio de nombre de usuario único 
     * @param nombreUsuario Nombre de usuario a verificar.
     * @return El usuario con dicho nombre o null si está disponible.
     */
     UsuarioConsumidor buscarPorNombreUsuario(String nombreUsuario);

     /**
      * Devuelve la lista completa de todos los usuarios registrados. 
      * @return Lista de usuarios de la plataforma. 
      */
      List<UsuarioConsumidor> listarTodos();
}
