package clases;

/**
 * Proporciona métodos de utilidad estáticos para simular las acciones de control
 * del reproductor multimedia de la plataforma (reproducir, pausar, adelantar).
 */

public class Reproductor {

    /**
     * Simula el avance en la barra de tiempo de un {@link Contenido}. 
     * @param segundos Cantidad de segundos a adelantar. 
     * @param c {@link Contenido} en reproducción. 
     */
    public static void adelantar(int segundos, Contenido c){
        if (c!=null && segundos>0){
            System.out.println("Adelantando "+c.getNombre()+", "+segundos+" segundos.");
        }
    }

    /**
     * Simula la pausa en la reproducción de un {@link Contenido}.
     * @param c {@link Contenido} a pausar.
     */
    public static void pausar(Contenido c){ 
        if (c!=null){
            System.out.println("Pausando "+c.getNombre());
        }
    }

    /**
     * Ejecuta la reproducción de un {@link Contenido}, evalúa la emisión de publicidad 
     * e incrementa las métricas globales del contenido. 
     * @param c {@link Contenido} a reproducir. 
     * @param p {@link Plan} de suscripción del usuario. 
     * @param cantReproducciones Cantidad acumulada de reproducciones en el historial del usuario.
     */
    public static void reproducirContenido(Contenido c, Plan p, int cantReproducciones){
        if (c!=null){
            if (p!=null && p.tienePublicidad()){
                if (cantReproducciones>0 && (cantReproducciones % 10 == 0)){
                    reproducirPublicidad();
                }
            }
            c.reproducirContenido();
            c.incrementarReproducciones();
        }
    }

    /**
     * Emite el anuncio publicitario correspondiente para planes gratuitos. 
     */
    public static void reproducirPublicidad(){
        System.out.println("📢 [PUBLICIDAD] ¡Suscribite al Plan Premium para escuchar sin anuncios!");
    }
}
