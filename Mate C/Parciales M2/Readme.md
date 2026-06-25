Los parciales me parecen re largos, aguante los parcialitos
<br>
<br>

Al analizar los exámenes parciales y recuperatorios presentes en las fuentes, se observa que la mayoría sigue una estructura estándar de tres partes: una justificación teórica, un análisis geométrico y un ejercicio práctico de diagonalización. Sin embargo, algunos parciales se destacan por incluir desafíos conceptuales o estructuras matriciales menos frecuentes:

### **1. Evaluación del 02/12/2025: Desafío de Ejemplificación**
Este parcial se diferencia del resto en su punto 3.a.i, donde no pide resolver un sistema dado, sino que solicita al alumno **"Indicar qué significa que una matriz $M$ sea diagonalizable"** y, lo que es más inusual, pide **"Dar un ejemplo de i) una matriz $M$ de $2 \times 2$ diagonalizable y ii) una matriz $C$ de $2 \times 2$ no diagonalizable"**. 
*   Mientras que otros exámenes dan la matriz para analizar, aquí se evalúa la capacidad de construcción del alumno. 
*   Además, en el punto 3.c.ii, pide expresar **$M^{-2}$** en términos de $U$ y $D$. La mayoría de los exámenes suelen pedir potencias positivas altas (como $M^8$ o $A^{20}$), pero este solicita explícitamente una potencia negativa, lo que requiere comprender la inversión de la matriz diagonalizada.

### **2. Evaluaciones con Matrices de 4x4 (01/07/2025 y 11/07/2023)**
Aunque la mayoría de los ejercicios trabajan con matrices de $2 \times 2$ o $3 \times 3$, estos dos exámenes presentan matrices de **$4 \times 4$**:
*   **Parcial del 01/07/2025 (Fuente 1):** Presenta una matriz $M$ de $4 \times 4$ que, a pesar de su tamaño, está estructurada por **bloques diagonales** (dos bloques de $2 \times 2$). Esto permite calcular los autovalores de cada bloque por separado para obtener el conjunto total, un concepto que se explica detalladamente en la "Actividad de Repaso Resuelta".
*   **Recuperatorio del 11/07/2023 (Fuente 20):** La matriz $B$ de este examen también es de $4 \times 4$ y posee bloques nulos, lo que facilita encontrar **autovalores obvios** (aquellos que se ven directamente en la diagonal si la fila o columna tiene un solo elemento no nulo).

### **3. Recuperatorio del 18/07/2019: Relaciones Algebraicas Complejas**
Este examen se destaca en su punto 3.a por pedir una demostración sobre la herencia de autovectores:
*   Pregunta: **"Si $v$ es autovector de $A$, ¿lo es también de $B = A - 2A^2$? En tal caso, ¿con qué autovalor?"**. 
*   Este tipo de pregunta requiere aplicar la propiedad de que si $v$ es autovector de $A$ con $\lambda$, entonces es autovector de $A^k$ con $\lambda^k$. Es un planteo más abstracto que el simple cálculo numérico.

### **4. El concepto de "Autovalores Obvios"**
Varios parciales (como los de **25/06/2024**, **03/12/2024** y **11/07/2023**) incluyen una pregunta específica: **"Indicar si posee autovalores obvios"**. 
*   Esto hace referencia a matrices con estructuras triangulares o por bloques donde los autovalores coinciden con los elementos de la diagonal principal. 
*   Se destaca el **Recu del 11/07/23** porque la matriz $B$ tiene un bloque inferior donde el $-6$ es un autovalor evidente por ser el único elemento en su fila y columna dentro de ese bloque.

En resumen, si buscas algo diferente para estudiar, el parcial del **02/12/2025** es el que más se aleja de la rutina de cálculo al exigir la creación de ejemplos propios y el manejo de potencias negativas.

---
Al profundizar en los exámenes parciales y recuperatorios, he identificado varios ejercicios que se alejan del simple cálculo numérico y proponen desafíos **conceptuales, geométricos o de construcción**.

Aquí tienes los ejercicios más destacados por ser diferentes:

