# Análisis del Caso de Estudio: UcabRecicla Express (Versión Final)

A continuación se presentan los diagramas UML corregidos, garantizando la consistencia entre ellos y el cumplimiento estricto de la notación UML 2.5 y la sintaxis de Mermaid.

### 1. Diagrama de Casos de Uso

```mermaid
flowchart LR
    %% Actores
    U((Usuario No Autenticado))
    UA((Usuario Autenticado))
    PL((Personal Logístico))
    
    %% Generalización entre actores (CU-2)
    UA -- "generalización" --> U

    %% Frontera del sistema
    subgraph Frontera [UcabRecicla Express]
        UC1([Consultar contenedores activos])
        UC2([Registrar declaración de depósito])
        UC4([Generar reporte de fraude])
        UC5([Verificar foto-evidencia])
        UC7([Emitir alerta operativa de vaciado])
    end

    %% Asociaciones Actor - Caso de Uso
    U --- UC1
    UA --- UC2
    PL --- UC7

    %% Relaciones Include y Extend
    UC2 -. "«include»" .-> UC5
    UC4 -. "«extend» <br> [cantidad > 10]" .-> UC2
    UC7 -. "«extend» <br> [acumulado >= 30]" .-> UC2
```

### 2. Diagrama de Clases

```mermaid
classDiagram
    class Material {
        <<enumeration>>
        PLASTICO
        ALUMINIO
        CARTON
    }

    class Usuario {
        -idUsuario: String
        +consultarContenedores() void
    }

    class UsuarioAutenticado {
        +seleccionarPuntoVerde() void
        +registrarDeposito(cantidad: int, material: Material, foto: String) void
    }
    Usuario <|-- UsuarioAutenticado

    class PersonalLogistico {
        -idPersonal: String
        +recibirNotificacionVaciado() void
    }

    class PuntoVerde {
        -idPuntoVerde: String
        -ubicacion: String
    }

    class Contenedor {
        -idContenedor: String
        -material: Material
        -capacidadMaxima: int = 30
        -nivelLlenado: int
        +actualizarInventario(cantidad: int) int
        +verificarCapacidad() boolean
    }

    class Deposito {
        -idTransaccion: String
        -cantidadEnvases: int
        -fotoEvidencia: String
        +confirmarDeposito() void
    }

    class ReporteFraude {
        -cantidadDeclarada: int
    }

    class AlertaOperativa {
        -idAlerta: String
        +notificarPersonal() void
    }

    class AppUcabRecicla {
        +registrarDeposito(cantidad: int, material: Material, foto: String) void
    }

    class ControladorDepositos {
        -MAX_ENVASES_FRAUDE: int = 10
        +procesarDeclaracion(cantidad: int, material: Material, foto: String) void
        +evaluarFiltroFraude(cantidad: int) boolean
        +confirmacionVerificacion(resultado: boolean) void
    }

    class ServicioFotoEvidencia {
        +verificarEvidenciaAsync(foto: String) void
    }

    class ConsolaAdministracion {
        +dispararEventoAlerta(idContenedor: String, mensaje: String) void
    }

    %% Relaciones Estructurales
    PuntoVerde "1" *-- "1..*" Contenedor : contiene
    Contenedor "1" <-- "*" Deposito : se registra en
    Contenedor "1" --> "0..*" AlertaOperativa : se refiere a
    AlertaOperativa "*" --> "1" PersonalLogistico : notifica a
    Deposito "*" --> "1" Material : clasifica
    UsuarioAutenticado "1" --> "*" Deposito : realiza
    
    %% Relaciones de Reporte de Fraude
    ControladorDepositos "1" --> "0..*" ReporteFraude : genera
    ReporteFraude "*" --> "1" UsuarioAutenticado : registra a
    
    %% Dependencias de arquitectura y secuencia con multiplicidad
    AppUcabRecicla "1" --> "1" ControladorDepositos : invoca
    ControladorDepositos "1" --> "*" Deposito : crea y gestiona
    ControladorDepositos "1" --> "1" ServicioFotoEvidencia : solicita validación
    ControladorDepositos "1" --> "1" ConsolaAdministracion : alerta a
    ControladorDepositos "1" --> "*" Contenedor : actualiza
    ConsolaAdministracion "1" --> "*" AlertaOperativa : crea
```

