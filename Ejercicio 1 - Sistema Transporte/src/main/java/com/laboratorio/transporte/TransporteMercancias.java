package com.laboratorio.transporte;


public class TransporteMercancias extends Transporte {
    protected double toneladas;

    // Constructor
    public TransporteMercancias(double horasConducidas, double toneladas) {
        super(horasConducidas);
        this.toneladas = toneladas;
    }

    // Constructor sobrecargado
    public TransporteMercancias() {
        super();
        this.toneladas = 0;
    }

    // Get y set
    public double getToneladas() {
        return toneladas;
    }

    public void setToneladas(double toneladas) {
        this.toneladas = toneladas;
    }

    // Calcula el bono
    @Override
    public double calcularBonificacionEspecifica() {
        return toneladas * 2.0; 
    }
    
    // Devuelve un texto
    public String obtenerDatos() {
        return "Mercancias: " + toneladas + ", Horas: " + horasConducidas;
    }
}
