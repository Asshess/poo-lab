package farmacia.estructuras;

import farmacia.excepciones.CapacidadInvalidaException;
import farmacia.excepciones.CintaLlenaException;

/**
 * Estructura tipo Cola (FIFO) para la Cinta Transportadora de Salida.
 * Procesa los elementos estrictamente en orden de llegada.
 */
public class CintaSalida<T> {

    private final Integer capacidadMaxima;
    private final Object[] elementos;
    private Integer frente;
    private Integer fin;
    private Integer cantidadActual;

    public CintaSalida(Integer capacidadMaxima) throws CapacidadInvalidaException {
        if (capacidadMaxima == null || capacidadMaxima <= 0) {
            throw new CapacidadInvalidaException(
                    "Error de configuración: La capacidad de la cinta de salida debe ser mayor a cero (valor recibido: " + capacidadMaxima + ")."
            );
        }
        this.capacidadMaxima = capacidadMaxima;
        this.elementos = new Object[capacidadMaxima];
        this.frente = 0;
        this.fin = 0;
        this.cantidadActual = 0;
    }

    public void depositar(T elemento) throws CintaLlenaException {
        if (estaLlena()) {
            throw new CintaLlenaException(
                    "OBSTRUCCIÓN EN SALIDA: La cinta transportadora alcanzó su límite físico (" + capacidadMaxima + " elementos). No se puede depositar: " + elemento
            );
        }
        elementos[fin] = elemento;
        fin = (fin + 1) % capacidadMaxima;
        cantidadActual++;
    }

    @SuppressWarnings("unchecked")
    public T despachar() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cinta de salida está vacía.");
        }
        T elemento = (T) elementos[frente];
        elementos[frente] = null;
        frente = (frente + 1) % capacidadMaxima;
        cantidadActual--;
        return elemento;
    }

    public boolean estaVacia() {
        return cantidadActual == 0;
    }

    public boolean estaLlena() {
        return cantidadActual.equals(capacidadMaxima);
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    @Override
    public String toString() {
        return "Cinta de Salida [Ocupación: " + cantidadActual + "/" + capacidadMaxima + "]";
    }
}
