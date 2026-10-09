package teatro;

public class Cliente {
    private String nombre;
    private int socialNumber;
    private String tipoCliente;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(int socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Cliente(String nombre, int socialNumber, String tipoCliente) {
        this.nombre = nombre;
        this.socialNumber = socialNumber;
        this.tipoCliente = tipoCliente;
    }

}
