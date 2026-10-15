package com.laboratorio.transporte;

public class TransportePersonas extends Transporte {
    private int cantidadPersonas;

    public TransportePersonas(double horasConducidas, int cantidadPersonas) {
        super(horasConducidas);
        this.cantidadPersonas = cantidadPersonas;
    }

    // Sobreescribimos el método para transporte de personas
    public double calcularBonificacionEspecifica() {
        double bono = 0;
        if (cantidadPersonas > 9) {
            bono = 1.0 * horasConducidas; // 1 dolar por hora si es colectivo
        } else {
            bono = 0.5 * horasConducidas; // 0.5 dolares si no es colectivo
        }
        return bono;
    }
    
    // Método para mostrar datos fácilmente
    public String obtenerDatos() {
        return "Personas (Pasajeros: " + cantidadPersonas + ", Horas: " + horasConducidas + ")";
    }
}
