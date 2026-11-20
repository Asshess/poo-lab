package farmacia.modelo;

public abstract class Medicamento {

    private static Integer consecutivo = 0;
    private final String id;
    private final String nombre;

    public Medicamento(String nombre) {
        consecutivo++;
        this.id = "MED-" + consecutivo;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre;
    }
}
