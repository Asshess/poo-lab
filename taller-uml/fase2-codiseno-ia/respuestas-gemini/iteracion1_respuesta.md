# Análisis del Caso de Estudio: UcabRecicla Express (Versión Corregida)

A continuación se presentan los diagramas UML corregidos, garantizando la consistencia entre ellos y el cumplimiento de la notación UML 2.5 y la sintaxis de Mermaid.

### 1. Diagrama de Casos de Uso

```mermaid
flowchart LR
    %% Actores
    U((Usuario No Autenticado))
    UA((Usuario Autenticado))
    PL((Personal Logístico))
    
    %% Generalización entre actores (Línea continua con etiqueta que la distingue)
    UA -- "«generalization»" --> U

    %% Frontera del sistema
    subgraph Frontera [UcabRecicla Express]
        UC1([Consultar contenedores activos])
        UC2([Registrar declaración de depósito])
        UC4([Generar reporte de fraude])
        UC5([Verificar foto-evidencia])
        UC7([Emitir alerta operativa de vaciado])
    end

    %% Asociaciones Actor - Caso de Uso (Líneas sin punta)
    U --- UC1
    UA --- UC2
    PL --- UC7

    %% Relaciones Include y Extend (usando comillas angulares y yendo hacia el caso base)
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
        -idReporte: String
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
    }

    class ServicioFotoEvidencia {
        +verificarEvidenciaAsync(foto: String) void
    }

    class ConsolaAdministracion {
        +dispararEventoAlerta(idContenedor: String, mensaje: String) void
    }

    %% Relaciones
    PuntoVerde "1" *-- "1..*" Contenedor : contiene
    Contenedor "1" <-- "*" Deposito : se registra en
    Contenedor "1" --> "0..*" AlertaOperativa : genera
    AlertaOperativa "*" --> "1" PersonalLogistico : notifica a
    Deposito "1" --> "0..1" ReporteFraude : genera
    Deposito "*" --> "1" Material : clasifica
    UsuarioAutenticado "1" --> "*" Deposito : realiza
    
    %% Dependencias de arquitectura (agregadas por consistencia con interacción)
    AppUcabRecicla --> ControladorDepositos : invoca
    ControladorDepositos --> Deposito : crea y gestiona
    ControladorDepositos --> ServicioFotoEvidencia : solicita validación
    ControladorDepositos --> ConsolaAdministracion : alerta a
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
    
    CTRL->>DEP: 1.1.1: crear()
    activate DEP
    DEP-->>CTRL: (creado)
    deactivate DEP
    
    %% Autollamada - Filtro Anti-Fraude
    CTRL->>CTRL: 1.1.2: evaluarFiltroFraude(5)
    Note right of CTRL: Cantidad válida (5 <= 10).
    
    %% Llamada Asíncrona
    CTRL-)VF: 1.1.3: «asíncrono» verificarEvidenciaAsync(foto)
    activate VF
    Note right of CTRL: Llamada no bloqueante
    
    %% Retornos de estado
    CTRL-->>APP: 1.1.4: estado := "Depósito en verificación"
    deactivate CTRL
    APP-->>UA: 1.2: "Evidencia enviada. Su depósito se está verificando."
    deactivate APP

    Note over VF, CTRL: Transcurren ~1,5 segundos
    
    %% Retorno Asíncrono
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
    opt [nuevoAcumulado >= 30]
        CTRL->>CONS: 2.3: dispararEventoAlerta(idContenedor, "Vaciado Urgente")
        activate CONS
        
        CONS->>ALERT: 2.3.1: crear()
        activate ALERT
        
        ALERT->>PL: 2.3.2: notificarPersonal()
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

    %% Enlaces
    UA -- "1: registrarDeposito(5, material, foto) ▶<br>◀ 1.2: estado := Depósito en verificación" --- APP
    
    APP -- "1.1: procesarDeclaracion(5, material, foto) ▶<br>◀ 1.1.4: estado := Depósito en verificación" --- CTRL
    
    CTRL -- "1.1.1: crear() ▶<br>2.1: confirmarDeposito() ▶" --- DEP
    
    CTRL -- "1.1.2: evaluarFiltroFraude(5) ↺" --- CTRL
    
    CTRL -- "1.1.3: «asíncrono» verificarEvidenciaAsync(foto) ▶<br>◀ 2: «asíncrono» confirmacionVerificacion(OK)" --- VF
    
    CTRL -- "2.2: nuevoAcumulado := actualizarInventario(5) ▶" --- CONT
    
    CTRL -- "2.3: [nuevoAcumulado >= 30] dispararEventoAlerta(idContenedor, 'Vaciado') ▶" --- CONS
    
    CONS -- "2.3.1: crear() ▶" --- ALERT
    
    ALERT -- "2.3.2: notificarPersonal() ▶" --- PL

    %% Estilos
    classDef object fill:#f9f9f9,stroke:#333,stroke-width:2px;
    class APP,CTRL,DEP,VF,CONT,CONS,ALERT object;
```

