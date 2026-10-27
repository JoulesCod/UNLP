# Guía Teórica Definitiva: Primer Parcial (CSO / ISO)

**Asignatura:** Conceptos de Sistemas Operativos (CSO) / Introducción a los Sistemas Operativos (ISO)  
**Institución:** Facultad de Informática - Universidad Nacional de La Plata (UNLP)  
**Alineación:** Contenidos completos de Prácticas 1 y 2 (Silberschatz, Stallings y Exámenes de la UNLP).

---

## MÓDULO 1: Conceptos Fundamentales, Arquitectura y Soporte de Hardware

### 1.1 Funciones y Objetivos del Sistema Operativo
Un Sistema Operativo (SO) es un programa que actúa como intermediario entre los usuarios/aplicaciones y el hardware del computador. Sus tres objetivos principales son:
1. **Abstracción:** Ocultar la complejidad y especificidad del hardware físico mediante una interfaz de alto nivel cómoda y uniforme (llamadas al sistema, archivos, procesos).
2. **Administración Eficiente de Recursos:** Asignar y arbitrar el uso de la CPU, memoria principal, almacenamiento secundario y dispositivos de Entrada/Salida (E/S) para maximizar el rendimiento general.
3. **Protección y Aislamiento:** Garantizar que las aplicaciones no interfieran entre sí ni con el propio Kernel, evitando que un fallo en un programa de usuario comprometa la estabilidad global del sistema.

### 1.2 Soporte de Hardware: Modos de Ejecución (*Dual-Mode*)
El mecanismo de doble modo de ejecución (**Modo Usuario** y **Modo Supervisor / Kernel**) es un **mecanismo del hardware del procesador** (definido mediante un bit de modo en el registro de estado o *PSW*), **NO** una creación puramente de software del Kernel.

* **Modo Usuario (*Non-Privileged Mode*):**
  * Subconjunto restringido de instrucciones ejecutables.
  * **No** puede ejecutar instrucciones privilegiadas (ej: deshabilitar interrupciones, modificar registros de la MMU, acceder directamente a puertos de E/S o a posiciones de memoria protegida).
* **Modo Kernel / Supervisor (*Privileged Mode*):**
  * Acceso total y sin restricciones a todas las instrucciones de la CPU y a todo el mapa de memoria física.
  * **Regla de examen:** Únicamente el **Kernel del SO** (y sus *drivers*) ejecuta en Modo Supervisor. Los demonios, utilidades, el Shell y las aplicaciones de usuario corren obligatoriamente en Modo Usuario.

### 1.3 Cambio de Modo (*Mode Switch*) vs. Cambio de Contexto (*Context Switch*)
* **Mode Switch (Cambio de Modo):** 
  * Ocurre cuando un proceso en Modo Usuario realiza una llamada al sistema (*System Call*) o sufre una interrupción/trampa (*Trap*).
  * La CPU conmuta a Modo Kernel para ejecutar la rutina de atención del SO **a favor del mismo proceso**.
  * **Punto de examen:** Un *Mode Switch* **NO crea un nuevo proceso** ni implica un *Context Switch*, a menos que el Kernel decida desalojar al proceso actual.
* **Context Switch (Cambio de Contexto):**
  * Ocurre cuando el *Scheduler* detiene la ejecución del proceso actual para asignarle la CPU a otro proceso en la cola de *Ready*.
  * Es una operación costosa (*overhead* de CPU), pues requiere guardar el estado completo de registros (incluyendo $PC$ y $SP$) en la PCB del proceso saliente y restaurar el estado almacenado en la PCB del proceso entrante.

---

## MÓDULO 2: Gestión de Procesos y Ciclo de Vida

### 2.1 Concepto de Proceso y Estructura en Memoria
Un proceso es un **programa en ejecución**. Su estructura comprende dos áreas claramente delimitadas:

