package com.laboratorio.transporte;

public abstract class Transporte {
    // Usamos protected para que las clases hijas puedan usar la variable
    protected double horasConducidas;

    public Transporte(double horasConducidas) {
        this.horasConducidas = horasConducidas;
    }

    // Calcula el pago base que es igual para todos (5 dolares por hora)
    public double calcularPagoFijoPorHora() {
        return 5.0 * horasConducidas;
    }

    // Método abstracto que cada hijo debe programar
    public abstract double calcularBonificacionEspecifica();

    // Suma el pago fijo más la bonificación
    public double calcularPagoTotalTransporte() {
        double fijo = calcularPagoFijoPorHora();
        double bono = calcularBonificacionEspecifica();
        return fijo + bono;
    }
}