---

### Lista de Cambios (Changelog de Errores)

**Casos de Uso:**
* **CU-1**: Se reemplazó `<<...>>` por las comillas angulares `«include»` y `«extend»`.
* **CU-2**: La flecha `UA -. "Es un" .-> U` se cambió por una línea continua `UA -- "«generalization»" --> U`.
* **CU-3**: Se reemplazaron las flechas direccionales (`-->`) por asociaciones sin punta de flecha (`---`) entre actores y casos de uso.
* **CU-4**: Se eliminó la relación del Personal Logístico con "Generar reporte de fraude".
* **CU-5**: Se eliminaron los pasos internos (UC3 y UC6) y se hicieron las extensiones directamente sobre el caso de uso base UC2.
* **CU-6**: Se actualizó la guarda de la extensión a `[acumulado >= 30]`.

**Clases:**
* **CL-1**: Se dividió en `PuntoVerde` y `Contenedor` estableciendo una composición `*--`. Los atributos y métodos operativos pasaron a `Contenedor`.
* **CL-2**: Se añadieron las clases `Usuario`, `UsuarioAutenticado` (con herencia), y `PersonalLogistico`, creando su asociación con `AlertaOperativa`.
* **CL-3**: Se agregó `ReporteFraude`, el atributo constante `-MAX_ENVASES_FRAUDE = 10` y el método `evaluarFiltroFraude(cantidad: int)`.
* **CL-4**: Se eliminaron los atributos inventados (`credenciales`, `estadoProceso`, `fecha`, etc.) conservando identificadores.
* **CL-5**: Se corrigió la composición por una asociación simple `-->` entre `Contenedor` y `AlertaOperativa`, quitando el texto de la etiqueta.
* **CL-6**: Se eliminaron las comillas de las asociaciones en el renderizado y se adaptaron atributos/métodos a la notación estricta UML (`-idUsuario: String`).

**Secuencia:**
* **SEC-1**: Se añadieron las clases faltantes de control y entidad al diagrama de clases (`AppUcabRecicla`, `ControladorDepositos`, `ConsolaAdministracion`, `ServicioFotoEvidencia`), igualando todos los nombres, participantes y métodos.
* **SEC-2**: Se numeró explícitamente todo el árbol de mensajes (1, 1.1, 1.1.1, 2, 2.1...).
* **SEC-3**: Se usó la flecha `-)` para los mensajes asíncronos y se extendió la activación del servicio de foto `VF` desde la petición hasta el retorno.
* **SEC-4**: Se reescribió la precondición enfocada puramente en estados previos (añadiendo el acumulado previo del contenedor = 25).
* **SEC-5**: Se completó el flujo `opt` enviando el mensaje `notificarPersonal()` al `PersonalLogistico` a través de la clase `AlertaOperativa`.

**Colaboración:**
* **COL-1**: Se añadió el mensaje reflexivo `evaluarFiltroFraude` en `CTRL` y se colocaron los retornos de estados de verificación hacia `APP` y `UA`.
* **COL-2**: Se estructuró el mensaje como `nuevoAcumulado := actualizarInventario(5)`.
* **COL-3**: Se eliminaron los corchetes `[]` para la condición de asincronía y se implementó como el texto `«asíncrono»`.
* **COL-4**: Todos los objetos se renombraron con el formato de instancia `:Clase`, idénticos al diagrama de secuencia.