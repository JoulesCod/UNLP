package dao;

import dao.implJDBC.*;

/**
 * Fábrica de DAOs para desacoplar los controladores de las implementaciones JDBC concretas.
 */
public class Factory {

    /**
     * Provee la instancia concreta para el acceso a datos de Usuarios
     * 
     * @return Instancia de {@link UsuarioDAOjdbc}
     */
    public static UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOjdbc();
    }

    /**
     * Provee la instancia concreta para el acceso a datos de Contenidos
     * 
     * @return Instancia de {@link ContenidoDAOjdbc}
     */
    public static ContenidoDAO getContenidoDAO() {
        return new ContenidoDAOjdbc();
    }

    /**
     * Provee la instancia concreta para el acceso a datos de Playlists. 
     * @return Instancia de {@link PlaylistDAOjdbc}
     */
    public static PlaylistDAO getPlaylistDAO() {
        return new PlaylistDAOjdbc();
    }

    /**
     * Provee la instancia concreta para el acceso a datos de Álbumes
     * 
     * @return Instancia de {@link AlbumDAOjdbc}
     */
    public static AlbumDAO getAlbumDAO() {
        return new AlbumDAOjdbc();
    }

    /**
     * Provee la instancia concreta para el acceso a datos de Podcasts
     * 
     * @return Instancia de {@link PodcastDAOjdbc}
     */
    public static PodcastDAO getPodcastDAO() {
        return new PodcastDAOjdbc();
    }

    /**
     * Provee la instancia concreta para el acceso a datos de Planes de suscripción
     * @return Instancia de {@link PlanDAOjdbc}
     */
    public static PlanDAO getPlanDAO() {
        return new PlanDAOjdbc();
    }
}