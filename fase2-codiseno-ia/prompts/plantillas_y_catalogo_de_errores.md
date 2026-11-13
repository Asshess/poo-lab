# Método de corrección: plantillas y catálogo de errores

Guía que usó el equipo para redactar los prompts de corrección (máximo 3 iteraciones).

**Reglas del enunciado**
- Cada error se cita **con su ubicación** (ej.: "en la secuencia, falta el retorno del mensaje 4").
- Máximo **3 iteraciones**. En cada una se registra: prompt, código, render y errores que persisten.
- Si tras la 3.ª quedan errores, se documentan como **limitación de la IA** (apartado 6 del [informe](../../informe/informe.md)).

**Flujo**
1. Renderizar la v1 y llenar la columna "Errores de la IA" de la matriz (informe, apartado 5).
2. Armar el prompt de la iteración 1 con la plantilla A, usando las frases del catálogo (sección C) que apliquen **y adaptando el nombre o número exacto** que usó la IA.
3. Enviarlo **en el mismo chat** de la v1. Guardar la respuesta sin editar en `respuestas-gemini/` y los renders en `renders/iteracionN/`.
4. Revisar qué se corrigió, qué persiste y qué error **nuevo** apareció. Si queda algo, hacer la iteración 2 con la plantilla B, y lo mismo para la 3.

---

## A. Plantilla — Iteración 1

```text
Revisé los 4 diagramas que generaste contra el caso y contra la notación UML 2.5. Encontré los errores listados abajo, cada uno con su ubicación. Corrígelos todos.

Restricciones:
- No modifiques lo que ya está correcto.
- No agregues actores, clases, atributos ni mensajes que no se deriven del caso.
- Mantén los mismos participantes y la misma numeración de mensajes entre la secuencia y la colaboración.
- El código debe renderizar sin errores en la versión actual de Mermaid Live.

Errores en el diagrama de casos de uso:
1. [ubicación] — [qué está mal] — [qué se espera]
2. ...

Errores en el diagrama de clases:
1. ...

Errores en el diagrama de secuencia:
1. ...

Errores en el diagrama de colaboración:
1. ...

Responde con el código completo y corregido de cada diagrama afectado, cada uno en su propio bloque ```mermaid, y al final una lista que indique, para cada error numerado, qué cambiaste.
```

## B. Plantilla — Iteraciones 2 y 3

```text
Aplicaste parte de las correcciones. Estado actual:

Corregidos correctamente (no los toques): [lista corta, ej.: CU-1, CL-3, SEC-2]

Errores que PERSISTEN (cita exacta de la iteración anterior):
1. [diagrama] — [ubicación en el código nuevo] — [qué sigue mal] — [qué se espera]

Errores NUEVOS introducidos por tu corrección:
1. [diagrama] — [ubicación] — [qué rompiste] — [qué se espera]

Devuelve solo los diagramas afectados, completos, cada uno en su bloque ```mermaid, con la lista de cambios al final. No modifiques ningún otro elemento.
```

> Consejo para la iteración 3: si un error sintáctico persiste (no renderiza), muestra a la IA el **mensaje de error literal** que da Mermaid Live y la línea donde ocurre.

---

## C. Catálogo de errores típicos (frases listas para citar)

Usa solo las que **realmente** aparezcan en la respuesta de la IA, y cambia los nombres/números por los que ella usó. Marca cada una como **sintáctico** (no renderiza / notación inválida) o **semántico** (renderiza pero modela mal) y su gravedad en la matriz.

### Casos de uso (CU)
| Id | Frase para el prompt | Tipo · gravedad sugerida |
|---|---|---|
| CU-1 | "La relación entre «Registrar declaración» y «Verificar foto-evidencia» está etiquetada como extend, pero la verificación es obligatoria en todo depósito: debe ser «include», con la flecha punteada desde el caso base hacia el incluido." | Semántico · alta |
| CU-2 | "«Notificar alerta de llenado» solo ocurre si el acumulado ≥ 30: debe ser «extend» y la flecha debe ir desde el caso extendido (la alerta) hacia el caso base, no al revés." | Semántico · alta |
| CU-3 | "El rechazo por fraude (cantidad > 10) es un comportamiento condicional: modela «Reportar intento de fraude» como «extend» del caso de registro, con la condición como etiqueta." | Semántico · media |
| CU-4 | "Falta el actor Personal logístico (receptor de la alerta en la consola de administración) / falta el actor que consulta sin sesión (Visitante/Usuario no autenticado)." | Semántico · alta |
| CU-5 | "Los actores están dentro del subgraph del sistema: deben quedar fuera de la frontera; solo los casos de uso van dentro." | Semántico · media |
| CU-6 | "Usuario autenticado debe heredar de Usuario no autenticado (generalización entre actores), para poder también consultar contenedores." | Semántico · baja |
| CU-7 | "La etiqueta `<<include>>` en la línea N rompe el render: usa la etiqueta entre comillas (`-. "«include»" .->`)." | Sintáctico · alta |
| CU-8 | "Incluiste pasos internos del sistema (p. ej. «Actualizar inventario») como casos de uso iniciados por el usuario; son acciones internas, no objetivos del actor." | Semántico · media |

