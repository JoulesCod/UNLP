https://github.com/JoulesCod/UNLP/releases/tag/R-info-UNLP 
<br>
(Versión 4 de R-info, se puede ver el la sección de "Releases" en mi Repo general de UNLP)

# LEAN Y REPITAN DESPUÉS DE MI:
- SI LOS ROBOTS DEPOSITAN (o buscan) ELEMENTOS EN LA MISMA ESQUINA (o si hay una MÍNIMA (MINIMAAAA) chanse que les toque en la misma esquina, por el random, por ejemplo) **VAN Y VUELVEN DEJANDO DE A UN ELEMENTO** <br>
  **VAN Y VUELVEN DEJANDO DE A UN ELEMENTO** <br>
  **VAN Y VUELVEN DEJANDO DE A UN ELEMENTO** <br>
  **VAN Y VUELVEN DEJANDO DE A UN ELEMENTO** <br>
  **VAN Y VUELVEN DEJANDO DE A UN ELEMENTO** <br>
  QUEDA CLARO? <br>
  Estan en Pos(Ca_act, Av_act), van a Pos(Ca_deposito, Av_deposito), dejan **UN** elemento, vuelven a Pos(Ca_act, Av_act), Y ASI! <br>
  Si un robot va solito a buscar cosas a una esquina, que agarre lo que necesita, si solo necesita 10 flores, que no agarre las 99999 que dejaste para que la esquina esté cargada


- SI EL ROBOT JEFE VA A LIMPIAR UNA ESQUINA/ÁREA DONDE ES **IM-PO-SI-BLE** QUE ESTEN OTROS ROBOTS EN EL MOMENTO, **NO BLOQUEEN**!!!!! <br>
  NO BLOQUEEN AL PEDO <br>
  NO BLOQUEEN AL PEDO <br>
  NO BLOQUEEN AL PEDO <br>
  si cualquier robot se va a manejar en un espacio en el que no se va a chocar con nadie, ¿PARA Q BLOQUEAS???? <br>
- AHORA, si hay una mínima chance que sí se choquen... BOLQUEEN, AUNQUE SEA UN SOLO PUNTO <br>
  ejemplo: si el recorrido de 2 robots se cruza tipo en X, dos escaleras que se cruzan en un punto, dos recorridos terminan en un mismo lugar, etc


- El orden es Pos(Avenida, Calle) --> Visualmente las verticales son Avenidas, las horizontales son calles <br>
  --> PosCa:= 1 --> y=1 | PosCa+1 --> y=2 <br>
  --> PosAv:= 1 --> x=1 | PosAV+1 --> x=2 (pensarlo como funciones) <br>
  <img width="400" height="320" alt="image" src="https://github.com/user-attachments/assets/e81315ee-28f7-4d16-a83b-b214bbedf427" />

- Tener una hoja para calcular bien las áreas raras que seguro van a haber

Ejemplos a tener en cuenta
  <img width="1031" height="1600" alt="image" src="https://github.com/user-attachments/assets/963aa89d-0c1b-41fd-9b39-40930a929a07" />

si la figura es así: <br>
o _____ o <br>
| . . . | <br>
| . . . | <br>
o _____ o <br>
y hay 4 robots donde cada uno recorre un lado, según la cátedra, son 8 áreas: cada lado (= 4 privadas) y cada esquina (= 4 parcialmente compartidas *) } 8 áreas en total <br>
*son <mark>Parcialmente compartidas</mark> porque van a haber mas de un robot allí, PERO NO AL MISMO TIEMPO (sinó se chocarían) <br>
EN ESTE EJERCICIO la cantidad de tipos de robots es 4, porque si fuera un solo tipo (aunque el código sea similar), vamos a condicionarlo (o sea: si está en la pos X --> derecha, sino --> repetir 3 dercha) y eso no nos interesa ==> NO CONDICIONAR POR LA POSICIÓN <br>

AHORA, si los robots estuvieran a la espera a que un jefe les diga que lado recorrer, ahí claramente es un solo tipo de robot que recorre (+ el tipo del jefe), y vas a tener modularizados los recorridos q le vas a dar al robot según lo que le toque
