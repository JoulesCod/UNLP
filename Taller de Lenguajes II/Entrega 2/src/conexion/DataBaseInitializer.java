package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de la inicialización y estructuración de la base de datos SQLite.
 * Contiene el conjunto de sentencias DDL necesarias para crear todas las tablas, 
 * relaciones (claves foráneas) e índices de la plataforma si no existen previamente.
 */
public class DataBaseInitializer {

    /**
     * Ejecuta las sentencias DDL para crear las tablas e índices en SQLite y carga los datos iniciales de los planes
     * 
     * @param connection Conexión activa a la base de datos SQLite
     * @throws SQLException Si ocurre un error al ejecutar las sentencias DDL o DML iniciales
     */
    public static void creacionDeTablasEnBD(Connection connection) throws SQLException {
        String[] ddl = {
            """
            CREATE TABLE IF NOT EXISTS PLAN (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                tipo TEXT NOT NULL,
                precio_mensual REAL NOT NULL CHECK (precio_mensual >=0)
            )
            """,

            """
            CREATE TABLE IF NOT EXISTS USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                email TEXT NOT NULL UNIQUE,
                nombre TEXT NOT NULL,
                apellido TEXT NOT NULL,
                nombre_usuario TEXT NOT NULL UNIQUE,
                fecha_inicio TEXT NOT NULL,
                tipo TEXT NOT NULL,
                plan_id INTEGER NOT NULL , 
                FOREIGN KEY (plan_id) REFERENCES PLAN(id)
            )""",
            
            """
            CREATE TABLE IF NOT EXISTS ALBUM (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha_lanzamiento TEXT NOT NULL,
                nombre TEXT NOT NULL
            )
            """,

            """
            CREATE TABLE IF NOT EXISTS PODCAST (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha_lanzamiento TEXT NOT NULL,
                nombre TEXT NOT NULL
            )
            """,

            """
            CREATE TABLE IF NOT EXISTS CONTENIDO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                genero TEXT NOT NULL,
                tipo TEXT NOT NULL,
                duracion_segundos INTEGER NOT NULL CHECK (duracion_segundos > 0),
                cant_reproducciones INTEGER NOT NULL DEFAULT 0 CHECK (cant_reproducciones >= 0), 
                cant_meGusta INTEGER NOT NULL DEFAULT 0 CHECK (cant_meGusta >= 0),
                fecha_lanzamiento TEXT NOT NULL,
                reproducciones_mujeres INTEGER NOT NULL DEFAULT 0 CHECK (reproducciones_mujeres >= 0), 
                reproducciones_hombres INTEGER NOT NULL DEFAULT 0 CHECK (reproducciones_hombres >= 0),
                recaudado REAL NOT NULL DEFAULT 0 CHECK (recaudado >= 0),
                id_usuario INTEGER NOT NULL,
                letra TEXT, 
                es_single INTEGER,  
                id_album INTEGER,

                invitado TEXT, 
                numero_episodio INTEGER, 
                resumen TEXT, 
                id_podcast INTEGER,
                narrador TEXT, 
                sinopsis TEXT, 
                cant_capitulos INTEGER, 
                FOREIGN KEY (id_usuario) REFERENCES USUARIO(id) ON DELETE CASCADE,
                FOREIGN KEY (id_album) REFERENCES ALBUM(id) ON DELETE SET NULL, 
                FOREIGN KEY (id_podcast) REFERENCES PODCAST(id) ON DELETE CASCADE 
            )
            """,

            """
            CREATE TABLE IF NOT EXISTS REPRODUCCION ( 
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                fecha_hora TEXT NOT NULL,
                id_usuario INTEGER NOT NULL,
                id_contenido INTEGER NOT NULL,
                FOREIGN KEY (id_usuario) REFERENCES USUARIO(id) ON DELETE CASCADE,
                FOREIGN KEY (id_contenido) REFERENCES CONTENIDO(id) ON DELETE CASCADE
            )
            """,

            """
                CREATE TABLE IF NOT EXISTS PLAYLIST (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                id_usuario INTEGER NOT NULL,
                FOREIGN KEY (id_usuario) REFERENCES USUARIO(id) ON DELETE CASCADE
            )""",

            """
            CREATE TABLE IF NOT EXISTS PLAYLIST_X_CONTENIDO (
                id_playlist INTEGER NOT NULL, 
                id_contenido INTEGER NOT NULL, 
                posicion INTEGER NOT NULL, 
                PRIMARY KEY (id_playlist, id_contenido), 
                FOREIGN KEY (id_playlist) REFERENCES PLAYLIST(id) ON DELETE CASCADE, 
                FOREIGN KEY (id_contenido) REFERENCES CONTENIDO(id) ON DELETE CASCADE 
                )
            """,

            """
            CREATE INDEX IF NOT EXISTS IDX_PLAYLIST_CONTENIDO 
                ON PLAYLIST_X_CONTENIDO (id_contenido)
            """
        };

        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        try (Statement stmt = connection.createStatement()) {
            for (String sql : ddl) {
                stmt.executeUpdate(sql);
            }
            stmt.execute("INSERT OR IGNORE INTO PLAN (id, nombre, tipo, precio_mensual) VALUES (1, 'Gratuito', 'GRATUITO', 0.0)");
            stmt.execute("INSERT OR IGNORE INTO PLAN (id, nombre, tipo, precio_mensual) VALUES (2, 'Pago', 'PAGO', 1500.0)");
            
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e; 
        } finally {
            connection.setAutoCommit(autoCommit); 
        }
    }
}