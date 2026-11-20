package controlador;

import dao.Factory;
import dao.UsuarioDAO;
import java.util.List;
import modelo.UsuarioConsumidor;
import modelo.UsuarioCreador;

/**
 * Controlador que gestiona la lógica de negocio y validaciones para los usuarios.
 * Se comunica con la capa de datos a través de UsuarioDAO.
 */
public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    /**
     * Constructor por defecto
     * Inicializa la instancia del DAO de usuarios mediante la fábrica abstracta {@link Factory}.
     */
    public UsuarioController() {
        // Obtenemos la instancia del DAO a través de la fábrica abstracta
        this.usuarioDAO = Factory.getUsuarioDAO();
    }

    /**
     * Registra un nuevo Usuario Consumidor en el sistema tras validar las reglas de negocio
     * 
     * @param nombre Nombre del usuario consumidor
     * @param apellido Apellido del usuario consumidor
     * @param nombreUsuario Nombre de usuario (username) único
     * @param email Correo electrónico único.
     * @throws IllegalArgumentException Si algún dato está vacío, contiene números o ya existe en la BD
     */
    public void registrarConsumidor(String nombre, String apellido, String nombreUsuario, String email) { 
        validarDatosBasicos(nombre, apellido, nombreUsuario, email); 
        UsuarioConsumidor nuevoConsumidor = new UsuarioConsumidor(nombreUsuario.trim(), nombre.trim(), apellido.trim(), email.trim()); 
        usuarioDAO.guardar(nuevoConsumidor); 
    } 
    
    /**
     * Registra un nuevo Usuario Creador en el sistema tras validar las reglas de negocio.
     * 
     * @param nombre Nombre del usuario creador
     * @param apellido Apellido del usuario creador
     * @param nombreUsuario Nombre de usuario (username) único
     * @param email Correo electrónico único.
     * @throws IllegalArgumentException Si algún dato está vacío, contiene números o ya existe en la BD.
     */
     public void registrarCreador(String nombre, String apellido, String nombreUsuario, String email) { 
        validarDatosBasicos(nombre, apellido, nombreUsuario, email); 
        UsuarioCreador nuevoCreador = new UsuarioCreador(nombreUsuario.trim(), nombre.trim(), apellido.trim(), email.trim()); 
        usuarioDAO.guardar(nuevoCreador); 
    }

    /**
     * Busca un usuario registrado en el sistema por su identificador único 
     * 
     * @param id Identificador numérico del usuario
     * @return El {@link UsuarioConsumidor} o {@link UsuarioCreador} correspondiente, o {@code null} si no existe
     * @throws IllegalArgumentException Si el ID ingresado es menor o igual a cero
     */
    public UsuarioConsumidor buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de usuario debe ser mayor a cero.");
        }
        return usuarioDAO.buscarPorId(id);
    }

    /**
     * Busca un usuario registrado en el sistema a partir de su nombre de usuario
     * 
     * @param nombreUsuario Nombre de usuario (username) a consultar
     * @return El {@link UsuarioConsumidor} o {@link UsuarioCreador} correspondiente, o {@code null} si no se encuentra
     * @throws IllegalArgumentException Si {@code nombreUsuario} es nulo o está en blanco
     */
    public UsuarioConsumidor buscarPorUsername(String nombreUsuario) { 
        if (nombreUsuario == null || nombreUsuario.isBlank()) { 
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío."); 
        } 
        return usuarioDAO.buscarPorNombreUsuario(nombreUsuario.trim()); 
    }

    /**
     * Retorna la lista completa de usuarios registrados en la plataforma.
     * 
     * @return Lista de objetos {@link UsuarioConsumidor} (incluyendo creadores)
     */
    public List<UsuarioConsumidor> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    /**
     * Valida las reglas de negocio básicas para el registro de un usuario
     * Verifica que los campos no estén vacíos, que no haya dígitos en nombre y apellido, 
     * el formato del email y la unicidad de email y username en la base de datos.
     * 
     * @param nombre Nombre a validar.
     * @param apellido Apellido a validar
     * @param nombreUsuario Username a validar
     * @param email Correo electrónico a validar
     * @throws IllegalArgumentException Si alguna validación falla
     */
    private void validarDatosBasicos(String nombre, String apellido, String nombreUsuario, String email) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío.");
        }

        if (nombre.matches(".*\\d.*") || apellido.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre y el apellido no pueden tener dígitos numéricos.");
        }

        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
        }

        if (usuarioDAO.buscarPorEmail(email.trim()) != null) {
            throw new IllegalArgumentException("El email '" + email + "' ya se encuentra registrado en la plataforma.");
        }

        if (usuarioDAO.buscarPorNombreUsuario(nombreUsuario.trim()) != null) {
            throw new IllegalArgumentException("El nombre de usuario '" + nombreUsuario + "' ya está en uso.");
        }
    }
}
