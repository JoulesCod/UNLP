🌹 Punto 1
🌸 Punto 4
🍀 Punto 5

Los parciales me parecen re largos, aguante los parcialitos <br>
Son todos iguales...

Recomiendo MUY fuertemente, para los parcialitos, mandarle a Notebook LM todos los parciales que se tienen del módulo y preguntarle cuáles son los puntos más raros, que parciales tienen algo diferente y que otras ideas se le ocurre. FIJO te arma uno que aparece.

---
---

# Punto 1)
Al revisar los exámenes parciales y recuperatorios disponibles en las fuentes, se observa que, aunque el **Punto 1** suele seguir una estructura predecible (transformaciones lineales y proyecciones), existen variantes que presentan desafíos lógicos, mayor nivel de abstracción o integran conceptos de forma poco habitual.

### **1. Definición de Transformaciones mediante Combinaciones de Vectores**
Lo más común es que te den las imágenes de la base canónica ($L(e_1), L(e_2)$, etc.). Sin embargo, algunos parciales te obligan a usar la **propiedad de linealidad** para "despejar" las imágenes antes de armar la matriz:
*   <mark>**✅Evaluación 01/07/2025 (Punto 1.a):** En lugar de darte $L(e_2)$, el enunciado da **$L(e_1 - e_2)=-2e_2$**. Esto requiere que sepas que $L(e_1) - L(e_2) = -2e_2$ para poder hallar la segunda columna de la matriz.</mark>
*   **Evaluación 02/12/2025 (Punto 1.a):** Sigue una lógica similar pero más compleja, dando datos como **$T(4e_1) = 4e_1 + 4e_2$** y **$T(e_1 - e_2) = 2e_1$**. El desafío extra aquí es el manejo algebraico de los escalares antes de construir la matriz representativa.

### **2. Composición de Operaciones Geométricas**
Estos ejercicios no te dan una fórmula, sino una descripción de lo que le sucede al plano. El desafío es construir la matriz total multiplicando las matrices de cada operación en el orden correcto ($A_{total} = A_2 \cdot A_1$):
*   <mark>**✅Recuperatorio 11/07/2023 (Punto 1.b):** Pide una transformación que primero proyecta sobre el **eje $y$** y luego rota **$\pi/2$**.</mark>
*   **Recuperatorio 12/07/2022 (Punto 1.a):** Es especialmente desafiante porque invierte el orden usual y combina una **rotación** con una **reflexión respecto al eje $y$**. La dificultad reside en no confundir las matrices de cada operador geométrico básico.

### **3. Expresiones Genéricas vs. Cálculos Numéricos**
La mayoría de los exámenes piden la proyección de un vector específico (ej. $v = (1, 2, 3)$). El siguiente caso eleva el nivel de abstracción:
*   **Recuperatorio 12/07/2022 (Punto 1.b.ii):** En lugar de pedir un número, solicita hallar la **expresión general** para la proyección ortogonal $P_S(v)$ de un vector genérico **$v = (x, y, z)$** sobre el subespacio $S$. Esto requiere trabajar con variables en lugar de constantes, lo cual es un error común en el manejo de símbolos bajo presión de examen.

### **4. Subespacios definidos por Ecuaciones y en $\mathbb{R}^4$**
Casi siempre se trabaja con subespacios definidos por sus generadores (vectores). Estos casos cambian el punto de partida:
*   **Evaluación 03/12/2024 (Punto 1.b):** El subespacio $S$ no viene dado por vectores, sino por la ecuación **$x + y - z = 0$**. El alumno debe primero hallar una base del plano (resolviendo el sistema) para recién después aplicar Gram-Schmidt o la fórmula de proyección.
*   **Flotante 15/02/2024 (Punto 1.b):** Presenta un desafío de escala al trabajar en **$\mathbb{R}^4$**. Aunque el método es el mismo, la carga de cuentas y la posibilidad de error numérico aumentan significativamente al operar con vectores de cuatro componentes y proyectar sobre un subespacio de dimensión 2.