### 3. Diagrama de Secuencia

```mermaid
sequenceDiagram
    actor UA as :UsuarioAutenticado
    participant APP as :AppUcabRecicla
    participant CTRL as :ControladorDepositos
    participant DEP as :Deposito
    participant VF as :ServicioFotoEvidencia
    participant CONT as :Contenedor
    participant CONS as :ConsolaAdministracion
    participant ALERT as :AlertaOperativa
    actor PL as :PersonalLogistico

    Note over UA, PL: Precondición: usuario autenticado, punto verde y contenedor activos, foto adjunta y acumulado previo del contenedor = 25.

    UA->>APP: 1: registrarDeposito(5, material, foto)
    activate APP
    
    APP->>CTRL: 1.1: procesarDeclaracion(5, material, foto)
    activate CTRL
    
    %% Autollamada - Filtro Anti-Fraude
    CTRL->>CTRL: 1.1.1: evaluarFiltroFraude(5)
    Note right of CTRL: Cantidad válida (5 <= 10).
    
    %% Creación de la entidad
    CTRL->>DEP: 1.1.2: crear()
    activate DEP
    DEP-->>CTRL: (creado)
    deactivate DEP
    
    %% Llamada Asíncrona
    CTRL-)VF: 1.1.3: «asíncrono» verificarEvidenciaAsync(foto)
    activate VF
    Note right of CTRL: Llamada no bloqueante
    
    %% Retornos de estado sin numeración (COL-2)
    CTRL-->>APP: estado := "Depósito en verificación"
    deactivate CTRL
    APP-->>UA: estado := "Evidencia enviada. Su depósito se está verificando."
    deactivate APP

    Note over VF, CTRL: Transcurren ~1,5 segundos
    
    %% Retorno Asíncrono llamando al método correcto de CTRL (SEC-1)
    VF-)CTRL: 2: «asíncrono» confirmacionVerificacion(OK)
    deactivate VF
    activate CTRL
    
    CTRL->>DEP: 2.1: confirmarDeposito()
    activate DEP
    DEP-->>CTRL: (confirmado)
    deactivate DEP
    
    CTRL->>CONT: 2.2: nuevoAcumulado := actualizarInventario(5)
    activate CONT
    CONT-->>CTRL: (retorna 30)
    deactivate CONT
    
    %% Fragmento de control (Alerta) con sintaxis correcta (SEC-7)
    opt nuevoAcumulado >= 30
        CTRL->>CONS: 2.3: dispararEventoAlerta(idContenedor, "Vaciado Urgente")
        activate CONS
        
        CONS->>ALERT: 2.3.1: crear()
        activate ALERT
        
        %% Método correcto del actor Logístico (SEC-1)
        ALERT->>PL: 2.3.2: recibirNotificacionVaciado()
        activate PL
        PL-->>ALERT: (notificado)
        deactivate PL
        
        ALERT-->>CONS: (creada)
        deactivate ALERT
        
        CONS-->>CTRL: (alertaRegistrada)
        deactivate CONS
    end
    
    deactivate CTRL
```

### 4. Diagrama de Colaboración (Comunicación)

