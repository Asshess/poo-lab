package com.laboratorio.transporte;


public class Conductor {
    private int cedula;
    private String nombre;
    private String apellido;
    private int edad;
    
    private double sueldoBase = 700.0;
    
    // Arreglo estatico
    private Transporte[] transportesRealizados;
    private int cantidadTransportes;

    // Constructor completo
    public Conductor(int cedula, String nombre, String apellido, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.transportesRealizados = new Transporte[100];
        this.cantidadTransportes = 0;
    }

    // Constructor sobrecargado
    public Conductor(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = 0;
        this.transportesRealizados = new Transporte[100];
        this.cantidadTransportes = 0;
    }

    // Agrega transporte
    public void agregarTransporte(Transporte transporte) {
        if (cantidadTransportes < transportesRealizados.length) {
            transportesRealizados[cantidadTransportes] = transporte;
            cantidadTransportes++;
        }
    }

    // Calcula sueldo total
    public double calcularSueldoTotal() {
        double total = sueldoBase;
        for (int i = 0; i < cantidadTransportes; i++) {
            Transporte t = transportesRealizados[i];
            total = total + t.calcularPagoTotalTransporte();
        }
        return total;
    }

    // Metodos de acceso
    public int getCedula() { return cedula; }
    public void setCedula(int cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public Transporte[] getTransportesRealizados() { return transportesRealizados; }
    public int getCantidadTransportes() { return cantidadTransportes; }
}