1. **Espacio de Direcciones (Espacio de Usuario):**
   * **Text / Code:** Código ejecutable del programa (instrucciones).
   * **Data / BSS:** Variables globales y estáticas (inicializadas y no inicializadas).
   * **Heap:** Memoria dinámica asignada en tiempo de ejecución (ej: `malloc()`).
   * **Stack (Pila):** Variables locales, parámetros de funciones y direcciones de retorno.

2. **Bloque de Control de Proceso (PCB - *Process Control Block*):**
   * Estructura de datos mantenida **exclusivamente por el Kernel** en el **Espacio de Kernel**.
   * **Contenido clave:** PID, PPID, Estado del proceso, Registros de la CPU ($PC$, $SP$), Información de Planificación (prioridad, quantum), Punteros a Memoria (Tablas de Páginas/Segmentos), Descriptores de archivos abiertos y datos de E/S.
   * **Afirmación de examen:** El espacio de direcciones de usuario de un proceso **NO incluye a su PCB**.

### 2.2 Transiciones de Estado de un Proceso (Modelo de 5 Estados)

```
        +-----+  admitted  +-------+  scheduler   +---------+  exit   +------------+
        | New | ---------> | Ready | -----------> | Running | ------->| Terminated |
        +-----+            +-------+   dispatch   +---------+         +------------+
                             ^                         |
                             |      I/O or event       |  I/O or event
                             |       completion        v    wait
                             |                    +---------+
                             +------------------- | Waiting |
                                                  +---------+
```

1. **New (Nuevo):** El Kernel crea la PCB y le asigna un PID.
2. **Ready (Listo):** Cargar en RAM con todas las condiciones para ejecutar; aguarda que el planificador le asigne la CPU.
3. **Running (En Ejecución):** Sus instrucciones se están ejecutando en la CPU.
4. **Waiting / Blocked (Bloqueado):** Aguarda por un evento externo (finalización de E/S, señal, etc.). **Ojo:** Existen **múltiples colas de espera**, típicamente una por cada dispositivo de E/S o evento específico.
5. **Terminated (Terminado):** Ha finalizado su ejecución (`exit()`).

#### Transiciones Clave de Examen:
* **Running $\to$ Ready:** Producida por la expiración del **Quantum** en algoritmos apropiativos (Round Robin) o por el arribo de un proceso con mayor prioridad.
* **Running $\to$ Waiting:** Ocurre cuando el proceso solicita de forma voluntaria una operación bloqueante (ej: `read()`).

### 2.3 System Calls de Creación y Ejecución en UNIX (`fork` vs `exec`)

#### `fork()`
* Clona el proceso invocante. Crea un proceso hijo con un espacio de direcciones **copia idéntica** al del padre.
* Devuelve:
  * `0` al proceso **hijo**.
  * El **PID del hijo** (valor $> 0$) al proceso **padre**.
  * `< 0` en caso de error.

#### `exec()` (`execve`, `execv`)
* Reemplaza la imagen del espacio de direcciones de usuario por la de un nuevo programa ejecutable.
* **Puntos Críticos de Examen:**
  1. **NO se destruye ni se crea una nueva PCB:** `exec()` **reutiliza el mismo PCB** y conserva el **mismo PID**, PPID, permisos y descriptores de archivos abiertos.
  2. Si `exec()` tiene éxito, **las líneas de código posteriores a la llamada NO se ejecutan jamás**.

---

## MÓDULO 3: Planificación de CPU (*Scheduling*)

### 3.1 Niveles de Planificadores (*Schedulers*)
1. **Long-Term Scheduler (Planificador a Largo Plazo):**
   * Decide qué procesos en disco pasan del estado *New* a *Ready* (cargados en memoria).
   * **Función principal:** Controla el **grado de multiprogramación** (cantidad total de procesos en RAM).
