# Análisis del Caso de Estudio: UcabRecicla Express

### Análisis Inicial del Dominio

*   **Actores:** Usuario No Autenticado, Usuario Autenticado, Personal Logístico.
*   **Entidades:** `PuntoVerde` (Contenedor), `Deposito` (Orden de reciclaje), `Material` (Plástico, Aluminio, Cartón), `AlertaOperativa`, `Usuario`.
*   **Reglas de Negocio:**
    1.  Límite de prevención de fraude: máximo 10 envases por transacción. Si se supera, se aborta la operación y se registra un reporte sin modificar inventarios.
    2.  Verificación de evidencia asíncrona: toma aproximadamente 1,5 segundos y no bloquea la interfaz del usuario.
    3.  Límite de capacidad por contenedor: máximo 30 unidades. Si el inventario alcanza o supera este límite, se debe disparar una alerta crítica de vaciado.
*   **Ambigüedades detectadas:** El texto no especifica si el "Personal Logístico" recibe la alerta operativa a través de la app móvil, correo o solo se visualiza en la consola. 
*   **Supuestos adoptados:** 
    1.  Asumo que la "consola de administración" y la "consola de auditoría" son interfaces (o subsistemas) que forman parte de la misma frontera del sistema "UcabRecicla Express".
    2.  En el diagrama de clases, modelo `Material` como una enumeración (Enum).
    3.  Para el escenario de los diagramas de interacción, asumo que la cantidad depositada es válida (ej. 5 envases) y que, al sumarse, el contenedor alcanza exactamente su límite (30), para poder graficar la notificación de alerta.

---

### 1. Diagrama de Casos de Uso

```mermaid
flowchart LR
    %% Definición de Actores
    U((Usuario No Autenticado))
    UA((Usuario Autenticado))
    PL((Personal Logístico))
    
    UA -. "Es un" .-> U

    %% Frontera del sistema
    subgraph UcabRecicla Express
        UC1([Consultar contenedores activos])
        UC2([Registrar declaración de depósito])
        UC3([Aplicar filtro anti-fraude])
        UC4([Generar reporte de fraude])
        UC5([Verificar foto-evidencia])
        UC6([Actualizar inventario del contenedor])
        UC7([Emitir alerta operativa de vaciado])
    end

    %% Relaciones Actor - Caso de Uso
    U --> UC1
    UA --> UC2
    PL --> UC7
    PL --> UC4

    %% Relaciones Include y Extend
    UC2 -. "<<include>>" .-> UC3
    UC4 -. "<<extend>> <br> [Cantidad > 10]" .-> UC3
    
    UC2 -. "<<include>>" .-> UC5
    UC2 -. "<<include>>" .-> UC6
    
    UC7 -. "<<extend>> <br> [Capacidad >= 30]" .-> UC6
```

---

### 2. Diagrama de Clases

```mermaid
classDiagram
    class Material {
        <<enumeration>>
        PLASTICO
        ALUMINIO
        CARTON
    }

    class UsuarioAutenticado {
        -String idUsuario
        -String credenciales
        +seleccionarPuntoVerde() void
        +declararReciclaje(cantidad: int, material: Material) Deposito
    }

    class PuntoVerde {
        -String idContenedor
        -String ubicacion
        -int capacidadMaxima
        -int nivelLlenado
        +consultarEstado() String
        +actualizarInventario(cantidad: int) int
        +verificarCapacidad() boolean
    }

    class Deposito {
        -String idTransaccion
        -int cantidadEnvases
        -String fotoEvidencia
        -String estadoProceso
        -Date fecha
        +evaluarFiltroFraude() boolean
        +iniciarVerificacionAsincrona() void
        +confirmarDeposito() void
    }

    class AlertaOperativa {
        -String idAlerta
        -String tipoAlerta
        -Date fechaGeneracion
        +notificarPersonal() void
    }

    %% Relaciones y multiplicidad
    UsuarioAutenticado "1" --> "*" Deposito : "realiza"
    Deposito "*" --> "1" Material : "clasifica"
    PuntoVerde "1" <-- "*" Deposito : "registra en"
    PuntoVerde "1" *-- "*" AlertaOperativa : "genera (Composición)"
```

---

### 3. Diagrama de Secuencia
*Escenario: Registrar Depósito con Validación Asíncrona y Notificación de Alerta por Llenado.*

```mermaid
sequenceDiagram
    actor UA as Usuario Autenticado
    participant APP as App UcabRecicla
    participant CTRL as Controlador Depósitos
    participant VF as Servicio FotoEvidencia
    participant PV as Entidad PuntoVerde
    participant CONS as Consola Administración

    Note over UA, CONS: Precondición: Usuario autenticado, contenedor elegido, cantidad=5, límite contenedor=30.

    UA->>APP: registrarDeposito(material, 5, foto)
    activate APP
    
    APP->>CTRL: procesarDeclaracion(material, 5, foto)
    activate CTRL
    
    %% Filtro Anti-Fraude (Síncrono)
    CTRL->>CTRL: aplicarFiltroFraude(5)
    Note right of CTRL: Cantidad válida (5 <= 10).<br>No se dispara reporte.
    
    %% Llamada Asíncrona
    CTRL-)VF: verificarEvidenciaAsync(foto)
    Note right of CTRL: Llamada no bloqueante
    
    CTRL-->>APP: estado: "Depósito en verificación"
    deactivate CTRL
    APP-->>UA: "Evidencia enviada. Su depósito se está verificando."
    deactivate APP

    %% Retorno Asíncrono después de 1.5s
    Note over VF, CTRL: Transcurren ~1,5 segundos
    activate VF
    VF->>CTRL: confirmacionVerificacion(OK)
    deactivate VF
    activate CTRL
    
    %% Actualización de inventario
    CTRL->>PV: sumarInventario(5)
    activate PV
    PV-->>CTRL: nuevoAcumulado = 30
    deactivate PV
    
    %% Fragmento de control (Alerta)
    opt nuevoAcumulado >= 30
        CTRL->>CONS: dispararEventoAlerta(idPuntoVerde, "Vaciado Urgente")
        activate CONS
        CONS-->>CTRL: alertaRegistrada
        deactivate CONS
    end
    
    deactivate CTRL
```

---

### 4. Diagrama de Colaboración (Comunicación)
*Escenario: Registrar Depósito con Validación Asíncrona y Notificación de Alerta por Llenado.*

```mermaid
flowchart LR
    %% Nodos (Objetos / Participantes)
    UA((Usuario))
    APP[App UcabRecicla]
    CTRL[Controlador Depósitos]
    VF[Servicio FotoEvidencia]
    PV[Entidad PuntoVerde]
    CONS[Consola Admin]

    %% Enlaces únicos por cada par con direccionalidad simulada en los labels y numeración jerárquica
    UA -- "1: registrarDeposito(5, foto) ▶" --- APP
    
    APP -- "1.1: procesarDeclaracion(5, foto) ▶" --- CTRL
    
    %% El Controlador interactúa con los 3 subsistemas
    CTRL -- "1.1.1: [asíncrono] verificarEvidencia(foto) ▶ <br> ◀ 2: confirmacionVerificacion(OK)" --- VF
    
    CTRL -- "2.1: sumarInventario(5) ▶ <br> ◀ 2.1.1: retorna(nuevoAcumulado=30)" --- PV
    
    CTRL -- "2.2: [acumulado >= 30] dispararEventoAlerta() ▶" --- CONS

    %% Estilos para semejar un entorno UML
    classDef object fill:#f9f9f9,stroke:#333,stroke-width:2px;
    class APP,CTRL,VF,PV,CONS object;
```