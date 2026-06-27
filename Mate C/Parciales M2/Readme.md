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
*   **✅Evaluación 01/07/2025 (Punto 1.a):** En lugar de darte $L(e_2)$, el enunciado da **$L(e_1 - e_2) = -2e_2$**. Esto requiere que sepas que $L(e_1) - L(e_2) = -2e_2$ para poder hallar la segunda columna de la matriz.
*   **Evaluación 02/12/2025 (Punto 1.a):** Sigue una lógica similar pero más compleja, dando datos como **$T(4e_1) = 4e_1 + 4e_2$** y **$T(e_1 - e_2) = 2e_1$**. El desafío extra aquí es el manejo algebraico de los escalares antes de construir la matriz representativa.

### **2. Composición de Operaciones Geométricas**
Estos ejercicios no te dan una fórmula, sino una descripción de lo que le sucede al plano. El desafío es construir la matriz total multiplicando las matrices de cada operación en el orden correcto ($A_{total} = A_2 \cdot A_1$):
*   **✅Recuperatorio 11/07/2023 (Punto 1.b):** Pide una transformación que primero proyecta sobre el **eje $y$** y luego rota **$\pi/2$**. 
*   **Recuperatorio 12/07/2022 (Punto 1.a):** Es especialmente desafiante porque invierte el orden usual y combina una **rotación** con una **reflexión respecto al eje $y$**. La dificultad reside en no confundir las matrices de cada operador geométrico básico.

### **3. Expresiones Genéricas vs. Cálculos Numéricos**
La mayoría de los exámenes piden la proyección de un vector específico (ej. $v = (1, 2, 3)$). El siguiente caso eleva el nivel de abstracción:
*   **Recuperatorio 12/07/2022 (Punto 1.b.ii):** En lugar de pedir un número, solicita hallar la **expresión general** para la proyección ortogonal $P_S(v)$ de un vector genérico **$v = (x, y, z)$** sobre el subespacio $S$. Esto requiere trabajar con variables en lugar de constantes, lo cual es un error común en el manejo de símbolos bajo presión de examen.

### **4. Subespacios definidos por Ecuaciones y en $\mathbb{R}^4$**
Casi siempre se trabaja con subespacios definidos por sus generadores (vectores). Estos casos cambian el punto de partida:
*   **Evaluación 03/12/2024 (Punto 1.b):** El subespacio $S$ no viene dado por vectores, sino por la ecuación **$x + y - z = 0$**. El alumno debe primero hallar una base del plano (resolviendo el sistema) para recién después aplicar Gram-Schmidt o la fórmula de proyección.
*   **Flotante 15/02/2024 (Punto 1.b):** Presenta un desafío de escala al trabajar en **$\mathbb{R}^4$**. Aunque el método es el mismo, la carga de cuentas y la posibilidad de error numérico aumentan significativamente al operar con vectores de cuatro componentes y proyectar sobre un subespacio de dimensión 2.

### **5. Justificación Teórica de la Linealidad**
*   **✅Recuperatorio 11/07/2023 (Punto 1.a.i):** Es uno de los pocos que pide explícitamente **justificar** si una función es una transformación lineal. No basta con operar; hay que demostrar analíticamente que se cumplen las propiedades de **aditividad** ($T(u+v) = T(u)+T(v)$) y **homogeneidad** ($T(cu) = cT(u)$) usando vectores genéricos.

### **6. Definición del Complemento Ortogonal ($S^\perp$)**
*   **Evaluación 02/12/2025 (Punto 1.b.iii):** Agrega un paso que no suele aparecer: **"Definir el subespacio $S^\perp$ ortogonal a $S$ y dar una base del mismo"**. Para resolverlo, hay que entender la conexión entre el subespacio y el espacio nulo de la matriz formada por sus vectores base.

**Consejo para el examen:** Si te aparece un ejercicio de "composición" (Punto 2 de esta lista), recordá siempre que la matriz de la **primera** operación que se menciona es la que va a la **derecha** en el producto de matrices.
<br>

