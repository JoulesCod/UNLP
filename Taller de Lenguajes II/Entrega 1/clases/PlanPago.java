package clases;
import java.time.LocalDateTime;

/**
 * Representa la suscripción paga/premium de la plataforma.
 * Permite descargas offline, no emite publicidad y gestiona la vigencia del servicio.
 */
public class PlanPago extends Plan{
    private String tipo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaVencimiento;

    /**
     * Instancia un nuevo PlanPago especificando tipo y precio. 
     * @param tipo Modalidad del plan (ej: "Individual", "Familiar"). 
     * @param precioMensual Tarifa mensual de la suscripción. 
     */
    public PlanPago(String tipo, double precioMensual){
        super("Plan premium "+tipo, precioMensual);
        this.tipo= tipo;
        this.fechaInicio= LocalDateTime.now();
        this.fechaVencimiento= LocalDateTime.now().plusMonths(1);
    }

    @Override
    public boolean puedeDescargar(){
        return true;
    }

    @Override
    public boolean tienePublicidad(){
        return false;
    }

    // --- Getters y setters ---
    public void setTipo(String tipo){
        this.tipo= tipo;
    }
    public String getTipo(){
        return this.tipo;
    }
    public LocalDateTime getFechaInicio(){
        return this.fechaInicio;
    }
    public LocalDateTime getFechaVencimiento(){
        return this.fechaVencimiento;
    }

}
