# Análisis del Caso de Estudio: UcabRecicla Express (Versión Final)

A continuación se presentan los diagramas UML corregidos, garantizando la consistencia entre ellos y el cumplimiento estricto de la notación UML 2.5 y la sintaxis de Mermaid.

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
    AlertaOperativa "0..*" --> "1" Contenedor : se refiere a
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
    participant VF as :ServicioFotoEvidencia
    participant CONT as :Contenedor
    participant CONS as :ConsolaAdministracion
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
    create participant DEP as :Deposito
    CTRL->>DEP: 1.1.2: «create» crear()
    activate DEP
    DEP-->>CTRL: (creado)
    deactivate DEP
    
    %% Llamada Asíncrona
    CTRL-)VF: 1.1.3: «asíncrono» verificarEvidenciaAsync(foto)
    activate VF
    Note right of CTRL: Llamada no bloqueante
    
    %% Retornos de estado sin numeración
    CTRL-->>APP: estado := "Depósito en verificación"
    deactivate CTRL
    APP-->>UA: estado := "Evidencia enviada. Su depósito se está verificando."
    deactivate APP

    Note over VF, CTRL: Transcurren ~1,5 segundos
    
    %% Retorno Asíncrono llamando al método correcto de CTRL
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
    
    %% Fragmento de control (Alerta)
    opt nuevoAcumulado >= 30
        CTRL->>CONS: 2.3: dispararEventoAlerta(idContenedor, "Vaciado Urgente")
        activate CONS
        
        create participant ALERT as :AlertaOperativa
        CONS->>ALERT: 2.3.1: «create» crear()
        activate ALERT
        
        %% Método correcto del actor Logístico
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
    
    CTRL -- "1.1.2: «create» crear() ▶<br>2.1: confirmarDeposito() ▶" --- DEP
    
    CTRL -- "1.1.3: «asíncrono» verificarEvidenciaAsync(foto) ▶<br>◀ 2: «asíncrono» confirmacionVerificacion(OK)" --- VF
    
    CTRL -- "2.2: nuevoAcumulado := actualizarInventario(5) ▶" --- CONT
    
    CTRL -- "2.3: [nuevoAcumulado >= 30] dispararEventoAlerta(idContenedor, 'Vaciado Urgente') ▶" --- CONS
    
    CONS -- "2.3.1: «create» crear() ▶" --- ALERT
    
    ALERT -- "2.3.2: recibirNotificacionVaciado() ▶" --- PL

    %% Estilos
    classDef object fill:#f9f9f9,stroke:#333,stroke-width:2px;
    class APP,CTRL,DEP,VF,CONT,CONS,ALERT object;
```

---

### Lista de Cambios por ID

*   **CL-10**: En el diagrama de clases, invertí la navegabilidad de la relación para que refleje correctamente la dependencia de la alerta hacia el contenedor: `AlertaOperativa "0..*" --> "1" Contenedor : se refiere a`.
*   **SEC-8**:
    *   **Secuencia:** Eliminé a `:Deposito` y `:AlertaOperativa` de las declaraciones iniciales del diagrama para que no tengan línea de vida desde el principio. Utilicé `create participant` justo antes del llamado a `crear()` y agregué el estereotipo `«create»` en los mensajes `1.1.2` y `2.3.1`.
    *   **Colaboración:** Agregué el estereotipo `«create»` a los mensajes `1.1.2` y `2.3.1`, manteniendo coherencia exacta con la secuencia.