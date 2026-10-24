package clases;

import java.lang.reflect.Constructor;

/**
 * Clase abstracta que define la estructura base para los planes de suscripción.
 * Permite consultar si el nivel de suscripción admite descargas offline y si incluye anuncios.
 */
public abstract class Plan {
    private String nombre;
    private double precioMensual;

    /**Constructor base para un plan de suscripción. 
     * @param nombre Nombre descriptivo del plan.
     * @param precioMensual Tarifa mensual base.
     */
    public Plan(String nombre, double precioMensual){
        this.nombre= nombre;
        this.precioMensual= precioMensual;
    }

    /**
     * Determina si el plan habilita descargas offline. 
     * @return {@code true} si permite descargas, {@code false} en caso contrario. 
     */
    public abstract boolean puedeDescargar();

    /**
     * Indica si la reproducción con este plan emite anuncios publicitarios.
     * @return {@code true} si incluye publicidad, {@code false} si es libre de anuncios. 
     */
    public abstract boolean tienePublicidad();

    @Override
    public String toString() { 
        return getNombre() + " ($" + getPrecioMensual() + " al mes)"; 
    }

    // ---Getters y setters ---
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecioMensual() {
        return this.precioMensual;
    }
    public void setPrecioMensual(double precioMensual) {
        this.precioMensual = precioMensual;
    }

}
