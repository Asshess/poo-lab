# Prompt de corrección: iteración 1

Enviado en el mismo chat de Gemini, después de la v1. Cita 21 errores con su ubicación; los IDs (CU-1, CL-1, SEC-1, COL-1…) son los de la matriz comparativa del [informe](../../informe/informe.md).

- Respuesta (sin editar): [`../respuestas-gemini/iteracion1_respuesta.md`](../respuestas-gemini/iteracion1_respuesta.md)
- Código Mermaid: [`../mermaid/iteracion1/`](../mermaid/iteracion1/) · Renders: [`../renders/iteracion1/`](../renders/iteracion1/)

```text
Revisé los 4 diagramas que generaste contra el caso y contra la notación UML 2.5. Encontré los errores listados abajo, cada uno con su ubicación. Corrígelos todos.

Restricciones:
- No modifiques lo que ya está correcto.
- No agregues actores, clases, atributos ni mensajes que no se deriven del caso.
- Usa los mismos participantes, nombres de métodos y numeración de mensajes en la secuencia y en la colaboración, y que esos participantes y métodos existan en el diagrama de clases.
- El código debe renderizar sin errores en la versión actual de Mermaid Live.

Errores en el diagrama de casos de uso:
CU-1. En las 5 relaciones include/extend (por ejemplo, UC2 -. "<<include>>" .-> UC3), Mermaid Live muestra la etiqueta como "<>" porque interpreta <<include>> como una etiqueta HTML: en el render no se distingue include de extend. Usa «include» / «extend» (comillas angulares) o &lt;&lt;include&gt;&gt;.
CU-2. En UA -. "Es un" .-> U, la generalización entre actores está dibujada como flecha punteada con el texto "Es un", igual que un include/extend. Debe verse como generalización (línea continua, etiquetada como generalización) y distinguirse de las dependencias.
CU-3. Las asociaciones actor–caso de uso (U --> UC1, UA --> UC2, PL --> UC7, PL --> UC4) tienen punta de flecha; en UML la asociación entre actor y caso de uso es una línea sin punta (---).
CU-4. En PL --> UC4, Personal Logístico está asociado a "Generar reporte de fraude". Según el caso, el reporte de fraude se emite en la consola de auditoría y el Personal Logístico solo recibe la alerta de vaciado. Elimina esa asociación.
CU-5. "Aplicar filtro anti-fraude" (UC3) y "Actualizar inventario del contenedor" (UC6) son pasos internos del sistema, no objetivos de un actor (descomposición funcional). Haz que "Generar reporte de fraude" extienda directamente a "Registrar declaración de depósito" con la condición [cantidad > 10], y que "Emitir alerta operativa de vaciado" extienda a ese mismo caso con [acumulado >= 30]. Conserva "Verificar foto-evidencia" como «include».
CU-6. En UC7 -. "<<extend>> <br> [Capacidad >= 30]" .-> UC6, la condición compara la capacidad; lo que se evalúa es el acumulado (nivel de llenado) del contenedor: [acumulado >= 30].

Errores en el diagrama de clases:
CL-1. No existe la clase Contenedor: PuntoVerde tiene idContenedor, capacidadMaxima y nivelLlenado, y se pierde el tipo de material que recibe cada contenedor. Separa PuntoVerde (nombre, ubicación) de Contenedor (material: Material, capacidadMaxima, nivelLlenado, actualizarInventario, verificarCapacidad), con composición PuntoVerde "1" *-- "1..*" Contenedor. El depósito se registra en un Contenedor.
CL-2. Faltan las clases de los actores del diagrama de casos de uso: no existen Usuario (no autenticado) ni PersonalLogistico, aunque AlertaOperativa.notificarPersonal() notifica al personal logístico. Agrega la generalización Usuario <|-- UsuarioAutenticado, la clase PersonalLogistico y su asociación con AlertaOperativa, con multiplicidades.
CL-3. El caso exige emitir un reporte de intento de fraude en la consola de auditoría, pero no hay una clase ReporteFraude, y el límite de 10 envases no aparece en ninguna parte del modelo (evaluarFiltroFraude() no recibe la cantidad ni hay constante). Agrégalos.
CL-4. Inventaste atributos que el caso no menciona: credenciales, estadoProceso y fecha en Deposito, y tipoAlerta y fechaGeneracion en AlertaOperativa. Elimínalos (puedes mantener los identificadores).
CL-5. PuntoVerde "1" *-- "*" AlertaOperativa es una composición, pero la alerta es un evento que emite el contenedor al llenarse, no una parte suya. Debe ser una asociación Contenedor "1" --> "0..*" AlertaOperativa. Quita también el texto "(Composición)" de la etiqueta.
CL-6. Las etiquetas de las relaciones (: "realiza", : "clasifica"…) se muestran con las comillas literales en el render: quítalas. Además, los atributos usan estilo Java (-String idUsuario) y los métodos estilo UML (cantidad: int). Unifica con la notación UML: -idUsuario: String.

Errores en el diagrama de secuencia:
SEC-1. No es coherente con el diagrama de clases: App UcabRecicla, Controlador Depósitos, Servicio FotoEvidencia y Consola Administración no existen como clases, y Deposito y AlertaOperativa, que sí existen, no participan. Los métodos también tienen otros nombres (sumarInventario frente a actualizarInventario; aplicarFiltroFraude frente a evaluarFiltroFraude). Además, el depósito nunca se crea ni se confirma (confirmarDeposito). Haz que los participantes y mensajes correspondan a clases y métodos del diagrama de clases, agregando a ese diagrama las clases de control o servicio que necesites.
SEC-2. Los mensajes no están numerados, así que no se puede comprobar que coincidan con la colaboración. Numera explícitamente cada mensaje con la misma numeración jerárquica que el diagrama de colaboración.
SEC-3. VF->>CTRL: confirmacionVerificacion(OK) usa ->> (síncrono), pero es la respuesta de una llamada asíncrona: usa -). Además, "activate VF" está después de la nota de 1,5 s; el servicio debe activarse al recibir verificarEvidenciaAsync y permanecer activo durante la verificación.
SEC-4. La nota de precondición mezcla datos de entrada (cantidad=5) y reglas (límite contenedor=30), y no dice que el contenedor ya tenía 25 unidades, que es lo que hace que nuevoAcumulado = 30. Las precondiciones deben ser estados previos: usuario autenticado, punto verde y contenedor activos, foto adjunta y acumulado previo del contenedor = 25.
SEC-5. Dentro del opt [nuevoAcumulado >= 30], la alerta termina en la Consola Administración y nunca llega al Personal Logístico, que el caso exige notificar. Agrega esa notificación.

Errores en el diagrama de colaboración:
COL-1. No es la misma interacción que la secuencia: faltan la autollamada aplicarFiltroFraude(5) del controlador (enlace reflexivo), el retorno estado "Depósito en verificación" a la App y el mensaje de la App al usuario. Agrégalos con la numeración de la secuencia.
COL-2. En el enlace CTRL—PV, "2.1.1: retorna(nuevoAcumulado=30)" numera un retorno como si PuntoVerde enviara un mensaje nuevo anidado en 2.1. Representa el valor de retorno dentro del propio mensaje 2.1 (por ejemplo, 2.1: nuevoAcumulado := actualizarInventario(5)).
COL-3. En "1.1.1: [asíncrono] verificarEvidencia(foto)", los corchetes son notación de guarda (condición). Indica la asincronía de otra forma (por ejemplo, con «asíncrono» o una nota), sin corchetes.
COL-4. Los nombres de los objetos no siguen la notación de instancia :Clase y no coinciden con la secuencia (Usuario frente a Usuario Autenticado; Consola Admin frente a Consola Administración). Usa exactamente los mismos participantes con la forma :Clase.

Responde con el código completo y corregido de los 4 diagramas, cada uno en su propio bloque ```mermaid, y al final una lista que indique, para cada ID (CU-1 … COL-4), qué cambiaste.
```