### **5. Justificación Teórica de la Linealidad**
*   <mark>**✅Recuperatorio 11/07/2023 (Punto 1.a.i):** Es uno de los pocos que pide explícitamente **justificar** si una función es una transformación lineal. No basta con operar; hay que demostrar analíticamente que se cumplen las propiedades de **aditividad** ($T(u+v) = T(u)+T(v)$) y **homogeneidad** ($T(cu) = cT(u)$) usando vectores genéricos.</mark>

### **6. Definición del Complemento Ortogonal ($S^\perp$)**
*   **Evaluación 02/12/2025 (Punto 1.b.iii):** Agrega un paso que no suele aparecer: **"Definir el subespacio $S^\perp$ ortogonal a $S$ y dar una base del mismo"**. Para resolverlo, hay que entender la conexión entre el subespacio y el espacio nulo de la matriz formada por sus vectores base.

**Consejo para el examen:** Si te aparece un ejercicio de "composición" (Punto 2 de esta lista), recordá siempre que la matriz de la **primera** operación que se menciona es la que va a la **derecha** en el producto de matrices.
<br>

# Punto 4)
Al revisar los exámenes parciales y recuperatorios del **Módulo II**, se observa que el **Punto 4** (dedicado a **Ecuaciones Diferenciales Ordinarias - EDO**) es donde la cátedra suele introducir desafíos que van más allá del cálculo mecánico, exigiendo **interpretación física**, análisis de **comportamiento a largo plazo** o el manejo de **parámetros literales**.

Aquí tienes los ejercicios del Punto 4 que se destacan por ser diferentes o presentar un desafío extra:

### **1. El uso de Parámetros Literales en lugar de Números**
Lo más común es resolver una EDO con números (ej. $y'' + 4y = 3 \cos(2t)$). Sin embargo, algunos parciales elevan la dificultad usando letras, lo que obliga a arrastrar variables y aumenta el riesgo de errores algebraicos al despejar la solución particular:
*   <mark>**Evaluación ✅01/07/2025 (Punto 4.b):** Pide resolver $y'' + 4y = a \cos(\omega t)$ con la condición $|\omega| \neq 2$.</mark>
*   **Recuperatorio 12/07/2022 (Punto 4.a):** Similarmente, plantea $y'' + 9y = a + b \cos(\omega t)$ con $|\omega| \neq 3$.
*  **Desafío:** No puedes simplemente sumar números; debes trabajar con expresiones como $y_p(t) = \frac{a}{k^2 - \omega^2} \cos(\omega t)$, lo que requiere mucha precisión en el manejo de las constantes.

