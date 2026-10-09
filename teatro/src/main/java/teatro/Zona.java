package teatro;

public class Zona {
    private static Zona[] zonas = new Zona[4];

    static {
        registrar(new Zona("Principal", 200, 3500, 4500));
        registrar(new Zona("Palco", 40, 5000, 1800));
        registrar(new Zona("Central", 400, 2500, 1700));
        registrar(new Zona("Lateral", 10, 1200, 800));
    }

    public String nombre;
    private int cantLocalidades;
    private double precioNormal;
    private double precioAbonado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantLocalidades() {
        return cantLocalidades;
    }

    public void setCantLocalidades(int cantLocalidades) {
        this.cantLocalidades = cantLocalidades;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(double precioNormal) {
        this.precioNormal = precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }

    public void setPrecioAbonado(double precioAbonado) {
        this.precioAbonado = precioAbonado;
    }

    public Zona(String nombre, int cantLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.cantLocalidades = cantLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    public static void registrar(Zona zona) {
        if (zona != null && zona.getNombre() != null) {
            for (int i = 0; i < zonas.length; i++) {
                if (zonas[i] == null) {
                    zonas[i] = zona;
                    return;
                }
            }
        }
    }

    public static Zona buscarZona(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        String nombreBuscado = nombre.trim();
        for (Zona zona : zonas) {
            if (zona != null && zona.getNombre().equals(nombreBuscado)) {
                return zona;
            }
        }
        return null;
    }
}