# Punto 4)
Al revisar las evaluaciones de ambos módulos, se observa que el **Punto 4** suele ser el lugar donde la cátedra introduce desafíos que van más allá del cálculo mecánico, exigiendo **interpretación física**, **razonamiento lógico** sobre propiedades o el manejo de **estructuras matriciales complejas**.

Aquí tienes los ejercicios del **Punto 4** más destacados por su originalidad o dificultad extra:

### **2. Interpretación de Modelado Físico (Módulo II)**
En los parciales de **✅01/07/2025** y **02/12/2025**, tras resolver una ecuación diferencial de segundo orden, se agrega una pregunta conceptual que no suele aparecer en la práctica básica.
*   **El desafío:** "¿Qué sistemas podría modelar esta ecuación?" o "¿Con qué fenómeno se relaciona este caso?" [4.c, 4.b].
*   **Lo diferente:** Debes conectar la matemática con la física. Por ejemplo, si la solución oscila y crece sin tope, debes identificar el fenómeno de **resonancia**. Si tiene un término de fricción, es un **oscilador armónico amortiguado**.

### **3. Análisis Asintótico: Comportamiento para $t \gg 1$ (Módulo II)**
El parcial del **03/12/2024** introduce un requisito de análisis de límites.
*   **El desafío:** Después de hallar la solución general $y(t),$ pide: **"Describir su comportamiento para $t \gg 1$"** [4.a].
*   **Lo diferente:** Esto requiere que analices qué términos de tu solución "sobreviven" a largo plazo (estado estacionario) y cuáles desaparecen (transitorios) debido a exponenciales negativas ($e^{-at} \to 0$).

### **4. Desafíos Lógicos y Demostraciones (Módulo I)**
Algunos exámenes (como el del **29/04/2021** o **26/04/2022**) presentan preguntas de "Verdadero o Falso" o demostraciones teóricas en el Punto 4.
*   **El desafío:** "Si una matriz real $M$ satisface $MM^T = I_n$, ¿qué valores puede tomar su determinante?" [4.c].
*   **Lo diferente:** No hay números para calcular. Debes usar que $\det(MM^T) = \det(M) \cdot \det(M^T) = [\det(M)]^2$ y que $\det(I) = 1,$ para concluir que $\det(M) = \pm 1$.
*   **Otro caso:** "Dada $A$ singular y $C$ no singular, ¿es $D = AC + A^2$ siempre singular?" [4.c]. Aquí el reto es **factorizar la matriz** ($D = A(C+A)$) para usar la propiedad de que el producto de matrices es singular si al menos uno de sus factores lo es.

### **5. Sistemas no Homogéneos con Parámetros (Módulo II)**
El ejercicio **4.b del ✅01/07/2025** es un desafío de precisión.
*   **El desafío:** Resolver $y'' + 4y = a \cos(\omega t)$ considerando que $|\omega| \neq 2.$
*   **Lo diferente:** El uso de letras ($a, \omega$) en lugar de números obliga a arrastrar variables en el método de coeficientes indeterminados, lo que aumenta drásticamente la probabilidad de errores algebraicos al despejar la solución particular.

**Resumen para tu estudio:** Si el examen es del **Módulo I**, prepárate para **bloques y propiedades teóricas** en el Punto 4. Si es del **Módulo II**, Point 4 te exigirá **explicar el comportamiento físico** de tus soluciones de ecuaciones diferenciales.

# Punto 5)
Al analizar los ejercicios del **Punto 5** en los exámenes del Módulo II (centrados en **Series de Fourier y Ecuaciones Diferenciales en Derivadas Parciales**), se observan patrones donde la cátedra introduce desafíos que van más allá del cálculo mecánico de integrales.

Aquí te detallo los que presentan una vuelta de tuerca o un desafío extra:

