package com.laboratorio.transporte;


public class TransportePersonas extends Transporte {
    private int cantidadPersonas;

    // Constructor
    public TransportePersonas(double horasConducidas, int cantidadPersonas) {
        super(horasConducidas);
        this.cantidadPersonas = cantidadPersonas;
    }

    // Constructor sobrecargado
    public TransportePersonas() {
        super();
        this.cantidadPersonas = 0;
    }

    // Get y set
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    // Calcula el bono
    @Override
    public double calcularBonificacionEspecifica() {
        double bono = 0;
        if (cantidadPersonas > 9) {
            bono = 1.0 * horasConducidas;
        } else {
            bono = 0.5 * horasConducidas;
        }
        return bono;
    }
    
    // Devuelve un texto
    public String obtenerDatos() {
        return "Personas: " + cantidadPersonas + ", Horas: " + horasConducidas;
    }
}
