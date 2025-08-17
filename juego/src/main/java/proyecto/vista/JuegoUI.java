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
        // --- Lógica del juego ---
        motor = new MotorJuego();
        motor.agregarListenerTiempo(this);
        motor.llenarCintaInicial();
        motor.generarOrdenInicial();
        motor.iniciarJuego();

        // --- Ventana ---
        setTitle("OverCooked-Fide");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#f4f4f4"));

        // --- Panel superior: tiempo, puntaje y salir ---
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panelSuperior.setBackground(Color.decode("#2d3436"));

        labelTiempo = new JLabel("Tiempo: 300");
        labelTiempo.setForeground(Color.WHITE);
        labelTiempo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        labelPuntaje = new JLabel("Puntaje: 0");
        labelPuntaje.setForeground(Color.WHITE);
        labelPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton botonSalirMenu = new JButton("Salir al Menú");
        botonSalirMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botonSalirMenu.setBackground(Color.decode("#d63031"));
        botonSalirMenu.setForeground(Color.WHITE);
        botonSalirMenu.setFocusPainted(false);
        botonSalirMenu.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        botonSalirMenu.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        panelSuperior.add(labelTiempo);
        panelSuperior.add(labelPuntaje);
        panelSuperior.add(botonSalirMenu);
        add(panelSuperior, BorderLayout.NORTH);

        // --- Panel lateral: órdenes activas ---
        panelOrdenes = new JPanel(new GridLayout(3, 1, 5, 5));
        panelOrdenes.setBorder(BorderFactory.createTitledBorder("Órdenes activas"));
        panelOrdenes.setBackground(Color.WHITE);
        add(panelOrdenes, BorderLayout.WEST);

        // --- Panel central: cinta + ingredientes preparados ---
        JPanel centro = new JPanel(new GridLayout(2, 1));

        panelCinta = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCinta.setBorder(BorderFactory.createTitledBorder("Cinta transportadora"));
        panelCinta.setBackground(Color.WHITE);
        centro.add(panelCinta);

        panelPreparados = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelPreparados.setBorder(BorderFactory.createTitledBorder("Ingredientes preparados"));
        panelPreparados.setBackground(Color.WHITE);
        centro.add(panelPreparados);

        add(centro, BorderLayout.CENTER);

        // --- Panel inferior: mensajes y botones ---
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(Color.decode("#dfe6e9"));

        labelMensaje = new JLabel(" ");
        labelMensaje.setForeground(Color.RED);
        labelMensaje.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelBotones.add(labelMensaje);

        JButton botonTomar = crearBoton("Tomar ingrediente", "#74b9ff");
        JButton botonTirar = crearBoton("Tirar ingrediente", "#fab1a0");
        JButton botonCompletar = crearBoton("Completar orden", "#55efc4");
        JButton botonLimpiar = crearBoton("Limpiar ingredientes", "#ffeaa7");

        // Acciones
        botonTomar.addActionListener((ActionEvent e) -> {
            Ingrediente ing = motor.tomarIngrediente();
            if (ing != null) {
                motor.agregarIngredientePreparado(ing);
                labelMensaje.setText("Tomaste: " + ing.getNombre());
                actualizarVista();
            } else {
                labelMensaje.setText("No hay ingrediente al frente.");
            }
        });

        botonTirar.addActionListener((ActionEvent e) -> {
            Ingrediente tirado = motor.tomarIngrediente();
            if (tirado != null) {
                labelMensaje.setText("Tiraste: " + tirado.getNombre());
            } else {
                labelMensaje.setText("No hay ingrediente al frente.");
            }
            actualizarVista();
        });

        botonCompletar.addActionListener((ActionEvent e) -> {
            Hamburguesa orden = motor.obtenerOrdenFrente();
            if (orden != null && ingredientesCoincidenPorNombre(motor.getIngredientesPreparados(), orden.getIngredientes())) {
                motor.completarOrden();
                motor.agregarPuntaje(orden.getPuntaje());
                motor.limpiarIngredientesPreparados();
                labelMensaje.setText("¡Orden completada!");
                actualizarVista();
            } else {
                labelMensaje.setText("Los ingredientes no coinciden con la orden actual.");
            }
        });

        botonLimpiar.addActionListener((ActionEvent e) -> {
            motor.limpiarIngredientesPreparados();
            labelMensaje.setText("Ingredientes limpiados.");
            actualizarVista();
        });

        panelBotones.add(botonTomar);
        panelBotones.add(botonTirar);
        panelBotones.add(botonCompletar);
        panelBotones.add(botonLimpiar);

        add(panelBotones, BorderLayout.SOUTH);

        // Render inicial
        actualizarVista();
        setVisible(true);
    }



    private JButton crearBoton(String texto, String colorHex) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBackground(Color.decode(colorHex));
        btn.setForeground(Color.BLACK);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return btn;
    }

    
    private ImageIcon loadIcon(String file, int w, int h) {
        java.net.URL url = getClass().getResource("/proyecto/imagenes/" + file);
        if (url == null) {
            // Ayuda para depurar nombres/rutas
            System.err.println("No se encontró imagen: " + file + " en /proyecto/imagenes/");
            return null;
        }
        Image img = new ImageIcon(url).getImage();
        Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

   
    private ImageIcon iconFor(Ingrediente ing, int w, int h) {
        String key = (ing.getNombre() == null) ? "" : ing.getNombre().toLowerCase().trim();
        String file = switch (key) {
            case "pan", "pan superior", "pan inferior" -> "pan.jpg";
            case "carne" -> "carne.jpeg";
            case "queso" -> "queso.jpg";      // ajusta a .jpeg si tu archivo lo es
            case "lechuga" -> "lechuga.jpeg";
            default -> null;
        };
        return (file != null) ? loadIcon(file, w, h) : null;
    }

   
    private JLabel makeIconLabel(String text, ImageIcon icon) {
        JLabel lbl = new JLabel(text, icon, SwingConstants.CENTER);
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl.setVerticalTextPosition(SwingConstants.BOTTOM);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return lbl;
    }

    

    private void actualizarVista() {
        // Cinta transportadora
        panelCinta.removeAll();
        List<Ingrediente> cinta = motor.obtenerIngredientesCinta();
        for (int i = 0; i < cinta.size(); i++) {
            Ingrediente ing = cinta.get(i);
            ImageIcon icon = iconFor(ing, 64, 64);
            JLabel lbl;
            if (icon != null) {
                String texto = (i == 0) ? "→ " + ing.getNombre() : " ";
                lbl = makeIconLabel(texto, icon);
                if (i == 0) {
                    lbl.setForeground(Color.RED);
                    lbl.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                } else {
                    lbl.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                }
            } else {
                lbl = new JLabel((i == 0 ? "→ " : "") + ing.getNombre());
                if (i == 0) lbl.setForeground(Color.RED);
            }
            panelCinta.add(lbl);
        }

        // Órdenes activas (recetas visuales)
        panelOrdenes.removeAll();
        List<Hamburguesa> ordenes = motor.obtenerOrdenesActivas();
        if (ordenes.isEmpty()) {
            JLabel label = new JLabel(" No hay órdenes activas... esperando nuevas");
            label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            panelOrdenes.add(label);
        } else {
            for (Hamburguesa h : ordenes) {
                JPanel card = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createTitledBorder("Orden (" + h.getPuntaje() + " pts)"));

                for (Ingrediente ing : h.getIngredientes()) {
                    ImageIcon icon = iconFor(ing, 32, 32);
                    JLabel lbl = (icon != null)
                            ? makeIconLabel(ing.getNombre(), icon)
                            : new JLabel(ing.getNombre());
                    card.add(lbl);
                }
                panelOrdenes.add(card);
            }
        }

        // Ingredientes preparados
        panelPreparados.removeAll();
        for (Ingrediente ing : motor.getIngredientesPreparados()) {
            ImageIcon icon = iconFor(ing, 64, 64);
            JLabel lbl = (icon != null)
                    ? makeIconLabel(ing.getNombre(), icon)
                    : new JLabel(ing.getNombre());
            panelPreparados.add(lbl);
        }

        // Puntaje
        labelPuntaje.setText("Puntaje: " + motor.getPuntaje());

        // Refrescar
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

    /* ===================== Comparación de ingredientes ===================== */

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
