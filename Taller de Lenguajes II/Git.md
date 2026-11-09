# Comandos GIT

## Explicacion de GIT general

- ***Qué es git?***
    - Herramienta free y open source para el versionado de código
    - Guarda el historial completo de cambios en un proyecto; permite revertir cambios o volver a versiones anteriores.
- ***Qué es un commit?***
    - Registra el estado del proyecto en un momento dado
    - Al desarrollar alguna funcionalidad, crear un commit registrando estos cambios
    - Cada commit tiene autor, fecha, mensaje e identificador único
- ***Qué es una rama?***
    - Línea de trabajo independiente dentro del proyecto.
    - Cada rama tiene su historial de cambios (commits) → asegura poder trabajar en conjunto sin pisarse
    - **Por lo general: NUEVA RAMA POR CADA funcionalidad.**
    - Al terminar: merge para incorporar los cambios de la rama nueva a la rama principal de desarrollo

Comandos

- git clone
- git status
- git add Main.java
- git commit -m “Mi primer commit”
- git push
- git pull
- git switch -c nueva-rama
- git switch main
- git merge nueva rama
- git log
- git mv arcivoAMover “carpetaDestino”/
