# Dependencias:

Para compilar y ejecutar este proyecto correctamente, asegúrate de tener instalado lo siguiente en tu sistema:

1. **Java Development Kit (JDK):** Versión **27** (OpenJDK 27). Aunque con el 21 compila bien, si tu version es <21 cambia en el **pom.xml** el <maven.compiler.source> y <maven.compiler.target> a la version 21
2. **Apache Maven o Ant:** Herramienta de gestión y construcción de proyectos.
3. **Entorno Gráfico:** Debido a que el proyecto hace uso de `javax.swing.JOptionPane` para la entrada de datos y muestra de reportes, debes ejecutarlo en un entorno de escritorio con interfaz gráfica disponible (no funcionará en servidores puramente CLI sin display gráfico).






# Maven: 

## Instrucciones de Compilación:

En una terminal, navega hasta la carpeta raíz del proyecto (a nivel en donde se encuentra el archivo `pom.xml`) y ejecuta el siguiente comando que limpia builds anteriores y compila el src:

```bash
mvn clean compile
```

Si quieres empaquetar el proyecto en un archivo `.jar` (que se guardará en la carpeta `/target`), puedes utilizar:

```bash
mvn clean package
```

## Ejecución:

Una vez que el proyecto se haya compilado exitosamente, puedes ejecutar la aplicación directamente usando el plugin de Maven con el siguiente comando:

```bash
mvn exec:java -Dexec.mainClass="com.laboratorio.transporte.Main"
```

# Ant: 



## Instrucciones de compilacion - Ant:

En una terminal, asegúrate de estar en la carpeta raíz (Al nivel endonde se encuentra el `build.xml`) y ejecuta:

```bash
ant clean compile
```

## Ejecución - Ant:

Una vez compilado, puedes ejecutar la aplicación con el siguiente comando:

```bash
ant run
```