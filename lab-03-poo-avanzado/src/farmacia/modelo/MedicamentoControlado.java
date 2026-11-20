package farmacia.modelo;

public class MedicamentoControlado extends Medicamento {

    private final Boolean requiereReceta;

    public MedicamentoControlado(String nombre, Boolean requiereReceta) {
        super(nombre);
        this.requiereReceta = requiereReceta;
    }

    public Boolean getRequiereReceta() {
        return requiereReceta;
    }

    @Override
    public String toString() {
        String condicion = requiereReceta ? "Requiere receta archivada" : "Libre venta";
        return super.toString() + " (Controlado | " + condicion + ")";
    }
}
