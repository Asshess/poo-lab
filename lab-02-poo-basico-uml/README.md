# Lab 02 — POO básico y UML

Dos ejercicios independientes:

- [`ejercicio-1-sistema-transporte/`](ejercicio-1-sistema-transporte/) — sistema de control de sueldos y transportes en Java (Maven/Ant, Swing + consola).
- [`ejercicio-5-arquitectura-uml/`](ejercicio-5-arquitectura-uml/) — análisis y diagramas de arquitectura.

## Ejecutar el Ejercicio 1

```bash
cd ejercicio-1-sistema-transporte
mvn clean verify
mvn exec:java -Dexec.mainClass="com.laboratorio.transporte.Main"
```

También se puede compilar con Ant:

```bash
ant clean compile
ant run
```

Requiere JDK 27 (con 21+ hay que ajustar `maven.compiler.source` y `target` en el `pom.xml`) y un entorno gráfico para los `JOptionPane`.

## Documentación

- `02_Lab_POO_Basico_UML.pdf` — enunciado.
- [`ejercicio-1-sistema-transporte/README.md`](ejercicio-1-sistema-transporte/README.md) — instrucciones detalladas del Ejercicio 1.
- `ejercicio-1-sistema-transporte/docs/` — informe técnico, diagrama de clases y capturas.
- `ejercicio-1-sistema-transporte/Informe_Tecnico_Lab2.pdf` — informe entregado.
- `ejercicio-5-arquitectura-uml/Ejercicio_5.md` — desarrollo del Ejercicio 5.
