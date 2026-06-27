🌹 Punto 1
🌸 Punto 4
🍀 Punto 5

Los parciales me parecen re largos, aguante los parcialitos

Recomiendo MUY fuertemente, para los parcialitos, mandarle a Notebook LM todos los parciales que se tienen del módulo y preguntarle cuáles son los puntos más raros, que parciales tienen algo diferente y que otras ideas se le ocurre. FIJO te arma uno que aparece.

---
---

# Punto 1)
Al revisar los exámenes parciales y recuperatorios disponibles en las fuentes, se observa que, aunque el **Punto 1** suele seguir una estructura predecible (transformaciones lineales y proyecciones), existen variantes que presentan desafíos lógicos, mayor nivel de abstracción o integran conceptos de forma poco habitual.

Aquí tienes los ejercicios del **Punto 1** que se destacan por ser diferentes o tener un desafío extra:

### **1. Definición de Transformaciones mediante Combinaciones de Vectores**
Lo más común es que te den las imágenes de la base canónica ($L(e_1), L(e_2)$, etc.). Sin embargo, algunos parciales te obligan a usar la **propiedad de linealidad** para "despejar" las imágenes antes de armar la matriz:
*   **Evaluación 01/07/2025 (Punto 1.a):** En lugar de darte $L(e_2)$, el enunciado da **$L(e_1 - e_2) = -2e_2$**. Esto requiere que sepas que $L(e_1) - L(e_2) = -2e_2$ para poder hallar la segunda columna de la matriz.
*   **Evaluación 02/12/2025 (Punto 1.a):** Sigue una lógica similar pero más compleja, dando datos como **$T(4e_1) = 4e_1 + 4e_2$** y **$T(e_1 - e_2) = 2e_1$**. El desafío extra aquí es el manejo algebraico de los escalares antes de construir la matriz representativa.

### **2. Composición de Operaciones Geométricas**
Estos ejercicios no te dan una fórmula, sino una descripción de lo que le sucede al plano. El desafío es construir la matriz total multiplicando las matrices de cada operación en el orden correcto ($A_{total} = A_2 \cdot A_1$):
*   **Recuperatorio 11/07/2023 (Punto 1.b):** Pide una transformación que primero proyecta sobre el **eje $y$** y luego rota **$\pi/2$**. 
*   **Recuperatorio 12/07/2022 (Punto 1.a):** Es especialmente desafiante porque invierte el orden usual y combina una **rotación** con una **reflexión respecto al eje $y$**. La dificultad reside en no confundir las matrices de cada operador geométrico básico.

### **3. Expresiones Genéricas vs. Cálculos Numéricos**
La mayoría de los exámenes piden la proyección de un vector específico (ej. $v = (1, 2, 3)$). El siguiente caso eleva el nivel de abstracción:
*   **Recuperatorio 12/07/2022 (Punto 1.b.ii):** En lugar de pedir un número, solicita hallar la **expresión general** para la proyección ortogonal $P_S(v)$ de un vector genérico **$v = (x, y, z)$** sobre el subespacio $S$. Esto requiere trabajar con variables en lugar de constantes, lo cual es un error común en el manejo de símbolos bajo presión de examen.

### **4. Subespacios definidos por Ecuaciones y en $\mathbb{R}^4$**
Casi siempre se trabaja con subespacios definidos por sus generadores (vectores). Estos casos cambian el punto de partida:
*   **Evaluación 03/12/2024 (Punto 1.b):** El subespacio $S$ no viene dado por vectores, sino por la ecuación **$x + y - z = 0$**. El alumno debe primero hallar una base del plano (resolviendo el sistema) para recién después aplicar Gram-Schmidt o la fórmula de proyección.
*   **Flotante 15/02/2024 (Punto 1.b):** Presenta un desafío de escala al trabajar en **$\mathbb{R}^4$**. Aunque el método es el mismo, la carga de cuentas y la posibilidad de error numérico aumentan significativamente al operar con vectores de cuatro componentes y proyectar sobre un subespacio de dimensión 2.

### **5. Justificación Teórica de la Linealidad**
*   **Recuperatorio 11/07/2023 (Punto 1.a.i):** Es uno de los pocos que pide explícitamente **justificar** si una función es una transformación lineal. No basta con operar; hay que demostrar analíticamente que se cumplen las propiedades de **aditividad** ($T(u+v) = T(u)+T(v)$) y **homogeneidad** ($T(cu) = cT(u)$) usando vectores genéricos.

