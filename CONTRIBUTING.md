# Cómo colaborar

## Flujo

1. Crea una rama desde `main`:

   ```bash
   git switch -c feat/lab-03-tolva
   ```

2. Haz cambios pequeños y comprueba que el proyecto compila.
3. Abre un PR contra `main`. Explica qué cambia y cómo lo probaste.
4. Se fusiona con squash y la rama se borra.

Prefijos de rama: `feat/`, `fix/`, `docs/`, `chore/` y `ci/`.

## Commits

Formato: `tipo(área): qué cambió`

Ejemplos:

- `feat(lab-03): valida la capacidad de la tolva`
- `fix(teatro): corrige el precio de entrada reducida`
- `docs(taller-uml): añade la iteración 3`

Tipos: `feat`, `fix`, `docs`, `test`, `refactor`, `chore` y `ci`.

## Comandos por proyecto

| Proyecto | Compilar / probar | Ejecutar |
|---|---|---|
| Lab 01 | `cd lab-01-poo-basico/VirtualPet && ant compile` | `cd lab-01-poo-basico/VirtualPet && ant` |
| Lab 02 | `cd lab-02-poo-basico-uml/ejercicio-1-sistema-transporte && mvn clean verify` | `mvn exec:java -Dexec.mainClass="com.laboratorio.transporte.Main"` |
| Lab 03 | `cd lab-03-poo-avanzado && ant` | `java -jar lab-03-poo-avanzado/dist/JavaApplication17.jar` |
| Teatro | `cd teatro/teatro && mvn test` | `cd teatro/teatro && mvn exec:java -Dexec.mainClass=teatro.Main` |

Las actividades de UML (`taller-uml` y `actividad-formativa-uml`) no tienen build: son documentación y diagramas.

El proyecto final vive en `proyecto-final/`. Cuando tenga build propio, se añade su comando a la tabla anterior y un job `Proyecto final` al CI; mientras tanto, su README es la referencia.

## Reglas

- No versionar `target/`, `build/`, `dist/`, `.class` ni `.jar`.
- Un PR por problema; evita mezclar limpieza, cambios funcionales y documentación.
- Actualiza el README del proyecto si cambia la forma de ejecutarlo.
- Si el repositorio es público, revisa que los PDF e imágenes no incluyan datos personales nuevos.
- No hagas merge con el CI en rojo.

## Entregas

Para congelar una entrega:

```bash
git tag -a lab-01/v1.0 -m "Entrega del Laboratorio 1"
git tag -a final/v1.0 -m "Entrega del proyecto final"
git push origin lab-01/v1.0 final/v1.0
```

Si la entrega incluye un PDF, adjúntalo también a un GitHub Release con el mismo nombre.