### Clases (CL)
| Id | Frase para el prompt | Tipo · gravedad sugerida |
|---|---|---|
| CL-1 | "Los miembros de la clase X no tienen visibilidad: todos los atributos deben ser `-` y los métodos `+`." | Semántico · media |
| CL-2 | "La relación entre X e Y no tiene multiplicidad en uno/ambos extremos: agrega, por ejemplo, `PuntoVerde "1" *-- "1..*" Contenedor`." | Semántico · alta |
| CL-3 | "PuntoVerde y Contenedor deben relacionarse por composición (el contenedor no existe fuera de su Punto Verde), no por asociación simple." | Semántico · media |
| CL-4 | "Inventaste los atributos [lista, ej.: email, telefono, fechaNacimiento] que el caso no menciona; elimínalos o justifícalos como supuesto." | Semántico · baja |
| CL-5 | "Falta la clase Declaración/Depósito con cantidad, material, foto-evidencia y punto verde, que es la entidad central del escenario." | Semántico · alta |
| CL-6 | "El tipo de material debe ser una enumeración (`<<enumeration>> TipoMaterial` con PLASTICO, ALUMINIO, CARTON), usada como tipo del atributo, no texto libre." | Semántico · baja |
| CL-7 | "Faltan los límites de negocio (10 envases por transacción, 30 unidades por contenedor) como atributos/constantes o en el método de validación." | Semántico · media |
| CL-8 | "La herencia de Estudiante/Profesor/Visitante usa una flecha incorrecta: la generalización es `Usuario <|-- Estudiante`." | Sintáctico/semántico · media |
| CL-9 | "La multiplicidad `X` en la línea N no es válida en UML (usa 0..1, 1, 0..*, 1..*)." | Sintáctico · media |

### Secuencia (SEC)
| Id | Frase para el prompt | Tipo · gravedad sugerida |
|---|---|---|
| SEC-1 | "El mensaje N `verificarFoto()` está como síncrono (`->>`); el caso dice que es asíncrono y el usuario no queda bloqueado: usa `-)` y devuelve el control al usuario antes de la confirmación." | Semántico · alta |
| SEC-2 | "Falta representar el retardo de ~1,5 s de la verificación (Note o restricción de duración `{≈1,5 s}` entre el envío y la confirmación)." | Semántico · alta |
| SEC-3 | "Falta la alerta de llenado: tras actualizar el inventario, agrega `opt [acumulado >= 30]` con el mensaje de alerta a la consola de administración / personal logístico." | Semántico · alta |
| SEC-4 | "Falta el retorno del mensaje N (`-->>`), p. ej. el resultado de `validarCantidad()`/`actualizarInventario()`." | Semántico · media |
| SEC-5 | "Faltan las precondiciones en una `Note` al inicio (usuario autenticado, punto verde activo, foto adjunta)." | Semántico · media |
| SEC-6 | "El escenario es el exitoso: la rama de fraude (cantidad > 10) no debe ser el flujo principal; si la muestras, que sea un `alt` cuya rama exitosa es [cantidad <= 10]." | Semántico · media |
| SEC-7 | "Las activaciones están desbalanceadas (`activate` sin `deactivate`, o `+`/`-` mal pareados) en la línea N." | Sintáctico · alta |
| SEC-8 | "El incremento del inventario lo ejecuta el participante X, pero según el caso corresponde al Contenedor/PuntoVerde." | Semántico · media |

### Colaboración (COL)
| Id | Frase para el prompt | Tipo · gravedad sugerida |
|---|---|---|
| COL-1 | "Hiciste un segundo diagrama de secuencia; un diagrama de colaboración no tiene líneas de vida: objetos como nodos y enlaces con los mensajes numerados." | Semántico · alta |
| COL-2 | "La numeración no coincide con la secuencia: el mensaje `X` es N en la secuencia y M en la colaboración." | Semántico · alta |
| COL-3 | "Falta numeración jerárquica: los mensajes anidados dentro de N deben ser N.1, N.2…" | Semántico · media |
| COL-4 | "Hay dos aristas para el mismo par de objetos A–B; debe haber un único enlace con todos sus mensajes en la etiqueta." | Semántico · baja |
| COL-5 | "Faltan participantes que sí están en la secuencia (X, Y) / sobran participantes nuevos (Z)." | Semántico · alta |
| COL-6 | "Las etiquetas no indican la dirección del mensaje; agrega `→`/`←` o el emisor en cada mensaje." | Semántico · baja |
| COL-7 | "La alerta de llenado y el mensaje asíncrono de verificación no aparecen en la colaboración aunque sí en la secuencia." | Semántico · alta |

---

## D. Registro por iteración (formato usado en el informe, apartado 6)

```markdown
### Iteración N
**Prompt enviado:** (pegar literal)
**Código Mermaid recibido:** (pegar literal, sin editar)
**Render:** `renders/iteracionN/0X_nombre.png`
**Errores corregidos:** CU-1, SEC-3, …
**Errores que persisten:** …
**Errores nuevos:** …
```