### **2. Interpretación del Modelo Físico y Fenómenos**
Varios exámenes no terminan con la solución matemática, sino que preguntan qué representa esa ecuación en el mundo real.
*   **Evaluación 02/12/2025 (Punto 4.b):** Tras dar la ecuación, pregunta: **"¿Qué sistemas podría modelar esta ecuación?"**.
*   **Actividad de Repaso Resuelta:** Indica que ecuaciones de segundo orden con términos de fricción ($y'$) representan **osciladores armónicos amortiguados** [2.a]. 
*   **Desafío:** Debes ser capaz de identificar si el sistema es subamortiguado, sobreamortiguado o crítico basándote en las raíces de la ecuación característica.

### **3. Análisis Asintótico: Comportamiento para $t \gg 1$**
Este es un requisito que aparece con frecuencia y que requiere entender la física de la solución (transitorios vs. estado estacionario).
*   **Evaluación 03/12/2024 (Punto 4.a y 4.b):** Pide hallar la solución y luego **"describir su comportamiento para $t \gg 1$"** tanto para una EDO como para un sistema.
*   **Flotante 15/02/2024 (Punto 4.a.ii):** Incluye el mismo requisito tras resolver una EDO no homogénea.
*   **Desafío:** Para resolverlo, debes identificar que los términos con exponenciales negativas ($e^{-at}$) tienden a cero cuando el tiempo crece, dejando solo la solución particular o el término constante.

### **4. Casos de Resonancia Específicos**
La resonancia ocurre cuando la frecuencia de la fuente externa coincide con la frecuencia natural del sistema, lo que cambia la forma de la propuesta para la solución particular.
*   <mark>**Evaluación ✅01/07/2025 (Punto 4.c):** Pregunta qué sucede si $\omega = 2$ en la ecuación anterior y con qué fenómeno se relaciona.</mark>
*   <mark>**Recuperatorio ✅11/07/2023 (Punto 3.a.ii):** (En este examen las EDO están en el punto 3). Pide la forma de la solución si $\omega = 5$ y si esta permanece acotada.</mark>
*   **Desafío:** Debes notar que la solución ya no es un simple seno/coseno, sino que aparece un factor $t$ (ej. $t \sin(\omega t)$), lo que significa que la **amplitud crece linealmente** con el tiempo, un fenómeno físicamente peligroso.

### **5. Sistemas no Homogéneos con Términos Mixtos**
Resolver un sistema de EDOs donde el término no homogéneo tiene varias funciones combinadas o letras:
*   **Evaluación 02/12/2025 (Punto 4.a):** El sistema incluye un término $ae^t$.
*   **Flotante 15/02/2024 (Punto 4.a.ii):** Incluye una fuente de la forma $B + Ce^{-t}$.
*   **Desafío:** Esto te obliga a usar el **Principio de Superposición**, hallando una solución particular para cada parte del término no homogéneo y luego sumándolas.

**Consejo para tu examen:** Si el Punto 4 te pide el "comportamiento para $t \gg 1$", fijate bien en los signos de los exponentes de tus soluciones. Si son negativos, esos términos "mueren" y solo sobrevive la solución particular o constante.

# Punto 5)
Al analizar los ejercicios del **Punto 5** en los exámenes del Módulo II (centrados en **Series de Fourier y Ecuaciones Diferenciales en Derivadas Parciales**), se observan patrones donde la cátedra introduce desafíos que van más allá del cálculo mecánico de integrales.

Aquí te detallo los que presentan una vuelta de tuerca o un desafío extra:

### **1. La "Trampa" de la Condición Inicial Constante ($U(x,0) = k$)**
Es muy común que el punto **5.b** pida resolver la ecuación de difusión. Lo "habitual" es que la condición inicial sea una función seno o coseno, lo que hace el cálculo casi inmediato. Sin embargo, en varios exámenes la condición es un número constante:
*   <mark>Ejemplos:** Parcial del **✅01/07/2025** ($U(x,0) = 1$) [5.b], **25/06/2024** ($U(x,0) = 2$) [5.b] y **✅11/07/2023** ($U(x,0) = -1/2$) [5.b].</mark>
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
*   **Ejemplo:** Parcial **✅01/07/2025** y **20/02/2020**. Piden la convergencia en $x = 3/2$ o $x = 3\pi/2$ cuando el intervalo original es $[-1,1]$ o $[-\pi, \pi]$ [5.a, 5.a].
*   **El desafío:** Requiere aplicar correctamente el **Teorema de Dirichlet** y entender la **extensión periódica**. Si el punto está en una discontinuidad de la extensión, la serie converge al punto medio de los límites laterales ($[f(x^+) + f(x^-)]/2$). Es un error común olvidar que la serie "repite" el comportamiento del intervalo base.

### **5. El Uso de Parámetros en lugar de Números**
*   **Ejemplo:** Parcial **03/12/2024** (Punto 5.b). Incluye la constante de difusión como $\alpha > 0$ sin darle un valor numérico.
*   **El desafío:** Obliga a arrastrar la variable $\alpha$ en toda la resolución de la EDP, lo que pone a prueba la precisión algebraica del alumno para no perder constantes en los exponentes de la solución final.

**Resumen:** Para lucirte en el Punto 5, no solo practiques integrales; asegurate de saber cómo se comporta la serie en los **saltos de la función** y cómo "mueren" los términos de mayor orden con el **paso del tiempo**.
