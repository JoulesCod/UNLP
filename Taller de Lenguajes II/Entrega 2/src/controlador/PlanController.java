package controlador;

import dao.Factory;
import dao.PlanDAO;
import modelo.Plan;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Controlador encargado de la consulta de planes de suscripción.
 */
public class PlanController {

    private PlanDAO planDAO;

    /**
     * Constructor que inicializa PlanDAO a través de la fábrica Factory
    */
    public PlanController() {
        this.planDAO = Factory.getPlanDAO();
    }

    /**
     * Consulta un plan de suscripción por su identificador
     * 
     * @param id Identificador único del plan
     * @return Objeto Plan encontrado
     *  @throws IllegalArgumentException Si el ID es menor o igual a 0 o no existe
     */
    public Plan buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del plan debe ser mayor a 0.");
        }
        Plan p = planDAO.buscarPorId(id);
        if (p == null) {
            throw new IllegalArgumentException("No existe ningún plan con el ID: " + id);
        }
        return p;
    }

    /**
     * Retorna todos los planes de suscripción registrados
     * 
     * @return Lista de planes disponibles
     */
    public List<Plan> listarTodos() {
        return planDAO.listarTodos();
    }
}
