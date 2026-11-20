package farmacia.estructuras;

import farmacia.excepciones.CapacidadInvalidaException;
import farmacia.excepciones.TolvaVaciaException;

/**
 * Estructura tipo Pila (LIFO) para la Tolva de Entrada.
 * Solo permite extraer el medicamento ubicado en el tope físico.
 */
public class Tolva<T> {

    private final Integer capacidadMaxima;
    private final Object[] elementos;
    private Integer tope;

    public Tolva(Integer capacidadMaxima) throws CapacidadInvalidaException {
        if (capacidadMaxima == null || capacidadMaxima <= 0) {
            throw new CapacidadInvalidaException(
                    "Error de configuración: La capacidad de la tolva debe ser mayor a cero (valor recibido: " + capacidadMaxima + ")."
            );
        }
        this.capacidadMaxima = capacidadMaxima;
        this.elementos = new Object[capacidadMaxima];
        this.tope = 0;
    }

    public void cargar(T elemento) throws Exception {
        if (estaLlena()) {
            throw new Exception("La tolva de entrada ha alcanzado su capacidad máxima (" + capacidadMaxima + ").");
        }
        elementos[tope] = elemento;
        tope++;
    }

    @SuppressWarnings("unchecked")
    public T extraer() throws TolvaVaciaException {
        if (estaVacia()) {
            throw new TolvaVaciaException("Incidente en Tolva: Se intentó extraer un medicamento de una tolva vacía.");
        }
        tope--;
        T elemento = (T) elementos[tope];
        elementos[tope] = null; // Liberar referencia
        return elemento;
    }

    public boolean estaVacia() {
        return tope == 0;
    }

    public boolean estaLlena() {
        return tope.equals(capacidadMaxima);
    }

    public Integer getCantidadActual() {
        return tope;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    @Override
    public String toString() {
        return "Tolva de Entrada [Ocupación: " + tope + "/" + capacidadMaxima + "]";
    }
}