### **6. Definición del Complemento Ortogonal ($S^\perp$)**
*   **Evaluación 02/12/2025 (Punto 1.b.iii):** Agrega un paso que no suele aparecer: **"Definir el subespacio $S^\perp$ ortogonal a $S$ y dar una base del mismo"**. Para resolverlo, hay que entender la conexión entre el subespacio y el espacio nulo de la matriz formada por sus vectores base.

**Consejo para el examen:** Si te aparece un ejercicio de "composición" (Punto 2 de esta lista), recordá siempre que la matriz de la **primera** operación que se menciona es la que va a la **derecha** en el producto de matrices.
<br>

# Punto 4)
Al revisar las evaluaciones de ambos módulos, se observa que el **Punto 4** suele ser el lugar donde la cátedra introduce desafíos que van más allá del cálculo mecánico, exigiendo **interpretación física**, **razonamiento lógico** sobre propiedades o el manejo de **estructuras matriciales complejas**.

Aquí tienes los ejercicios del **Punto 4** más destacados por su originalidad o dificultad extra:

### **1. Matrices por Bloques de Gran Escala (Módulo I)**
Varios parciales (como el del **06/05/2025** o **07/10/2025**) presentan un desafío de escala. No te dan una matriz simple, sino que te piden construir una matriz de $4 \times 4$ o $6 \times 6$ a partir de bloques más pequeños.
*   **El desafío:** En el punto **4.a.ii del 06/05/2025**, te dan una matriz $B$ de $2 \times 2$ y te piden hallar el determinante de una matriz $C$ de **$6 \times 6$** definida como:
    $$C = \begin{pmatrix} 2B^2 & 0 \\ 0 & -B^T \end{pmatrix}$$
*   **Lo diferente:** Para resolver esto sin morir en el intento, no debes armar la matriz completa, sino aplicar la **propiedad de determinantes de matrices por bloques** y potencias: $\det(C) = \det(2B^2) \cdot \det(-B^T)$ [18: 221, 222].

### **2. Interpretación de Modelado Físico (Módulo II)**
En los parciales de **01/07/2025** y **02/12/2025**, tras resolver una ecuación diferencial de segundo orden, se agrega una pregunta conceptual que no suele aparecer en la práctica básica.
*   **El desafío:** "¿Qué sistemas podría modelar esta ecuación?" o "¿Con qué fenómeno se relaciona este caso?" [1: 4.c, 2: 4.b].
*   **Lo diferente:** Debes conectar la matemática con la física. Por ejemplo, si la solución oscila y crece sin tope, debes identificar el fenómeno de **resonancia** [19: 543]. Si tiene un término de fricción, es un **oscilador armónico amortiguado** [19: 504].

### **3. Análisis Asintótico: Comportamiento para $t \gg 1$ (Módulo II)**
El parcial del **03/12/2024** introduce un requisito de análisis de límites.
*   **El desafío:** Después de hallar la solución general $y(t),$ pide: **"Describir su comportamiento para $t \gg 1$"** [3: 4.a].
*   **Lo diferente:** Esto requiere que analices qué términos de tu solución "sobreviven" a largo plazo (estado estacionario) y cuáles desaparecen (transitorios) debido a exponenciales negativas ($e^{-at} \to 0$) [19: 505, 526].

### **4. Desafíos Lógicos y Demostraciones (Módulo I)**
Algunos exámenes (como el del **29/04/2021** o **26/04/2022**) presentan preguntas de "Verdadero o Falso" o demostraciones teóricas en el Punto 4.
*   **El desafío:** "Si una matriz real $M$ satisface $MM^T = I_n$, ¿qué valores puede tomar su determinante?" [14: 4.c].
*   **Lo diferente:** No hay números para calcular. Debes usar que $\det(MM^T) = \det(M) \cdot \det(M^T) = [\det(M)]^2$ y que $\det(I) = 1,$ para concluir que $\det(M) = \pm 1$ [18: 216, 218].
*   **Otro caso:** "Dada $A$ singular y $C$ no singular, ¿es $D = AC + A^2$ siempre singular?" [17: 4.c]. Aquí el reto es **factorizar la matriz** ($D = A(C+A)$) para usar la propiedad de que el producto de matrices es singular si al menos uno de sus factores lo es [18: 218].

### **5. Sistemas no Homogéneos con Parámetros (Módulo II)**
El ejercicio **4.b del 01/07/2025** es un desafío de precisión.
*   **El desafío:** Resolver $y'' + 4y = a \cos(\omega t)$ considerando que $|\omega| \neq 2.$
*   **Lo diferente:** El uso de letras ($a, \omega$) en lugar de números obliga a arrastrar variables en el método de coeficientes indeterminados, lo que aumenta drásticamente la probabilidad de errores algebraicos al despejar la solución particular [19: 532].

