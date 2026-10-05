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

    // Referencia base para invocar acciones polimórficas
    private Mascota miMascota = new Pokemon(
        "Pikachu",
        3,
        Pokemon.TipoElemento.ELECTRICO,
        80,
        1
    );

    // Componentes de la interfaz
    private JLabel lblNombre;
    private JProgressBar barHambre, barFelicidad;
    private JButton btnAlimentar, btnJugar, btnEspecial, btnTruco, btnPokedescanso, btnGritoPokemon;
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

        lblNombre = new JLabel("Mascota: " + miMascota.getName(), SwingConstants.CENTER);
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
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 5, 5));

        btnAlimentar = new JButton("🍕 Alimentar");
        btnJugar = new JButton("🫐 Dar Bayas");
        btnEspecial = new JButton("⭐ Ataque Especial"); // Método de la subclase
        btnTruco = new JButton("🎪 Truco");
        btnPokedescanso = new JButton("😴 Pokedescanso");
        btnGritoPokemon = new JButton("🎭 Grito Pokémon");


        panelBotones.add(btnAlimentar);
        panelBotones.add(btnJugar);
        panelBotones.add(btnEspecial);
        panelBotones.add(btnTruco);
        panelBotones.add(btnPokedescanso);
        panelBotones.add(btnGritoPokemon);

        // Agregar paneles al marco principal
        add(panelEstado, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnAlimentar.addActionListener(e -> {
            String mensaje = miMascota.alimentar();
            txtLog.append(mensaje + "\n");
            actualizarBarras();
        });

        btnJugar.addActionListener(e -> {
            String entrada = JOptionPane.showInputDialog(
                this,
                "Cuantas bayas quieres darle?",
                "Dar Bayas",
                JOptionPane.QUESTION_MESSAGE
            );
            if (entrada == null) {
                return;
            }

            try {
                int cantidadBayas = Integer.parseInt(entrada.trim());
                if (cantidadBayas <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingresa una cantidad mayor que cero.");
                    return;
                }
                String mensaje = miMascota.alimentar(cantidadBayas);
                txtLog.append(mensaje + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingresa una cantidad entera válida.");
                return;
            }
            actualizarBarras();
        });

        btnEspecial.addActionListener(e -> {
            String mensaje = miMascota.atacarEspecial();
            txtLog.append(mensaje + "\n");
            actualizarBarras();
        });

        btnTruco.addActionListener(e -> {
            String mensaje = miMascota.hacerTruco();
            txtLog.append(mensaje + "\n");
            actualizarBarras();
        });

        btnPokedescanso.addActionListener(e -> {
            String mensaje = miMascota.dormir();
            txtLog.append(mensaje + "\n");
            actualizarBarras();
        });

        btnGritoPokemon.addActionListener(e -> {
            String mensaje = miMascota.expresarEmocion();
            txtLog.append(mensaje + "\n");
            actualizarBarras();
        });

        actualizarBarras();
    }

    private void actualizarBarras() {
        barHambre.setValue(miMascota.getHunger());
        barHambre.setString(miMascota.getHunger() + "/100");

        barFelicidad.setValue(miMascota.getHappiness());
        barFelicidad.setString(miMascota.getHappiness() + "/100");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterfazMascota().setVisible(true);
        });
    }
}
