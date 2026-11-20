# Prompt de corrección: iteración 3 (última permitida)

Enviado en el mismo chat de Gemini. Cita los dos errores restantes (CL-10, SEC-8) y acepta CU-2 como limitación de Mermaid.

- Respuesta (sin editar): [`../respuestas-gemini/iteracion3_respuesta.md`](../respuestas-gemini/iteracion3_respuesta.md)
- Código Mermaid: [`../mermaid/iteracion3/`](../mermaid/iteracion3/) · Renders: [`../renders/iteracion3/`](../renders/iteracion3/)

```text
Aplicaste correctamente todas las correcciones de la iteración anterior. Acepto tu explicación sobre CU-2: el flowchart de Mermaid no permite dibujar el triángulo hueco de la generalización, así que lo documentaré como limitación de la herramienta.

En esta revisión encontré dos errores más. Corrígelos sin modificar ningún otro elemento.

CL-10. En el diagrama de clases, Contenedor "1" --> "0..*" AlertaOperativa : se refiere a tiene la navegabilidad invertida respecto de su etiqueta: es la alerta la que se refiere al contenedor (necesita saber qué contenedor hay que vaciar), no al revés. Debe ser AlertaOperativa "0..*" --> "1" Contenedor : se refiere a.

SEC-8. En el diagrama de secuencia, 1.1.2: crear() y 2.3.1: crear() son mensajes de creación, pero :Deposito y :AlertaOperativa tienen línea de vida desde el inicio del diagrama, como si existieran antes de crearse. En UML, la línea de vida de un objeto creado empieza en el mensaje de creación, marcado como «create». Usa "create participant" de Mermaid justo antes de cada mensaje de creación y etiqueta ambos mensajes como «create» (por ejemplo, 1.1.2: «create» crear()). En el diagrama de colaboración, marca esos dos mensajes también como «create», con la misma numeración.

Devuelve el código completo de los diagramas de clases, secuencia y colaboración, cada uno en su propio bloque ```mermaid, con la lista de cambios por ID al final.
```
