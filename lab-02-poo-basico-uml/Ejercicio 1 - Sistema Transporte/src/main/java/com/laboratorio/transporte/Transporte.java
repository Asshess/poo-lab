package com.laboratorio.transporte;


public abstract class Transporte {
    protected double horasConducidas;

    // Constructor completo
    public Transporte(double horasConducidas) {
        this.horasConducidas = horasConducidas;
    }

    // Constructor sobrecargado
    public Transporte() {
        this.horasConducidas = 0;
    }

    // Metodos get y set
    public double getHorasConducidas() {
        return horasConducidas;
    }

    public void setHorasConducidas(double horasConducidas) {
        this.horasConducidas = horasConducidas;
    }

    // Calcula el pago base
    public double calcularPagoFijoPorHora() {
        return 5.0 * horasConducidas;
    }

    // Metodo abstracto
    public abstract double calcularBonificacionEspecifica();

    // Suma los montos
    public double calcularPagoTotalTransporte() {
        double fijo = calcularPagoFijoPorHora();
        double bono = calcularBonificacionEspecifica();
        return fijo + bono;
    }
}
