package com.laboratorio.transporte;

import java.util.ArrayList;

public class Conductor {
    private int cedula;
    private String nombre;
    private String apellido;
    private int edad;
    
    // Sueldo base fijo de 700
    private double sueldoBase = 700.0;
    
    // Lista para guardar todos los transportes que hace el conductor
    private ArrayList<Transporte> transportesRealizados;

    public Conductor(int cedula, String nombre, String apellido, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.transportesRealizados = new ArrayList<Transporte>();
    }

    // Agregar un transporte a la lista
    public void agregarTransporte(Transporte transporte) {
        transportesRealizados.add(transporte);
    }

    // Calcular todo el dinero que se le debe pagar
    public double calcularSueldoTotal() {
        double total = sueldoBase;
        
        // Recorremos la lista de transportes y sumamos lo de cada uno
        for (int i = 0; i < transportesRealizados.size(); i++) {
            Transporte t = transportesRealizados.get(i);
            total = total + t.calcularPagoTotalTransporte();
        }
        
        return total;
    }

    // Getters
    public int getCedula() { 
        return cedula; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public String getApellido() { 
        return apellido; 
    }

    public ArrayList<Transporte> getTransportesRealizados() { 
        return transportesRealizados; 
    }
}
