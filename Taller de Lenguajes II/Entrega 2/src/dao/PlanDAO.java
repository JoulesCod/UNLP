package dao;
import java.util.List; 
import modelo.Plan; 

/**
 * Interfaz de acceso a datos para la entidad Plan.
 */
public interface PlanDAO { 
    /**
     * Busca un plan por su identificador único
     * @param id ID del plan.
     * @return Objeto Plan o null si no existe
     */
    Plan buscarPorId(int id); 

    /**
     * Devuelve la lista de todos los planes disponibles en la plataforma
     * @return Lista de planes (Gratuito, Individual, Familiar, etc.)
     */
    List<Plan> listarTodos();
}
