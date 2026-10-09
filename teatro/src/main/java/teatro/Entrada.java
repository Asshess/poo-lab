package teatro;

public abstract class Entrada {
    private String funcion;
    private String zona;
    private Cliente cliente;

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Entrada(String funcion, String zona, Cliente cliente) {
        this.funcion = funcion;
        this.zona = zona;
        this.cliente = cliente;
    }

    public Zona getZonaInfo() {
        if (zona == null || zona.isBlank()) {
            return null;
        }
        return Zona.buscarZona(zona);
    }

    public abstract double calcularPrecio();
}
