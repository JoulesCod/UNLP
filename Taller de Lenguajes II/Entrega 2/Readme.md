# Taller de Lenguajes II — Facultad de Informática (UNLP)
## Trabajo Final Integrador — Entregable 2


## Descripción
Este directorio contiene la implementación del **Entregable 2 del Trabajo Final Integrador (TFI)** 
El sistema evoluciona la plataforma de streaming de audio desarrollada en el 1er entregable, incorporando una **capa de persistencia relacional con SQLite y JDBC**, reorganizando el código bajo el patrón arquitectónico **MVC (Modelo - Vista - Controlador)** y aplicando el patrón de acceso a datos **DAO (Data Access Object)** con fábrica y control de excepciones.

---

## Arquitectura de Software y Patrones de Diseño

El sistema está estructurado bajo una arquitectura de capas bien delimitada para garantizar el bajo acoplamiento, la alta cohesión y la mantenibilidad del código:

1. **Patrón MVC (Modelo - Vista - Controlador):**
   * **`modelo`**: Contiene únicamente las entidades del dominio (`UsuarioConsumidor`, `UsuarioCreador`, `Contenido`, `Cancion`, `EpisodioPodcast`, `Audiolibro`, `Album`, `Playlist`, `Plan`, `Reproduccion`). Las clases del modelo están totalmente desvinculadas de la entrada/salida por consola y de la tecnología de persistencia.
   * **`controlador`**: Clases encargadas de orquestar la lógica de negocio (`UsuarioController`, `ContenidoController`, `AlbumController`, `PlaylistController`, `ReproduccionController`, `PlanController`). Reciben las peticiones de la vista, aplican las validaciones de negocio y coordinan con la capa DAO. No contienen llamadas a `System.out.println` ni `Scanner`.
   * **`vista`**: Administra la interfaz de usuario interactiva por consola (`MenuPrincipal`) mediante `Scanner`. No accede a las tablas ni a los DAOs de forma directa; se comunica exclusivamente a través de los controladores.

2. **Patrón DAO (Data Access Object):**
   * Las interfaces (`UsuarioDAO`, `ContenidoDAO`, `AlbumDAO`, `PlaylistDAO`, `PlanDAO`, `ReproduccionDAO`, `PodcastDAO`) definen los contratos abstractos de persistencia con documentación **Javadoc**.
   * Las implementaciones concretas (`*DAOjdbc`) en `dao.implJDBC` encapsulan las sentencias SQL utilizando **`PreparedStatement`** para prevenir ataques de inyección SQL y optimizar el rendimiento.
   * La clase **`DAOException`** convierte las excepciones chequeadas de bajo nivel (`SQLException`) en excepciones no chequeadas (`RuntimeException`), evitando acoplar la arquitectura a JDBC.

3. **Patrón Factory (`Factory`):**
   * Provee instancias de los DAOs mediante métodos estáticos, permitiendo que los controladores dependan de las interfaces abstractas y no de las clases concretas de SQLite.

4. **Patrón Singleton y Control Transaccional (`conexion`):**
   * **`MyConnection`**: Mantiene una única instancia activa de `java.sql.Connection` a `plataforma.db` mediante el patrón Singleton con `DriverManager`, garantizando un cierre seguro de recursos mediante el método `cerrarConexion()`.
   * **`DataBaseInitializer`**: Ejecuta las sentencias DDL para la creación automática de tablas e índices dentro de una **transacción atómica** (`setAutoCommit(false)`, `commit()` y `rollback()`).

---

## Estructura del Proyecto (Packages y archivos)