### **1. Herencia de propiedades: Funciones de Matrices**
Varios parciales evalúan si comprendes cómo cambian los autovalores cuando aplicas una operación algebraica a la matriz, basándose en la propiedad de que si $v$ es autovector de $A$ con $\lambda$, entonces es autovector de $A^k$ con $\lambda^k$ [19: 577, 581].
*   **Recu 18/07/2019:** Pregunta si $v$ es autovector de $B = A - 2A^2$ dado que lo es de $A$, y pide hallar el nuevo autovalor asociado [23: 3.a].
*   **Recu 11/07/2023:** Propone una relación similar con $C = 3B + I$ y pide explicar cómo se relacionan los autovalores y autoespacios de $C$ con los de $B$ [20: 2.a].
*   **Flotante 15/02/2024:** Plantea el caso para la expresión $2A - A^2$ [14: 3.a].
*   **Evaluación 03/12/2024:** Pide expresar el vector $(A - 2A^4)v$ en términos de $\lambda$ y $v$ [3: 3.a].

### **2. Desafío de construcción y teoría (02/12/2025)**
Este examen es único porque exige que el alumno sea quien **cree los ejemplos**, no que resuelva uno dado:
*   **Punto 3.a.i:** Solicita definir qué significa que una matriz sea diagonalizable y, acto seguido, pide **dar un ejemplo propio** de una matriz de $2 \times 2$ que **sea** diagonalizable y otro de una que **no lo sea**, justificando la elección [2: 3.a].

### **3. Potencias aplicadas a vectores específicos**
En lugar de pedir la potencia de la matriz completa (como $A^{20}$), algunos ejercicios piden calcular el resultado de aplicar esa potencia a un **vector determinado**, lo cual es mucho más rápido si se identifica que el vector es un autovector:
*   **Recu 20/02/2020:** Pregunta si $v = (1, 2, 1)^T$ es autovector de $A$ y, basándose en eso, pide calcular **$A^{20}v$** [24: 3.c.ii].
*   **Flotante 15/02/2024:** Realiza una pregunta similar: "¿Es $w = (1, -1, 0)^T$ autovector de $A$?" y pide una expresión para $A^k w$ [14: 3.c.iii].

### **4. Matrices de 4x4 y Estructura por Bloques**
Aunque el tamaño asusta, estos ejercicios evalúan la capacidad de notar **estructuras simplificadoras**:
*   **Evaluación 01/07/2025:** Presenta una matriz $M$ de $4 \times 4$ dividida en dos bloques de $2 \times 2$ rodeados de ceros. El desafío es notar que los autovalores de $M$ son simplemente la unión de los autovalores de cada bloque [1: 3.b].
*   **Actividad de Repaso:** Explica formalmente este concepto con una matriz de $4 \times 4$ que tiene un bloque diagonal y otro bloque simétrico, facilitando el cálculo de la matriz de transición $S$ mediante la normalización de autovectores [13: 1.c].

### **5. Potencias negativas y exponentes simbólicos**
*   **Evaluación 02/12/2025:** Es el único que pide explícitamente calcular una **potencia negativa**: expresar **$M^{-2}$** en términos de las matrices de diagonalización $U$ y $D$ [2: 3.c.ii].
*   **Evaluación 03/12/2024:** Pide expresar **$B^k$** de forma genérica para cualquier $k \geq 1$ entero, escribiendo la matriz diagonal $D^k$ de forma explícita [3: 3.c.iii].

### **6. Análisis Geométrico "Sin Cuentas"**
Muchos parciales incluyen un ítem donde se debe hallar todo **sin hacer cálculos algebraicos**, solo razonando sobre la transformación en el plano:
*   **Reflexiones:** Se pide analizar la reflexión respecto al eje $y$ [1: 3.c], respecto a la recta $y = -x$ [20: 2.b] o respecto a $y = x$ [24: 3.b]. Se debe identificar que los vectores sobre la recta tienen $\lambda = 1$ y los perpendiculares $\lambda = -1$ [19: 566].
*   **Proyecciones:** El ejercicio de la **Actividad de Repaso** pide analizar geométricamente la proyección ortogonal sobre el eje $z$ en $\mathbb{R}^3$. Aquí el desafío es notar que el autoespacio para $\lambda = 0$ es un plano (el plano $xy$) y para $\lambda = 1$ es una recta (el eje $z$) [13: 1.b].

### **7. Relación con el Determinante y Singularidad**
*   **Actividad de Repaso:** Plantea una pregunta teórica fundamental: "¿Qué relación existe entre los autovalores y el determinante?". Evalúa si el alumno sabe que el determinante es el **producto de los autovalores** y que si la matriz es singular, al menos un autovalor **debe ser cero** [13: 1.a].
