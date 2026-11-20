package com.laboratorio.transporte;


public class TransporteMercanciasPeligrosas extends TransporteMercancias {
    
    // Constructor
    public TransporteMercanciasPeligrosas(double horasConducidas, double toneladas) {
        super(horasConducidas, toneladas);
    }

    // Constructor sobrecargado
    public TransporteMercanciasPeligrosas() {
        super();
    }

    // Calcula el bono
    @Override
    public double calcularBonificacionEspecifica() {
        double bonoNormal = super.calcularBonificacionEspecifica();
        return bonoNormal + 50.0;
    }
    
    // Devuelve un texto
    @Override
    public String obtenerDatos() {
        return "Mercancias Peligrosas: " + toneladas + ", Horas: " + horasConducidas;
    }
}
