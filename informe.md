
## 1. Clases

| Característica | Detalle | 
| ----- | ----- | 
| **Nombre de la Clase** | `Pokemon` | 
| **Clase de la que hereda** | `Mascota` | 
| **Atributos Propios** | • `tipo`: TipoElemento (Enum), visibilidad `private`.  • `puntosPoder`: int, visibilidad `private`.  • `nivelEvolucion`: int, visibilidad `private`. | 
| **Constructores** | • **Por defecto (sin parámetros):** Inicializa un Pokémon base (Pikachu, nivel 1, Eléctrico, 80 PP) delegando la creación mediante `this()`.  • **Sobrecargado (con parámetros):** Recibe `name`, `age`, `tipo`, `puntosPoder` y `nivelEvolucion`. Invoca a `super(name, age)` para inicializar los atributos heredados de `Mascota`. | 
| **Métodos Implementados** | • `alimentar(int cantidadBayas)`: *Sobrecargado/Sobrescrito.* Afecta el hambre (-8 x baya) y felicidad (+4 x baya).  • `atacarEspecial()`: *Sobrescrito (@Override).* Lógica de ataque según el tipo elemental con 50% de probabilidad de éxito. (+20 Hambre, altera Felicidad).  • `expresarEmocion()`: *Sobrescrito (@Override).* Mensaje según tipo. Reduce felicidad (-15).  • `dormir()`: *Sobrescrito (@Override).* Aumenta hambre (+10) y felicidad (+25).  • `hacerTruco()`: *Sobrescrito (@Override).* Realiza una acción según tipo (+15 Hambre, +20 Felicidad).  • *Getters y Setters*: Métodos de acceso encapsulados, incluyendo `limitarRango()` para mantener los valores entre 0 y 100. | 

## 2. GUI y Polimorfismo

**Cambios Estéticos en la Interfaz:**
La interfaz gráfica (GUI) fue adaptada para ofrecer una experiencia temática de Pokémon. Se modificó el título de la ventana para reflejar dinámicamente el nombre del Pokémon instanciado (ej. "Pikachu"). En la paleta de colores, se asignó un tono rojo (`Color(220, 53, 69)`) para la barra de progreso de Hambre, representando la urgencia o alerta alimenticia, y un color verde (`Color(40, 167, 69)`) para la barra de Felicidad, simbolizando el bienestar del Pokémon. Además, los botones fueron personalizados agregando emojis representativos (ej. 🍕, ⚡, 😴) y modificando sus textos para alinearse con el universo Pokémon (ej. "Pokedescanso" y "Dar Bayas").

**Ejecución Polimórfica (Late Binding):**
El principio de polimorfismo se evidencia claramente en la instanciación de la variable `private Mascota miMascota = new Pokemon(...);`. Aunque la variable de referencia es del tipo estático abstracto `Mascota`, el objeto real en memoria es de tipo `Pokemon`. Cuando desde los eventos de los botones (ActionListeners) se invocan métodos como `miMascota.atacarEspecial()` o `miMascota.dormir()`, la Máquina Virtual de Java (JVM) determina en tiempo de ejecución (enlace dinámico) que debe ejecutar el comportamiento específico sobrescrito en la clase `Pokemon`, no en la clase base. Esto permite que la GUI sea genérica y capaz de controlar a cualquier tipo de Pokémon sin tener que alterar el código de la ventana.

## 3. Evidencia de Pruebas y Validación de Límites

A continuación se presentan las capturas que validan el correcto funcionamiento de los límites (0 a 100) y la impresión en consola/log:

**Prueba 1: Mensajes descriptivos en el JTextArea**

*(Se observa la ejecución de diferentes acciones y cómo el `JTextArea` imprime los mensajes correspondientes al tipo Eléctrico y los resultados de los ataques)*

> *\[Inserta aquí la captura de pantalla de la ventana mostrando varios mensajes en el cuadro de texto central\]*
>
> `![Prueba Log](/home/ashes/Projects/lab-projects/Laboratorio_1-POO_Basico/limite.png)`

**Prueba 2: Validación de Límites en las Barras de Progreso (0 - 100)**

*(Se observa que tras múltiples alimentaciones o ataques consecutivos, las variables de estado y las barras `JProgressBar` no sobrepasan el valor máximo de 100 ni descienden del valor mínimo de 0, gracias a la función `Math.min` y `Math.max` implementadas en el backend).*

> *\[Inserta aquí la captura de pantalla demostrando que una barra se detuvo en 100 o en 0\]*
>
> `![Prueba Limites](/home/ashes/Projects/lab-projects/Laboratorio_1-POO_Basico/log.png)`