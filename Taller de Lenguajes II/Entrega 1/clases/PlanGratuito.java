package clases;

public class PlanGratuito extends Plan{
    
    public PlanGratuito(){
        super("Plan Gratuito", 0.0);
    }

    @Override
    public boolean puedeDescargar(){
        return false;
    }

    @Override
    public boolean tienePublicidad(){
        return true;
    }
}
