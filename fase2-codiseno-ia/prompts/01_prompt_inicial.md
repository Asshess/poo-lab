# Prompt inicial (Fase 2, paso 1)

Prompt enviado a **Gemini** en un chat nuevo. Contiene el enunciado narrativo del caso, sin cambios, y pide los 4 diagramas en Mermaid.

- Respuesta (sin editar): [`../respuestas-gemini/v1_respuesta.md`](../respuestas-gemini/v1_respuesta.md)
- Código Mermaid: [`../mermaid/v1/`](../mermaid/v1/) · Renders: [`../renders/v1/`](../renders/v1/)

```text
Actúa como analista de software experto en UML 2.5 y en la sintaxis de Mermaid (compatible con Mermaid Live Editor, https://mermaid.live).

A partir del siguiente caso de estudio, genera 4 diagramas UML en código Mermaid.

=== CASO DE ESTUDIO: UcabRecicla Express ===
La universidad requiere una aplicación móvil de gestión de depósitos inteligentes denominada UcabRecicla Express para auditar la integridad del proceso de reciclaje en el campus y optimizar el control operativo de los Puntos Verdes ubicados en áreas estratégicas como el Módulo de Aulas, la Feria Universitaria y el Edificio de Ingeniería.

Cualquier estudiante, profesor o visitante puede consultar, sin necesidad de iniciar sesión, la lista de contenedores activos en el sistema, visualizando el tipo de material que recibe cada uno (plástico, aluminio o cartón) y su nivel de llenado en tiempo real en relación con su capacidad total. Para realizar una declaración de reciclaje, el usuario autenticado debe seleccionar el Punto Verde de su preferencia, especificar el tipo de material e indicar la cantidad de envases a depositar. Durante la generación de la orden, el sistema aplica un filtro anti-fraude obligatorio que evalúa los datos de entrada antes de registrar el depósito; si el usuario declara una cantidad superior al límite permitido de 10 envases en una sola transacción, el sistema frena inmediatamente la operación, interrumpe el registro, emite un reporte de intento de fraude en la consola de auditoría y notifica el rechazo al usuario sin modificar los datos del sistema. Si la cantidad enviada cumple con las reglas de negocio, el servicio verifica la foto-evidencia (una foto de los envases depositados que el usuario adjunta a su declaración) de forma asíncrona: el usuario no queda bloqueado mientras espera y la verificación tarda unos 1,5 segundos en confirmarse. Transcurrido este tiempo y validado el depósito, se efectúa la actualización e incremento del inventario en el Punto Verde correspondiente. Finalmente, el sistema evalúa el nuevo acumulado del contenedor y, si este alcanza o supera su límite máximo de 30 unidades, dispara un evento de alerta operativa crítica en la consola de administración para notificar al personal logístico que el contenedor requiere un vaciado urgente.

Escenario para los diagramas de secuencia y de colaboración: Registrar Depósito con Validación Asíncrona y Notificación de Alerta por Llenado.
=== FIN DEL CASO ===

Diagramas requeridos:

1. Diagrama de casos de uso. Identifica los actores y representa los casos de uso que se desprenden del texto, dentro de la frontera del sistema. Incluye al menos una relación <<include>> y una <<extend>>. Mermaid no tiene un tipo nativo de casos de uso: usa `flowchart LR`, con un `subgraph` como frontera del sistema, los casos de uso como nodos ovalados `([...])` y las relaciones include/extend como flechas punteadas etiquetadas.

2. Diagrama de clases (`classDiagram`). Modela las entidades del dominio con sus atributos y métodos, e indica la visibilidad de cada miembro (+ público, - privado). Define la multiplicidad en ambos extremos de cada relación y aplica el tipo de relación que corresponda (asociación, composición, agregación y, si la identificas, generalización).

3. Diagrama de secuencia (`sequenceDiagram`) del flujo del escenario exitoso indicado. Decide qué participantes intervienen y cuáles son las precondiciones del escenario, y anótalas en el diagrama con `Note`. Representa los mensajes, sus retornos, las activaciones y los fragmentos de control que el escenario requiera.

4. Diagrama de colaboración (comunicación) de la misma interacción, con los mismos participantes. Mermaid no tiene un tipo nativo: usa `flowchart` con los objetos como nodos y un único enlace por cada par de objetos que se comunican. Numera explícitamente el orden cronológico de los mensajes sobre los enlaces, con numeración jerárquica cuando haya anidamiento (1, 1.1, 2…), coherente con el diagrama de secuencia.

Formato de la respuesta:
- Primero, una lista breve de: actores, entidades, reglas de negocio, ambigüedades detectadas en el texto y supuestos que adoptaste.
- Luego, cada diagrama en su propio bloque ```mermaid, precedido de su título.
- El código debe renderizar sin errores en la versión actual de Mermaid Live. Escribe entre comillas dobles las etiquetas que tengan caracteres especiales (<<, >>, :, paréntesis).
```