**Resumen para tu estudio:** Si el examen es del **Módulo I**, prepárate para **bloques y propiedades teóricas** en el Punto 4. Si es del **Módulo II**, Point 4 te exigirá **explicar el comportamiento físico** de tus soluciones de ecuaciones diferenciales.

# Punto 5)
Al revisar el **Punto 5** de los exámenes, es fundamental distinguir entre los dos módulos de la materia, ya que el tema cambia completamente: en el **Módulo I** se centra en **Espacios Vectoriales**, mientras que en el **Módulo II** aborda **Series de Fourier y Ecuaciones Diferenciales en Derivadas Parciales (EDP)**.

A continuación, selecciono los ejercicios que presentan desafíos extra o planteos poco habituales:

### **1. Módulo I: Espacios Vectoriales (Desafíos Lógicos y Abstractos)**

En este módulo, el punto 5 suele pedir bases y dimensiones, pero algunos exámenes introducen variantes teóricas interesantes:

*   **El desafío de las Matrices Simétricas (29/04/2021):** En lugar de trabajar con vectores en $\mathbb{R}^n$, el inciso **5.a** pide justificar si el conjunto de **matrices reales simétricas de $2 \times 2$** es un subespacio de $\mathbb{R}^{2 \times 2}$. Es un ejercicio diferente porque exige aplicar las condiciones de subespacio (vector nulo, suma y producto escalar) a una estructura matricial, obligándote a pensar en la definición de simetría ($A = A^T$) [18, 19: 114].
*   **La "Trampa" de la Desigualdad (06/05/2025):** El inciso **5.a** pregunta si $S = \{(x, y) \in \mathbb{R}^2 : x + y \geq 0\}$ es un subespacio. Este es un desafío de razonamiento: aunque contiene al origen y es cerrado bajo la suma, **falla en el producto por un escalar negativo** [19: 114]. Si multiplicas un vector de $S$ por $-1$, el resultado ya no cumplirá la desigualdad, por lo que no es un subespacio.
*   **Independencia con Parámetros en $\mathbb{R}^3$ (07/10/2025):** En el punto **5.b**, se da un conjunto de tres vectores donde uno depende de un parámetro $d$ y se pide determinar para qué valores es **linealmente independiente** o **dependiente**. Lo complejo aquí es que no solo pide el cálculo, sino especificar el subespacio generado en cada caso y dar su interpretación geométrica.

### **2. Módulo II: Fourier y EDP (Desafíos de Interpretación y Cálculo)**

En el Punto 5 del Módulo II, el mayor reto suele ser la conexión entre la Serie de Fourier y la condición inicial de la ecuación de difusión:

*   **El término dominante para $t \gg 1$ (20/02/2020):** Tras resolver la ecuación de difusión, el inciso **5.b** pide indicar el **término dominante de la solución para tiempos largos**. El desafío extra consiste en entender físicamente que, a medida que el tiempo crece, los términos de la serie con $n$ más alto decaen mucho más rápido debido al factor exponencial $e^{-n^2 \dots t}$, por lo que el término dominante es siempre el primero ($n=1$) [19: 624].
*   **Condición Inicial Constante ($U(x,0) = 1$) (01/07/2025):** En el punto **5.b**, la condición inicial es una constante. Esto suele confundir a los alumnos porque esperan una función seno o coseno. El desafío es darse cuenta de que debes calcular el **desarrollo de medio rango en senos** de la función constante $f(x)=1$ para poder "armar" la solución final [1, 19: 613].
*   **La solución "servida" vs. el cálculo (12/07/2022):** El punto **5.b** da una condición inicial $U(x,0) = 3\sin(x) - \sin(2x)$. Aquí el "desafío" es la **observación**: muchos alumnos intentan calcular las integrales de Fourier desde cero, cuando el ejercicio está diseñado para que reconozcas que los coeficientes $B_1=3$ y $B_2=-1$ (y los demás ceros) ya están dados por la propia expresión de la condición inicial [19: 608, 625].

**Resumen para el examen:** 
*   Si rendís **Módulo I**, tené cuidado con los subespacios que involucran **desigualdades o valores absolutos**, ya que suelen ser la respuesta "trampa" [19: 114].
*   Si rendís **Módulo II**, asegurate de entender cómo la **Serie de Fourier de la condición inicial** se "pega" a la solución de la EDP, y recordá que para $t$ grande, la solución siempre se parece a un simple seno de $n=1$ [19: 624, 625].