```mermaid
flowchart LR
    %% Nodos con notación de instancia :Clase
    UA((:UsuarioAutenticado))
    APP[:AppUcabRecicla]
    CTRL[:ControladorDepositos]
    DEP[:Deposito]
    VF[:ServicioFotoEvidencia]
    CONT[:Contenedor]
    CONS[:ConsolaAdministracion]
    ALERT[:AlertaOperativa]
    PL((:PersonalLogistico))

    %% Enlaces con retornos embutidos en la asignación del mensaje
    UA -- "1: estado := registrarDeposito(5, material, foto) ▶" --- APP
    
    APP -- "1.1: estado := procesarDeclaracion(5, material, foto) ▶" --- CTRL
    
    CTRL -- "1.1.1: evaluarFiltroFraude(5) ↺" --- CTRL
    
    CTRL -- "1.1.2: crear() ▶<br>2.1: confirmarDeposito() ▶" --- DEP
    
    CTRL -- "1.1.3: «asíncrono» verificarEvidenciaAsync(foto) ▶<br>◀ 2: «asíncrono» confirmacionVerificacion(OK)" --- VF
    
    CTRL -- "2.2: nuevoAcumulado := actualizarInventario(5) ▶" --- CONT
    
    CTRL -- "2.3: [nuevoAcumulado >= 30] dispararEventoAlerta(idContenedor, 'Vaciado Urgente') ▶" --- CONS
    
    CONS -- "2.3.1: crear() ▶" --- ALERT
    
    ALERT -- "2.3.2: recibirNotificacionVaciado() ▶" --- PL

    %% Estilos
    classDef object fill:#f9f9f9,stroke:#333,stroke-width:2px;
    class APP,CTRL,DEP,VF,CONT,CONS,ALERT object;
```

---

### Lista de Cambios y Correcciones Realizadas

*   **CU-2**: Se actualizó el texto sobre la línea a `"generalización"` sin usar el estereotipo. (Nota: los diagramas `flowchart` de Mermaid no soportan la línea de generalización con el triángulo hueco como el `classDiagram`; por tanto se representa con línea sólida y la etiqueta explícita tal cual solicitaste).
*   **SEC-1**: 
    * Se cambió el mensaje enviado al Personal Logístico a `2.3.2: recibirNotificacionVaciado()` en la secuencia y colaboración.
    * Se agregó la operación pública `+confirmacionVerificacion(resultado: boolean) void` a la clase `ControladorDepositos` para dar coherencia al mensaje asíncrono de retorno `2`.
*   **COL-2**: Se eliminó la numeración tipo "mensaje nuevo" en los valores de retorno. En el diagrama de secuencia las respuestas asíncronas son simples flechas `-->>` con texto; en el de colaboración se embutió la respuesta como una asignación (`1.1: estado := procesarDeclaracion(...)`).
*   **SEC-6**: Se corrigió el orden temporal. Ahora `1.1.1` es la evaluación de fraude (`evaluarFiltroFraude`) para validar los datos antes del registro, y `1.1.2` pasó a ser la instanciación de la entidad `Deposito` (`crear()`). Se renumeró idénticamente en colaboración.
*   **CL-7**: Se agregaron las asociaciones faltantes en el diagrama de clases (`ControladorDepositos --> Contenedor` y `ConsolaAdministracion --> AlertaOperativa`). Adicionalmente, el vínculo entre `Contenedor` y `AlertaOperativa` se corrigió de "genera" a "se refiere a".
*   **CL-8**: Se agregaron multiplicidades en ambos extremos a las 6 relaciones de dependencia/arquitectura indicadas (`"1" --> "1"`, `"1" --> "*"`, `"1" --> "0..*"`).
*   **CL-9**: El `ReporteFraude` ahora lo "genera" el `ControladorDepositos` (0..*) y el reporte "registra a" un (1) `UsuarioAutenticado`. Se eliminó el `idReporte` y el vínculo con el Depósito; se agregó el atributo de negocio `-cantidadDeclarada: int` a la clase de reporte.
*   **SEC-7**: Se eliminaron los corchetes dobles de la guarda en Mermaid mediante la sintaxis nativa `opt nuevoAcumulado >= 30`.
*   **COL-5**: Se ajustaron los parámetros del método de alerta en el diagrama de colaboración para que fueran exactamente `'Vaciado Urgente'`, haciendo espejo literal con la llamada de la secuencia.