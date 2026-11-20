package com.laboratorio.transporte;


import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {

    // Arreglo estatico de conductores
    private static Conductor[] listaConductores = new Conductor[100];
    private static int cantidadConductores = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Añadir Conductor");
            System.out.println("2. Asignar Transporte");
            System.out.println("3. Listar Mercancías Peligrosas");
            System.out.println("4. Ver Informe de Sueldos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            
            String entrada = scanner.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (Exception e) {
                opcion = 0;
            }

            if (opcion == 1) {
                anadirConductor();
            } else if (opcion == 2) {
                asignarTransporte();
            } else if (opcion == 3) {
                listarPeligrosas();
            } else if (opcion == 4) {
                verSueldos();
            }
        }
        
        scanner.close();
    }

    // Agrega un conductor usando JOptionPane
    private static void anadirConductor() {
        if (cantidadConductores >= listaConductores.length) {
            JOptionPane.showMessageDialog(null, "No hay mas espacio.");
            return;
        }
        
        try {
            String cedulaTxt = JOptionPane.showInputDialog("Ingrese la cedula:");
            if (cedulaTxt == null) return;
            int cedula = Integer.parseInt(cedulaTxt);

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
            if (nombre == null) return;

            String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
            if (apellido == null) return;

            String edadTxt = JOptionPane.showInputDialog("Ingrese la edad:");
            if (edadTxt == null) return;
            int edad = Integer.parseInt(edadTxt);

            Conductor c = new Conductor(cedula, nombre, apellido, edad);
            listaConductores[cantidadConductores] = c;
            cantidadConductores++;

            JOptionPane.showMessageDialog(null, "Agregado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos invalidos.");
        }
    }

    // Asigna transporte a conductor
    private static void asignarTransporte() {
        try {
            String cedulaTxt = JOptionPane.showInputDialog("Cedula del conductor:");
            if (cedulaTxt == null) return;
            int cedula = Integer.parseInt(cedulaTxt);

            Conductor c = null;
            for (int i = 0; i < cantidadConductores; i++) {
                if (listaConductores[i].getCedula() == cedula) {
                    c = listaConductores[i];
                    break;
                }
            }

            if (c == null) {
                JOptionPane.showMessageDialog(null, "Conductor no existe.");
                return;
            }

            String[] opciones = {"Personas", "Mercancias", "Peligrosas"};
            int tipo = JOptionPane.showOptionDialog(null, "Tipo de transporte", "Transporte", 
                       JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (tipo == -1) return;

            String horasTxt = JOptionPane.showInputDialog("Horas conducidas:");
            if (horasTxt == null) return;
            double horas = Double.parseDouble(horasTxt);

            Transporte t = null;
            if (tipo == 0) {
                String paxTxt = JOptionPane.showInputDialog("Cantidad de personas:");
                int pax = Integer.parseInt(paxTxt);
                t = new TransportePersonas(horas, pax);
            } else if (tipo == 1) {
                String tonTxt = JOptionPane.showInputDialog("Toneladas:");
                double ton = Double.parseDouble(tonTxt);
                t = new TransporteMercancias(horas, ton);
            } else if (tipo == 2) {
                String tonTxt = JOptionPane.showInputDialog("Toneladas:");
                double ton = Double.parseDouble(tonTxt);
                t = new TransporteMercanciasPeligrosas(horas, ton);
            }

            if (t != null) {
                c.agregarTransporte(t);
                JOptionPane.showMessageDialog(null, "Transporte asignado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error.");
        }
    }

    // Lista las mercancias peligrosas
    private static void listarPeligrosas() {
        String reporte = "--- Mercancias Peligrosas ---\n";
        
        for (int i = 0; i < cantidadConductores; i++) {
            Conductor c = listaConductores[i];
            Transporte[] transportes = c.getTransportesRealizados();
            int cantTrans = c.getCantidadTransportes();
            
            for (int j = 0; j < cantTrans; j++) {
                Transporte t = transportes[j];
                if (t instanceof TransporteMercanciasPeligrosas) {
                    TransporteMercanciasPeligrosas tmp = (TransporteMercanciasPeligrosas) t;
                    reporte += "Conductor: " + c.getNombre() + "\n" + tmp.obtenerDatos() + "\n\n";
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, reporte);
    }

    // Muestra los sueldos
    private static void verSueldos() {
        String reporte = "--- Sueldos ---\n";
        
        for (int i = 0; i < cantidadConductores; i++) {
            Conductor c = listaConductores[i];
            reporte += "Conductor: " + c.getNombre() + " " + c.getApellido() + "\n";
            reporte += "Total a pagar: $" + c.calcularSueldoTotal() + "\n\n";
        }
        
        JOptionPane.showMessageDialog(null, reporte);
    }
}