2. **Medium-Term Scheduler (Planificador a Mediano Plazo):**
   * Administra el **Swapping (Intercambio)**. Suspende o reanuda procesos moviendo su imagen entre RAM y el área de Swap en disco (*Swap Out / Swap In*).
3. **Short-Term Scheduler (Planificador a Corto Plazo / Dispatcher):**
   * Selecciona de forma muy frecuente qué proceso de la cola de *Ready* tomará la CPU a continuación.

### 3.2 Clasificación de Algoritmos: Apropiativos vs. No Apropiativos

| Criterio | No Apropiativos (*Non-Preemptive*) | Apropiativos (*Preemptive*) |
| :--- | :--- | :--- |
| **Desalojo involuntario** | **No.** El proceso no puede ser expulsado de la CPU. La cede solo por finalización (`exit`) o por E/S (`wait`). | **Sí.** El Kernel expulsa al proceso en ejecución para darle la CPU a otro más prioritario o por fin de quantum. |
| **Uso de Interrupción por Timer** | No realiza decisiones de desalojo por reloj. | Sí. Utiliza la interrupción de reloj para controlar los quantums. |
| **Algoritmos representativos** | FCFS, SJF Tradicional. | Round Robin (RR), SRTF, Prioridades Apropiativo. |
| **Comportamiento ante Trabajos** | Favorece a procesos **CPU-bound** (ráfagas largas). | Mantiene la equidad y beneficia el tiempo de respuesta de **I/O-bound**. |

### 3.3 Tipos de Procesos, Inanición y Aging
* **CPU-bound:** Realiza ráfagas de CPU largas y pocas operaciones de E/S.
* **I/O-bound:** Realiza ráfagas de CPU cortas y frecuentes operaciones de E/S. *(Beneficiados por algoritmos como Round Robin con quantums pequeños o prioridades dinámicas).*
* **Inanición (*Starvation*):** Ocurre cuando un proceso de baja prioridad o ráfaga larga queda indefinidamente sin acceder a la CPU.
* **Envejecimiento (*Aging*):** Solución a la inanición. Consiste en **incrementar gradualmente la prioridad** de un proceso a medida que pasa tiempo esperando en la cola de listos.

---

## MÓDULO 4: Gestión de Memoria Principal y MMU

### 4.1 La Unidad de Manejo de Memoria (MMU)
* Es un dispositivo de **hardware** encargado de traducir en tiempo de ejecución las **direcciones lógicas (virtuales)** generadas por la CPU a **direcciones físicas** en la memoria RAM.
* **Ojo de Parcial:** La MMU **NO asigna la memoria**. La asignación/reserva de memoria física es una tarea del **Kernel del SO**. La MMU solo traduce y controla los límites de protección.

### 4.2 Fragmentación de Memoria
* **Fragmentación Interna:** Desperdicio dentro de un bloque de tamaño fijo asignado, si el proceso no utiliza la totalidad del bloque (ej: Particiones Fijas, Paginación).
* **Fragmentación Externa:** Desperdicio cuando el espacio total libre en RAM es suficiente para satisfacer una solicitud, pero está dividido en **bloques no contiguos** (ej: Particiones Dinámicas, Segmentación pura).

### 4.3 Esquemas de Asignación de Memoria

#### A. Paginación Pura
* **Memoria Física:** Se divide en bloques fijos llamados **Marcos (Frames)**.
* **Memoria Lógica:** Se divide en bloques del mismo tamaño llamados **Páginas**.
* **Traducción:** Realizada mediante la **Tabla de Páginas** individual de cada proceso.
* **Tipos de Fragmentación:** **Elimina la Fragmentación Externa**, pero produce **Fragmentación Interna** (únicamente en el último marco asignado al proceso).
* **Fallo de Página (*Page Fault*):** Ocurre cuando se accede a una página virtual cuyo bit de presencia en la tabla de páginas es $0$. El Kernel pasa el proceso a estado **Waiting**, lee la página desde el disco y la carga en un marco libre de la RAM.
* **TLB (*Translation Lookaside Buffer*):** Caché de hardware para traducciones de la MMU. Un *TLB Miss* **NO implica un Page Fault**; simplemente obliga a consultar la tabla de páginas en RAM.

