package proyecto.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("OverCooked-Fide - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#f4f4f4"));
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("OverCooked-Fide", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.decode("#2d3436"));
        add(titulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        panelBotones.setBackground(Color.decode("#f4f4f4"));

        JButton btnJugar = new JButton("Empezar Juego");
        JButton btnSalir = new JButton("Salir");

        Font fontBoton = new Font("Segoe UI", Font.PLAIN, 16);
        btnJugar.setFont(fontBoton);
        btnSalir.setFont(fontBoton);

        btnJugar.setBackground(Color.decode("#74b9ff"));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setFocusPainted(false);

        btnSalir.setBackground(Color.decode("#d63031"));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);

        // Eventos
        btnJugar.addActionListener((ActionEvent e) -> {
            dispose(); // Cierra el menú
            new JuegoUI(); // Abre el juego
        });

        btnSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        panelBotones.add(btnJugar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    
}
