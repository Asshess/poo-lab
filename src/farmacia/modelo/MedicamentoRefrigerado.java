package farmacia.modelo;

public class MedicamentoRefrigerado extends Medicamento {

    private final Double temperaturaSeguridad;

    public MedicamentoRefrigerado(String nombre, Double temperaturaSeguridad) {
        super(nombre);
        this.temperaturaSeguridad = temperaturaSeguridad;
    }

    public Double getTemperaturaSeguridad() {
        return temperaturaSeguridad;
    }

    @Override
    public String toString() {
        return super.toString() + " (Refrigerado | Temp. Seguridad: " + temperaturaSeguridad + " °C)";
    }
}
