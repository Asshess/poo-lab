package com.laboratorio.transporte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {

    // Aquí guardamos todos los conductores del sistema
    private ArrayList<Conductor> listaConductores;

    public Main() {
        listaConductores = new ArrayList<Conductor>();

        // Configuración de la ventana principal
        setTitle("Sistema de Control de Sueldos");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla

        // Creamos un panel principal con diseño de cuadrícula (Grid)
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(6, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        // Título dentro de la ventana
        JLabel lblTitulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(lblTitulo);

        // Creamos los botones
        JButton btnAnadirConductor = new JButton("1. Añadir Conductor");
        JButton btnAsignarTransporte = new JButton("2. Asignar Transporte");
        JButton btnListarPeligrosas = new JButton("3. Listar Mercancías Peligrosas");
        JButton btnVerSueldos = new JButton("4. Ver Informe de Sueldos");
        JButton btnSalir = new JButton("5. Salir");

        // Agregamos los botones al panel
        panelPrincipal.add(btnAnadirConductor);
        panelPrincipal.add(btnAsignarTransporte);
        panelPrincipal.add(btnListarPeligrosas);
        panelPrincipal.add(btnVerSueldos);
        panelPrincipal.add(btnSalir);

        // Agregamos el panel a la ventana
        add(panelPrincipal);

        // --- Acciones de los botones --- //

        btnAnadirConductor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anadirConductor();
            }
        });

        btnAsignarTransporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                asignarTransporte();
            }
        });

        btnListarPeligrosas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarPeligrosas();
            }
        });

        btnVerSueldos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verSueldos();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Método para añadir un conductor pidiendo datos con JOptionPane
    private void anadirConductor() {
        try {
            String cedula = JOptionPane.showInputDialog(this, "Ingrese la cédula:");
            if (cedula == null || cedula.equals("")) return; // Si cancela o deja vacío

            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre:");
            if (nombre == null || nombre.equals("")) return;

            String apellido = JOptionPane.showInputDialog(this, "Ingrese el apellido:");
            if (apellido == null || apellido.equals("")) return;

            String edadTexto = JOptionPane.showInputDialog(this, "Ingrese la edad:");
            if (edadTexto == null || edadTexto.equals("")) return;
            
            int edad = Integer.parseInt(edadTexto);

            // Creamos y guardamos el conductor
            Conductor nuevo = new Conductor(cedula, nombre, apellido, edad);
            listaConductores.add(nuevo);

            JOptionPane.showMessageDialog(this, "Conductor agregado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Revise la edad.");
        }
    }

    // Método para asignar un transporte
    private void asignarTransporte() {
        if (listaConductores.size() == 0) {
            JOptionPane.showMessageDialog(this, "No hay conductores registrados.");
            return;
        }

        try {
            String cedula = JOptionPane.showInputDialog(this, "Ingrese la cédula del conductor:");
            if (cedula == null) return;

            // Buscamos al conductor
            Conductor conductorEncontrado = null;
            for (int i = 0; i < listaConductores.size(); i++) {
                if (listaConductores.get(i).getCedula().equals(cedula)) {
                    conductorEncontrado = listaConductores.get(i);
                    break;
                }
            }

            if (conductorEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Conductor no encontrado.");
                return;
            }

            // Preguntamos el tipo de transporte
            String[] opciones = {"Personas", "Mercancías", "Mercancías Peligrosas"};
            int seleccion = JOptionPane.showOptionDialog(this,
                    "Seleccione el tipo de transporte",
                    "Tipo de Transporte",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, opciones, opciones[0]);

            if (seleccion == -1) return; // Si cierra la ventana

            String horasTexto = JOptionPane.showInputDialog(this, "Ingrese horas conducidas:");
            if (horasTexto == null) return;
            double horas = Double.parseDouble(horasTexto);

            Transporte nuevoTransporte = null;

            if (seleccion == 0) {
                // Transporte de Personas
                String personasTexto = JOptionPane.showInputDialog(this, "Ingrese cantidad de personas:");
                int personas = Integer.parseInt(personasTexto);
                nuevoTransporte = new TransportePersonas(horas, personas);

            } else if (seleccion == 1) {
                // Transporte de Mercancías
                String tonTexto = JOptionPane.showInputDialog(this, "Ingrese cantidad de toneladas:");
                double toneladas = Double.parseDouble(tonTexto);
                nuevoTransporte = new TransporteMercancias(horas, toneladas);

            } else if (seleccion == 2) {
                // Transporte de Mercancías Peligrosas
                String tonTexto = JOptionPane.showInputDialog(this, "Ingrese cantidad de toneladas:");
                double toneladas = Double.parseDouble(tonTexto);
                nuevoTransporte = new TransporteMercanciasPeligrosas(horas, toneladas);
            }

            // Agregamos el transporte al conductor
            conductorEncontrado.agregarTransporte(nuevoTransporte);
            JOptionPane.showMessageDialog(this, "Transporte asignado correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos.");
        }
    }

    // Método para listar solo los transportes peligrosos
    private void listarPeligrosas() {
        String reporte = "--- Transportes de Mercancías Peligrosas ---\n\n";
        boolean hayPeligrosas = false;

        for (int i = 0; i < listaConductores.size(); i++) {
            Conductor c = listaConductores.get(i);
            ArrayList<Transporte> susTransportes = c.getTransportesRealizados();
            
            for (int j = 0; j < susTransportes.size(); j++) {
                Transporte t = susTransportes.get(j);
                
                // Verificamos si es mercancía peligrosa
                if (t instanceof TransporteMercanciasPeligrosas) {
                    TransporteMercanciasPeligrosas tp = (TransporteMercanciasPeligrosas) t;
                    reporte = reporte + "Conductor: " + c.getNombre() + " " + c.getApellido() + "\n";
                    reporte = reporte + "Detalle: " + tp.obtenerDatos() + "\n\n";
                    hayPeligrosas = true;
                }
            }
        }

        if (hayPeligrosas == false) {
            reporte = reporte + "No hay transportes de este tipo registrados.";
        }

        // Mostramos el texto en un área de texto con barras de desplazamiento
        JTextArea areaTexto = new JTextArea(reporte);
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(this, scroll, "Mercancías Peligrosas", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para calcular y mostrar todos los sueldos
    private void verSueldos() {
        String informe = "=== INFORME DE SUELDOS ===\n\n";

        for (int i = 0; i < listaConductores.size(); i++) {
            Conductor c = listaConductores.get(i);
            informe = informe + "Conductor: " + c.getNombre() + " " + c.getApellido() + "\n";
            informe = informe + "Cédula: " + c.getCedula() + "\n";
            
            ArrayList<Transporte> susTransportes = c.getTransportesRealizados();
            informe = informe + "Transportes realizados: " + susTransportes.size() + "\n";
            
            for (int j = 0; j < susTransportes.size(); j++) {
                Transporte t = susTransportes.get(j);
                
                // Usamos instanceof para saber qué método llamar, o agregamos un método en padre
                // Para hacerlo simple para principiantes, agregué el método obtenerDatos() en las hijas
                String datosTransporte = "";
                if (t instanceof TransportePersonas) {
                    datosTransporte = ((TransportePersonas) t).obtenerDatos();
                } else if (t instanceof TransporteMercanciasPeligrosas) {
                    datosTransporte = ((TransporteMercanciasPeligrosas) t).obtenerDatos();
                } else if (t instanceof TransporteMercancias) {
                    datosTransporte = ((TransporteMercancias) t).obtenerDatos();
                }
                
                informe = informe + "  - " + datosTransporte + " -> Pago: $" + t.calcularPagoTotalTransporte() + "\n";
            }
            
            informe = informe + "SUELDO TOTAL: $" + c.calcularSueldoTotal() + "\n";
            informe = informe + "--------------------------------------\n\n";
        }

        if (listaConductores.size() == 0) {
            informe = informe + "No hay conductores en el sistema.";
        }

        JTextArea areaTexto = new JTextArea(informe);
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scroll, "Informe Detallado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método principal que inicia la aplicación
    public static void main(String[] args) {
        // Ejecutamos la interfaz gráfica en el hilo adecuado de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main ventana = new Main();
                ventana.setVisible(true);
            }
        });
    }
}
