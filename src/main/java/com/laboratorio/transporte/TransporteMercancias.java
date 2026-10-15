package com.laboratorio.transporte;

public class TransporteMercancias extends Transporte {
    protected double toneladas;

    public TransporteMercancias(double horasConducidas, double toneladas) {
        super(horasConducidas);
        this.toneladas = toneladas;
    }

    // Sobreescribimos el método para transporte de mercancías
    public double calcularBonificacionEspecifica() {
        // 2 dolares extra por cada tonelada
        return toneladas * 2.0; 
    }
    
    public String obtenerDatos() {
        return "Mercancías (Toneladas: " + toneladas + ", Horas: " + horasConducidas + ")";
    }
}
