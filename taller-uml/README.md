# Taller UML y Validación Crítica con IA: UcabRecicla Express

**Unidad curricular:** Programación Orientada a Objetos · **Docente:** Jannelly Bello

**Integrantes:** Alessandro, Jade, Sebastian

**Herramienta de IA:** Gemini · **Renderizado:** Mermaid

---

## Entregable final

📄 **[`Informe_TallerUML_UcabRecicla.pdf`](Informe_TallerUML_UcabRecicla.pdf)**: el PDF con los apartados en el orden que pide el enunciado.

La misma versión se puede leer directamente en GitHub en [`informe/informe.md`](informe/informe.md).

## Contenido (orden de la sección 6 del enunciado)

| # | Apartado | Dónde está |
|---|---|---|
| 1 | Portada (integrantes y herramienta de IA) | Primera página del PDF |
| 2 | Ambigüedades detectadas y supuestos adoptados | Informe, apartado 2 |
| 3 | Diagramas originales de la Fase 1, hechos sin IA | [`fase1-modelado-manual/`](fase1-modelado-manual/): PNG de los 4 diagramas y fuente editable `.drawio.svg` |
| 4 | Prompt inicial y respuesta de la IA (código Mermaid v1 y render) | [`prompts/01_prompt_inicial.md`](fase2-codiseno-ia/prompts/01_prompt_inicial.md) · [`respuestas-gemini/v1_respuesta.md`](fase2-codiseno-ia/respuestas-gemini/v1_respuesta.md) · [`renders/v1/`](fase2-codiseno-ia/renders/v1/) |
| 5 | Matriz de análisis comparativo | Informe, apartado 5 |
| 6 | Prompts de corrección (3 iteraciones), código, renders y diagramas finales de la IA | [`prompts/`](fase2-codiseno-ia/prompts/) · [`respuestas-gemini/`](fase2-codiseno-ia/respuestas-gemini/) · [`mermaid/`](fase2-codiseno-ia/mermaid/) · [`renders/`](fase2-codiseno-ia/renders/) |
| 7 | Conclusión reflexiva | Informe, apartado 7 |
| 8 | Coevaluación individual | Cada integrante la entrega por separado y de forma confidencial; **no forma parte de este repositorio**. |

## Resumen de resultados de la Fase 2

| Etapa | Errores revisados | Corregidos | Nuevos | Pendientes al final de la etapa |
|---|---|---|---|---|
| v1 (prompt inicial) | 21 detectados (4 sintácticos, 17 semánticos) | n/a | n/a | 21 |
| Iteración 1 | 21 citados | 18 (y 2 en parte) | 6 | 9 |
| Iteración 2 | 9 citados | 8 (y CU-2 en parte) | 2 detectados en la revisión | 3 |
| Iteración 3 | 2 citados | 2 | 0 | **1, como limitación** |

En total se identificaron **29 errores** de la IA, de los cuales solo **5 fueron sintácticos**. Quedó un único pendiente, **CU-2**: la generalización entre actores no se puede dibujar con triángulo hueco porque el `flowchart` de Mermaid no tiene esa punta. Se documentó como limitación de la IA y de la herramienta.

Diagramas finales de la IA: casos de uso de la [iteración 2](fase2-codiseno-ia/renders/iteracion2/01_casos_de_uso.png); [clases](fase2-codiseno-ia/renders/iteracion3/02_clases.png), [secuencia](fase2-codiseno-ia/renders/iteracion3/03_secuencia.png) y [colaboración](fase2-codiseno-ia/renders/iteracion3/04_colaboracion.png) de la iteración 3.

## Estructura del repositorio

```
.
├── Informe_TallerUML_UcabRecicla.pdf   ← entregable final
├── informe/
│   ├── informe.md                      ← fuente del PDF (legible en GitHub)
│   ├── estilo.css
│   └── generar_pdf.sh
├── enunciado/                          ← enunciado del taller y formulario de coevaluación (en blanco)
├── fase1-modelado-manual/              ← diagramas originales hechos sin IA
│   ├── diagramas_fase1.drawio.svg
│   └── 01_casos_de_uso.png … 04_colaboracion.png
└── fase2-codiseno-ia/
    ├── prompts/                        ← prompt inicial, 3 prompts de corrección y el método usado
    ├── respuestas-gemini/              ← respuestas completas de Gemini, sin editar
    ├── mermaid/                        ← código Mermaid de cada diagrama: v1, iteracion1, iteracion2, iteracion3
    └── renders/                        ← imagen de cada diagrama en cada versión
```

Los archivos `.mmd` se pueden pegar directamente en [mermaid.live](https://mermaid.live). Además, GitHub dibuja los bloques Mermaid de los archivos de `respuestas-gemini/`.

## Regenerar el PDF

Requiere Node.js. Desde la raíz del repositorio:

```bash
bash informe/generar_pdf.sh
```