#### B. Segmentación Pura
* Divide la memoria en bloques de **tamaño variable** según la visión del programador (Código, Datos, Pila).
* Produce **Fragmentación Externa**.

#### C. Segmentación Paginada
* Combina ambas técnicas: los segmentos se dividen internamente en páginas de tamaño fijo.
* **Fragmentación:** Genera **Fragmentación Interna** (en la última página de cada segmento) y **NO genera Fragmentación Externa**.

---

## MÓDULO 5: Entorno GNU/Linux, Permisos y Shell Scripting

### 5.1 Permisos en Archivos (`chmod`)
Representados por tres octales: $\text{Usuario (u)} \mid \text{Grupo (g)} \mid \text{Otros (o)}$.  
Valores: Lectura ($r = 4$), Escritura ($w = 2$), Ejecución ($x = 1$).

* Ej: `chmod 750 archivo.txt`
  * **User (7):** $4+2+1 = rwx$ (Acceso total).
  * **Group (5):** $4+0+1 = r-x$ (Lectura y ejecución).
  * **Others (0):** $0+0+0 = ---$ (Sin ningún permiso).

### 5.2 Archivos del Sistema
* **`/etc/passwd`:** Contiene información de cuentas de usuario (UID, GID, home, shell) en texto plano. Es de **lectura pública**.
* **`/etc/shadow`:** Almacena las contraseñas encriptadas (*hashes*). Solo accesible por **`root` (UID 0)**.

### 5.3 Búsqueda de Información (`find` vs `grep`)
* **`find`:** Busca **archivos y directorios** en el árbol del sistema según nombre, tamaño o permisos.
* **`grep`:** Busca **patrones o cadenas de texto** dentro del *contenido* de los archivos. (La opción `-i` ignora mayúsculas/minúsculas).

### 5.4 Redirecciones y Pipes
* **`>`:** Redirige la salida estándar a un archivo (sobrescribe).
* **`>>`:** Redirige la salida estándar agregando texto al final (*append*).
* **`|` (Pipe):** Conecta la salida estándar de un comando con la entrada estándar de otro proceso.

---

## MÓDULO 6: Proceso de Arranque en GNU/Linux

### Secuencia Lógica General
$$\text{Firmware (BIOS / UEFI)} \longrightarrow \text{Bootloader (GRUB)} \longrightarrow \text{Kernel} \longrightarrow \text{Espacio de Usuario (init / systemd)}$$

1. **Firmware:**
   * **BIOS:** Ejecuta el POST y lee el **MBR** (primer sector del disco, 512 bytes). **NO entiende sistemas de archivos**.
   * **UEFI:** Lee tablas **GPT**, reconoce sistemas de archivos FAT16/FAT32 en la partición `ESP` y ejecuta binarios `.efi`.
2. **MBR vs GPT:**
   * **MBR:** Sector 0 ($512\text{ bytes}$). Contiene $446\text{ B}$ de código bootstrap, $64\text{ B}$ de tabla de particiones (máximo 4 particiones primarias) y $2\text{ B}$ de firma (`0xAA55`).
   * **Protective MBR en GPT:** Ubicado en LBA 0 de discos GPT para evitar que herramientas antiguas piensen que el disco está vacío y lo sobreescriban.
3. **Primer Proceso en Espacio de Usuario:**
   * **`init` (SystemV):** Primer proceso (PID 1). Lee `/etc/inittab` e inicializa servicios de forma **secuencial** mediante niveles de ejecución (*runlevels* $0$ a $6$).
   * **`systemd`:** Reemplaza a `init`. Inicializa servicios en **paralelo** mediante *Units* y *Targets* (ej: `multi-user.target`).
