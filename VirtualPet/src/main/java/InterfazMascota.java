/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java;

/**
 *
 * @author jbell
 */

import javax.swing.*;
import java.awt.*;

public class InterfazMascota extends JFrame {

    // Instancia de la clase hija
    private Dragon miMascota = new Dragon("Draco");

    // Componentes de la interfaz
    private JLabel lblNombre, lblEstado;
    private JProgressBar barHambre, barFelicidad;
    private JButton btnAlimentar, btnJugar, btnEspecial;
    private JTextArea txtLog;

    public InterfazMascota() {
        setTitle("Mi Mascota POO");
        setSize(400, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior: Nombre y Barras de Estado
        JPanel panelEstado = new JPanel(new GridLayout(3, 2, 5, 5));
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado de la mascota"));

        lblNombre = new JLabel("Mascota: " + miMascota.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));

        barHambre = new JProgressBar(0, 100);
        barHambre.setStringPainted(true);
        barHambre.setForeground(new Color(220, 53, 69)); // Rojo

        barFelicidad = new JProgressBar(0, 100);
        barFelicidad.setStringPainted(true);
        barFelicidad.setForeground(new Color(40, 167, 69)); // Verde

        panelEstado.add(new JLabel(" Nombre:"));
        panelEstado.add(lblNombre);
        panelEstado.add(new JLabel(" Nivel de Hambre:"));
        panelEstado.add(barHambre);
        panelEstado.add(new JLabel(" Nivel de Felicidad:"));
        panelEstado.add(barFelicidad);

        // Panel Central: Bitácora de acciones
        txtLog = new JTextArea(8, 20);
        txtLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(txtLog);

        // Panel Inferior: Botones de Interacción
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnAlimentar = new JButton("🍎 Comer");
        btnJugar = new JButton("🎮 Jugar");
        btnEspecial = new JButton("🔥 Escupir Fuego"); // Método de la subclase

        panelBotones.add(btnAlimentar);
        panelBotones.add(btnJugar);
        panelBotones.add(btnEspecial);

        // Agregar paneles al marco principal
        add(panelEstado, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnAlimentar.addActionListener(e -> {
            miMascota.alimentar(); // Llama método del Padre
            txtLog.append(miMascota.getNombre() + " comió una manzana.\n");
            actualizarBarras();
        });

        btnJugar.addActionListener(e -> {
            miMascota.jugar(); // Llama método del Padre
            txtLog.append(miMascota.getNombre() + " jugó un rato.\n");
            actualizarBarras();
        });

        btnEspecial.addActionListener(e -> {
            String mensaje = miMascota.escupirFuego(); // Llama método propio del Hijo
            txtLog.append(mensaje + "\n");
            actualizarBarras();
        });

        actualizarBarras();
    }

    private void actualizarBarras() {
        barHambre.setValue(miMascota.getHambre());
        barHambre.setString(miMascota.getHambre() + "/100");

        barFelicidad.setValue(miMascota.getFelicidad());
        barFelicidad.setString(miMascota.getFelicidad() + "/100");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterfazMascota().setVisible(true);
        });
    }
}
