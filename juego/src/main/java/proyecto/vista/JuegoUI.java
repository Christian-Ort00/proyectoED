package proyecto.vista;

import proyecto.juego.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class JuegoUI extends JFrame implements MotorJuego.TiempoListener {
    private MotorJuego motor;

    private JLabel labelTiempo;
    private JLabel labelPuntaje;
    private JPanel panelOrdenes;
    private JPanel panelCinta;
    private JPanel panelPreparados;
    private JLabel labelMensaje;  

    public JuegoUI() {
        motor = new MotorJuego();
        motor.agregarListenerTiempo(this);
        motor.llenarCintaInicial();
        motor.generarOrdenInicial();
        motor.iniciarJuego();

        setTitle("? OverCooked-Fide");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#f4f4f4"));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panelSuperior.setBackground(Color.decode("#2d3436"));

        labelTiempo = new JLabel("Tiempo: 300");
        labelTiempo.setForeground(Color.WHITE);
        labelTiempo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        labelPuntaje = new JLabel("Puntaje: 0");
        labelPuntaje.setForeground(Color.WHITE);
        labelPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        labelMensaje = new JLabel(" ");
labelMensaje.setForeground(Color.RED);
labelMensaje.setFont(new Font("Segoe UI", Font.BOLD, 13));



        panelSuperior.add(labelTiempo);
        panelSuperior.add(labelPuntaje);
        add(panelSuperior, BorderLayout.NORTH);

        panelOrdenes = new JPanel(new GridLayout(3, 1, 5, 5));
        panelOrdenes.setBorder(BorderFactory.createTitledBorder("Órdenes activas"));
        panelOrdenes.setBackground(Color.WHITE);
        add(panelOrdenes, BorderLayout.WEST);

        JPanel centro = new JPanel(new GridLayout(2, 1));

        panelCinta = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCinta.setBorder(BorderFactory.createTitledBorder("Cinta transportadora"));
        panelCinta.setBackground(Color.WHITE);
        centro.add(panelCinta);

        panelPreparados = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelPreparados.setBorder(BorderFactory.createTitledBorder("Ingredientes para preparar"));
        panelPreparados.setBackground(Color.WHITE);
        centro.add(panelPreparados);
        
        add(centro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(Color.decode("#dfe6e9"));

        panelBotones.add(labelMensaje);
        JButton botonTomar = new JButton("Tomar ingrediente");
        JButton botonTirar = new JButton("Tirar ingrediente");
        JButton botonCompletar = new JButton("Completar orden");
        JButton botonLimpiar = new JButton("Limpiar ingredientes");

        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);
        for (JButton btn : new JButton[]{botonTomar, botonTirar, botonCompletar, botonLimpiar}) {
            btn.setFont(buttonFont);
            btn.setFocusPainted(false);
            btn.setBackground(Color.decode("#74b9ff"));
            btn.setForeground(Color.WHITE);
        }

        botonTomar.addActionListener((ActionEvent e) -> {
            Ingrediente ing = motor.tomarIngrediente();
            if (ing != null) {
                motor.agregarIngredientePreparado(ing);
                actualizarVista();
            }
        });

        botonTirar.addActionListener((ActionEvent e) -> {
            motor.tomarIngrediente();
            actualizarVista();
        });

        botonCompletar.addActionListener((ActionEvent e) -> {
            Hamburguesa orden = motor.obtenerOrdenFrente();
            if (orden != null && ingredientesCoincidenPorNombre(motor.getIngredientesPreparados(), orden.getIngredientes())) {
                motor.completarOrden();
                motor.agregarPuntaje(orden.getPuntaje());
                motor.limpiarIngredientesPreparados();
                actualizarVista();
            } else {
                labelMensaje.setText("Los ingredientes no coinciden con la orden actual.");

            }
        });

        botonLimpiar.addActionListener((ActionEvent e) -> {
            motor.limpiarIngredientesPreparados();
            actualizarVista();
        });

        panelBotones.add(botonTomar);
        panelBotones.add(botonTirar);
        panelBotones.add(botonCompletar);
        panelBotones.add(botonLimpiar);

        add(panelBotones, BorderLayout.SOUTH);

        actualizarVista();
        setVisible(true);
    }

    private void actualizarVista() {
        panelCinta.removeAll();
        List<Ingrediente> cinta = motor.obtenerIngredientesCinta();
        for (int i = 0; i < cinta.size(); i++) {
            Ingrediente ing = cinta.get(i);
            JLabel label = new JLabel();
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            if (i == 0) {
                label.setText(" -> " + ing.getNombre()  );
                label.setForeground(Color.RED);
            } else {
                label.setText(ing.getNombre());
            }
            panelCinta.add(label);
        }

        panelOrdenes.removeAll();
        List<Hamburguesa> ordenes = motor.obtenerOrdenesActivas();
        if (ordenes.isEmpty()) {
            JLabel label = new JLabel(" No hay órdenes activas... esperando nuevas");
            label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            panelOrdenes.add(label);
        } else {
            for (Hamburguesa h : ordenes) {
                JLabel label = new JLabel(h.toString());
                label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                panelOrdenes.add(label);
            }
        }

        panelPreparados.removeAll();
        for (Ingrediente ing : motor.getIngredientesPreparados()) {
            JLabel label = new JLabel(ing.getNombre());
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panelPreparados.add(label);
        }

        labelPuntaje.setText("Puntaje: " + motor.getPuntaje());

        panelCinta.revalidate(); panelCinta.repaint();
        panelOrdenes.revalidate(); panelOrdenes.repaint();
        panelPreparados.revalidate(); panelPreparados.repaint();
    }

    @Override
    public void tiempoActualizado(int segundosRestantes) {
        SwingUtilities.invokeLater(() -> {
            labelTiempo.setText("Tiempo: " + segundosRestantes);
            actualizarVista();
        });
    }

    private boolean ingredientesCoincidenPorNombre(List<Ingrediente> preparados, List<Ingrediente> requeridos) {
        if (preparados.size() != requeridos.size()) return false;

        List<String> nombresPreparados = new ArrayList<>();
        for (Ingrediente ing : preparados) {
            nombresPreparados.add(ing.getNombre().toLowerCase());
        }

        for (Ingrediente ing : requeridos) {
            if (!nombresPreparados.remove(ing.getNombre().toLowerCase())) {
                return false;
            }
        }

        return true;
    }

  
}