```text
SoundStream/
├── .vscode/
│   └── settings.json                  <-- Configuración de bibliotecas referenciadas
├── lib/
│   └── sqlite-jdbc-3.53.4.0.jar          <-- Driver JDBC oficial para SQLite
├── src/
│   ├── App.java                      <-- Clase principal y ciclo de vida del sistema
│   │
│   ├── conexion/                     <-- Gestión de Conexión y DDL
│   │   ├── MyConnection.java         <-- Singleton para conexión JDBC
│   │   └── DataBaseInitializer.java  <-- Inicialización transaccional de la BDD
│   │
│   ├── modelo/                       <-- Clases de Dominio (POJOs limpios)
│   │   ├── UsuarioConsumidor.java
│   │   ├── UsuarioCreador.java
│   │   ├── Contenido.java            <-- Clase abstracta base
│   │   ├── Cancion.java
│   │   ├── EpisodioPodcast.java
│   │   ├── Audiolibro.java
│   │   ├── Album.java
│   │   ├── Podcast.java
│   │   ├── Playlist.java
│   │   ├── Biblioteca.java
│   │   ├── Plan.java, PlanGratuito.java, PlanPago.java
│   │   ├── Reproduccion.java
│   │   └── Genero.java               <-- Enum de categorías
│   │
│   ├── dao/                          <-- Capa de Persistencia (Interfaces)
│   │   ├── UsuarioDAO.java
│   │   ├── ContenidoDAO.java
│   │   ├── AlbumDAO.java
│   │   ├── PlaylistDAO.java
│   │   ├── PodcastDAO.java
│   │   ├── PlanDAO.java
│   │   ├── ReproduccionDAO.java
│   │   ├── DAOException.java         <-- Excepción de acceso a datos
│   │   ├── Factory.java              <-- Fábrica estática de DAOs
│   │   │
│   │   └── implJDBC/                 <-- Implementaciones SQL con SQLite
│   │       ├── UsuarioDAOjdbc.java
│   │       ├── ContenidoDAOjdbc.java
│   │       ├── AlbumDAOjdbc.java
│   │       ├── PlaylistDAOjdbc.java
│   │       ├── PodcastDAOjdbc.java
│   │       ├── PlanDAOjdbc.java
│   │       └── ReproduccionDAOjdbc.java
│   │
│   ├── controlador/                  <-- Lógica de Negocio y Validaciones
│   │   ├── UsuarioController.java
│   │   ├── ContenidoController.java
│   │   ├── AlbumController.java
│   │   ├── PlaylistController.java
│   │   ├── ReproduccionController.java
│   │   └── PlanController.java
│   │
│   └── vista/                        <-- Interfaz por Consola
│       └── MenuPrincipal.java        <-- Menú interactivo y Scanner
│
├── .gitignore                        <-- Excluye archivos *.db y archivos de audio
└── README.md                         <-- Documentación e informe del proyecto
```

---

## Diseño de Base de Datos y Mapeo Objeto-Relacional (ORM)

El esquema relacional fue diseñado siguiendo los principios de normalización y las reglas de transformación del modelo orientado a objetos a tablas SQL expuestas en la materia:

### 1. Mapeo de Herencia: *Single Table Inheritance*
Se utilizó la estrategia de **Tabla Única con Columna Discriminadora** para las jerarquías de `Contenido` (`CONTENIDO`) y `Usuario` (`USUARIO`):
* **Tabla `USUARIO`**: Almacena usuarios consumidores y creadores, diferenciados por la columna `tipo` (`CONSUMIDOR` / `CREADOR`).
* **Tabla `CONTENIDO`**: Agrupa canciones, podcasts y audiolibros. Utiliza la columna discriminadora `tipo` (`CANCION`, `PODCAST`, `AUDIOLIBRO`). Permite que consultas polimórficas del catálogo —como obtener la obra más reproducida o filtrar por género— se resuelvan en una única instrucción SQL sin realizar operaciones `JOIN` ni uniones complejas.

### 2. Mapeo de Relaciones N a N
* La relación entre `PLAYLIST` y `CONTENIDO` se mapea mediante la tabla intermedia **`PLAYLIST_X_CONTENIDO`**, con clave primaria compuesta `(id_playlist, id_contenido)` y la columna `posicion` para mantener el orden secuencial de reproducción.

### 3. Claves, Restricciones e Índices
* **Claves Primarias:** Definidas con `INTEGER PRIMARY KEY AUTOINCREMENT`. En el modelo Java, los atributos `id` son enteros asignados tras la inserción por el motor relacional.
* **Restricciones de Integridad:** Cláusulas `NOT NULL` en campos requeridos, `UNIQUE` en `email` y `nombre_usuario`, y restricciones `CHECK` para validar duraciones e importes mayores o iguales a cero.
* **Integridad Referencial:** Uso de `FOREIGN KEY` con cláusula `ON DELETE CASCADE` para limpiar de forma automática listas y registros dependientes cuando se elimina un registro padre.
* **Índices de Búsqueda:** Creación explícita del índice `IDX_PLAYLIST_CONTENIDO` sobre la columna `id_contenido` de la tabla intermedia para acelerar las búsquedas inversas.

---

## Investigación: Persistencia de Archivos de Audio en la Plataforma

Como parte de los requerimientos de investigación del **Entregable 2**, se analizaron las dos alternativas principales para el almacenamiento y la gestión de archivos multimedia en sistemas relacionales:

### Alternativa A: Almacenamiento directo en Base de Datos como Objeto Binario (`BLOB`)
Consiste en guardar el flujo de bytes del archivo comprimido de audio (`.mp3`, `.ogg`, `.wav`) en una columna de tipo `BLOB` dentro de la tabla relacional.