### **1. La "Trampa" de la Condición Inicial Constante ($U(x,0) = k$)**
Es muy común que el punto **5.b** pida resolver la ecuación de difusión. Lo "habitual" es que la condición inicial sea una función seno o coseno, lo que hace el cálculo casi inmediato. Sin embargo, en varios exámenes la condición es un número constante:
*   **Ejemplos:** Parcial del **01/07/2025** ($U(x,0) = 1$) [5.b], **25/06/2024** ($U(x,0) = 2$) [5.b] y **11/07/2023** ($U(x,0) = -1/2$) [5.b].
*   **El desafío:** Muchos alumnos se bloquean porque no ven una función trigonométrica. El reto es entender que para cumplir las condiciones de contorno nulas ($U=0$ en los bordes), debes realizar el **desarrollo de medio rango en senos** de la función constante. Esto genera una serie infinita, a diferencia de los casos donde la condición ya es un seno.

### **2. Interpretación Física: El Término Dominante ($t \gg 1$)**
Algunos ejercicios no terminan al hallar la solución general, sino que exigen una conclusión física sobre el comportamiento del sistema a largo plazo.
*   **Ejemplos:** Recuperatorio **20/02/2020** [5.b, 5.b] y **18/07/2019** [5.b].
*   **El desafío:** Se pide indicar el **término dominante para tiempos largos**. Para resolverlo, hay que comprender que en la solución $U(x,t) = \sum B_n \sin(n\pi x/L) e^{-n^2 \dots t}$, a medida que $t$ crece, las exponenciales con $n > 1$ se hacen insignificantes mucho más rápido que la de $n=1$. Por lo tanto, para $t \gg 1,$ la temperatura de la barra se comporta simplemente como el **primer término de la serie** ($n=1$).

### **3. La Solución "Servida" (Observación vs. Cálculo)**
Hay ejercicios diseñados para premiar al alumno que observa la estructura antes de empezar a integrar.
*   **Ejemplo:** Recuperatorio **12/07/2022** (Punto 5.b). La condición inicial es $U(x,0) = 3\sin(x) - \sin(2x)$.
*   **El desafío:** Si intentas aplicar la fórmula integral de los coeficientes $B_n,$ perderás mucho tiempo. El desafío extra aquí es la **capacidad de síntesis**: reconocer que la condición inicial ya es una suma finita de senos, por lo que los coeficientes son directamente $B_1 = 3,$ $B_2 = -1$ y todos los demás cero [3.b. La solución se escribe en un renglón.

### **4. Análisis de Convergencia en Discontinuidades**
Casi todos piden indicar a qué valor converge la serie en puntos específicos, pero algunos eligen puntos "frontera" o fuera del intervalo principal.
*   **Ejemplo:** Parcial **01/07/2025** y **20/02/2020**. Piden la convergencia en $x = 3/2$ o $x = 3\pi/2$ cuando el intervalo original es $[-1,1]$ o $[-\pi, \pi]$ [5.a, 5.a].
*   **El desafío:** Requiere aplicar correctamente el **Teorema de Dirichlet** y entender la **extensión periódica**. Si el punto está en una discontinuidad de la extensión, la serie converge al punto medio de los límites laterales ($[f(x^+) + f(x^-)]/2$). Es un error común olvidar que la serie "repite" el comportamiento del intervalo base.

### **5. El Uso de Parámetros en lugar de Números**
*   **Ejemplo:** Parcial **03/12/2024** (Punto 5.b). Incluye la constante de difusión como $\alpha > 0$ sin darle un valor numérico.
*   **El desafío:** Obliga a arrastrar la variable $\alpha$ en toda la resolución de la EDP, lo que pone a prueba la precisión algebraica del alumno para no perder constantes en los exponentes de la solución final.

**Resumen:** Para lucirte en el Punto 5, no solo practiques integrales; asegurate de saber cómo se comporta la serie en los **saltos de la función** y cómo "mueren" los términos de mayor orden con el **paso del tiempo**.
