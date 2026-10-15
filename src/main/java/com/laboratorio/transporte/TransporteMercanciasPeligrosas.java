package com.laboratorio.transporte;

public class TransporteMercanciasPeligrosas extends TransporteMercancias {
    
    public TransporteMercanciasPeligrosas(double horasConducidas, double toneladas) {
        super(horasConducidas, toneladas);
    }

    // Sobreescribimos de nuevo para sumar los 50 dolares fijos
    public double calcularBonificacionEspecifica() {
        double bonoNormal = super.calcularBonificacionEspecifica();
        return bonoNormal + 50.0;
    }
    
    public String obtenerDatos() {
        return "Mercancías Peligrosas (Toneladas: " + toneladas + ", Horas: " + horasConducidas + ")";
    }
}