* **Ventajas:**
  * **Integridad Transaccional ACID:** Los metadatos de la canción y el archivo físico de audio se insertan o eliminan en la misma transacción.
  * **Unificación de Backups:** Un único archivo `.db` contiene la totalidad de la información de la plataforma.
* **Desventajas:**
  * **Saturación del Motor Relacional:** Mover grandes bloques binarios en consultas de catálogo penaliza severamente el uso de memoria caché y reduce la velocidad de respuesta en SQLite.
  * **Dificultad de Streaming por Fragmentos:** No permite realizar solicitudes por rangos (*HTTP Range Requests*) de manera eficiente para streaming continuo.
  * **Crecimiento Excesivo del Archivo de Base de Datos:** Dificulta el mantenimiento y la migración.

### Alternativa B: Almacenamiento en Sistema de Archivos y Persistencia de Ruta (`TEXT`)
Consiste en guardar los archivos físicos de audio en el sistema de archivos del servidor (o en un bucket de objetos multimedia) y almacenar únicamente la ruta relativa o URI en la base de datos como una columna `TEXT`.

* **Ventajas:**
  * **Alto Rendimiento:** La base de datos opera con registros ligeros de texto y números, manteniendo velocidades óptimas en búsquedas y filtrados.
  * **Soporte Nativo de Streaming:** El servidor o reproductor puede abrir flujos directos (`FileInputStream` o buffers de lectura) y transmitir por fragmentos.
  * **Escalabilidad Independiente:** Separa el almacenamiento de datos relacionales del servidor de recursos multimedia.
* **Desventajas:**
  * Requiere gestión explícita por parte del DAO para mantener sincronizada la ruta en la base de datos con el archivo físico en el disco.

### Justificación
Se adopta la **Alternativa B (Sistema de Archivos y Persistencia de Ruta)**. En la tabla `CONTENIDO` se incluye la columna con la ubicación del recurso, mientras que los archivos físicos se almacenan en el directorio del servidor. Esta carpeta, junto con el archivo `.db` de SQLite, se encuentra ignorada en el archivo **`.gitignore`** del proyecto para no sobrecargar el repositorio de control de versiones.

---

## Requerimientos Funcionales y Validaciones (Para un grupo de 2 Integrantes)

El sistema satisface la totalidad de los requisitos especificados para equipos de 2 integrantes:

* **Gestión y Registro de Usuarios:**
  * Registro de usuarios consumidores y creadores con asignación de plan.
  * **Validaciones:**
    * Rechazo de números en los campos de nombre y apellido (mediante expresiones regulares `.*\\d.*`).
    * Verificación de unicidad de email y nombre de usuario en la base de datos previa a la inserción.
    * Comprobación de campos obligatorios no vacíos e informe de errores por consola.
* **Publicación de Contenidos:**
  * Publicación de canciones, episodios de podcasts y audiolibros por parte de creadores registrados.
  * **Validaciones:**
    * Restricción estricta: los álbumes contienen exclusivamente música (`Cancion`).
    * Verificación de géneros válidos definidos en la enumeración `Genero`.
    * Control de duplicados para evitar que un creador publique dos obras con el mismo título.
* **Consultas por Consola:**
  * **Filtrado por género:** Consulta SQL para listar los contenidos pertenecientes a una categoría específica.
  * **Contenido más reproducido:** Consulta SQL sobre las métricas acumuladas para retornar la obra con mayor cantidad de reproducciones en el catálogo.

---

## Instalación y Ejecución

### Requisitos Previos
* **Java Development Kit (JDK):** Versión 17 o superior.
* **SQLite JDBC Driver:** Archivo `sqlite-jdbc-3.53.4.0.jar` ubicado en la carpeta `lib/`.

### Compilación desde CLI
```bash
# Compilar todas las capas del proyecto hacia la carpeta bin/
javac -cp "lib/sqlite-jdbc-3.53.4.0.jar" -d bin src/conexion/*.java src/modelo/*.java src/dao/*.java src/dao/implJDBC/*.java src/controlador/*.java src/vista/*.java src/App.java
```

### Ejecución de la Aplicación
```bash
# Ejecutar la clase principal App
java -cp "bin:lib/sqlite-jdbc-3.53.4.0.jar" App
```
*(En Windows utilizar punto y coma como separador del classpath: `"bin;lib/sqlite-jdbc-3.53.4.0.jar"`)*.

Al iniciar, el sistema ejecutará `DataBaseInitializer` para verificar la existencia del esquema de tablas en `plataforma.db` y desplegará el menú interactivo de la vista.
