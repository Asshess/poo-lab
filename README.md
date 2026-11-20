# POO Lab

[![CI](https://github.com/Asshess/poo-lab/actions/workflows/ci.yml/badge.svg)](https://github.com/Asshess/poo-lab/actions/workflows/ci.yml)

Laboratorios, talleres y el proyecto final de Programación Orientada a Objetos (UCAB) reunidos en un solo repositorio. Cada carpeta es un entregable independiente, con su propio README, build y documentación.

## Proyecto final

El entregable principal del curso vive en [`proyecto-final/`](proyecto-final/). Está en preparación: su README concentra el alcance, el stack y los comandos cuando existan.

| Carpeta | Contenido | Stack | Estado |
|---|---|---|---|
| [`proyecto-final`](proyecto-final/) | Proyecto final del curso | Por definir | En preparación |

## Laboratorios y talleres

| Carpeta | Contenido | Stack | Cómo ejecutar |
|---|---|---|---|
| [`lab-01-poo-basico`](lab-01-poo-basico/) | VirtualPet: clases, encapsulamiento, herencia, polimorfismo e interfaz Swing | Java + Ant | `cd lab-01-poo-basico/VirtualPet && ant` |
| [`lab-02-poo-basico-uml`](lab-02-poo-basico-uml/) | Sistema de transporte y sueldos (Ej. 1) y arquitectura UML (Ej. 5) | Java 27 + Maven/Ant | `cd lab-02-poo-basico-uml/ejercicio-1-sistema-transporte && mvn verify` |
| [`lab-03-poo-avanzado`](lab-03-poo-avanzado/) | Farmacia: genéricos, colecciones, excepciones y estructuras LIFO/FIFO | Java + Ant (NetBeans) | `cd lab-03-poo-avanzado && ant` |
| [`taller-uml`](taller-uml/) | Taller de modelado UML: UcabRecicla, fases manual y con IA | Documentación + PlantUML/Mermaid | Ver [`taller-uml/README.md`](taller-uml/README.md) |
| [`actividad-formativa-uml`](actividad-formativa-uml/) | Coffee Campus: casos de uso, clases, secuencia y colaboración | Documentación + Mermaid | Ver [`actividad-formativa-uml/README.md`](actividad-formativa-uml/README.md) |
| [`teatro`](teatro/) | Venta de entradas de teatro con herencia y JUnit 5 | Java 26 + Maven | `cd teatro/teatro && mvn test` |

## Requisitos

- JDK 27 (los proyectos Maven declaran Java 26/27).
- Maven 3.10+ y Ant 1.10+.
- Para las interfaces de Lab 1 y Lab 2, un entorno de escritorio.

## Estructura de cada proyecto

```text
<proyecto>/
├── README.md     # qué es, cómo se ejecuta, integrantes
├── src/          # código
├── docs/         # enunciado, informe, diagramas y capturas
└── ...           # pom.xml, build.xml, nbproject
```

## Colaboración

- `main` se mantiene estable; los cambios entran por ramas cortas y PR.
- Commits en formato `tipo(área): qué cambió`.
- No se versionan `target/`, `build/`, `dist/`, `.class` ni `.jar`.
- Las entregas se congelan con un tag `lab-01/v1.0`, `taller-uml/v1.0`, etc.

Lee [`CONTRIBUTING.md`](CONTRIBUTING.md) para el flujo completo y los comandos de cada proyecto.

## Historial

El repositorio reúne la historia de los proyectos que antes vivían en repos separados y en la rama `practicas` del vault de Obsidian. Se conservaron los commits y autores de cada proyecto. El proyecto final comienza su historia aquí.
