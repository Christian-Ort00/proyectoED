package proyecto.juego;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {

    private Cinta cinta;
    private Orden ordenes;
    private List<Ingrediente> ingredientesPreparados;
    private int puntaje;
    private int tiempoRestante;
    private List<TiempoListener> tiempoListeners;

    public MotorJuego() {
        this.cinta = new Cinta();
        this.ordenes = new Orden();
        this.ingredientesPreparados = new ArrayList<>();
        this.puntaje = 0;
        this.tiempoRestante = 300; 
        this.tiempoListeners = new ArrayList<>();
    }

    public void generarOrdenInicial() {
        ordenes.generarOrdenAleatoria();
    }

    public void llenarCintaInicial() {
        while (!cinta.estaLleno()) {
            cinta.insertar(Ingrediente.generarAleatorio());
        }
    }

    public void iniciarJuego() {
        Thread temporizador = new Thread(() -> {
            while (tiempoRestante > 0) {
                try {
                    Thread.sleep(1000);
                    tiempoRestante--;
                    for (TiempoListener listener : tiempoListeners) {
                        listener.tiempoActualizado(tiempoRestante);
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        temporizador.start();
        
       
        MonitorCinta monitor = new MonitorCinta(cinta);
        new Thread(monitor).start();
        
        
        GeneradorOrdenes generadorOrdenes= new GeneradorOrdenes(ordenes);
        Thread hiloGenerador = new Thread(generadorOrdenes);
        hiloGenerador.start();
        
    }

    public List<Hamburguesa> obtenerOrdenesActivas() {
        return ordenes.obtenerOrdenes();
    }

    public List<Ingrediente> obtenerIngredientesCinta() {
        return cinta.obtenerIngredientes();
    }

    public Ingrediente tomarIngrediente()  {
        Ingrediente ing = cinta.tomar();
        if (cinta.getCantidadActual() <= 3) {
            while (!cinta.estaLleno()) {
                cinta.insertar(Ingrediente.generarAleatorio());
            }
        }
        return ing;
    }

    public void tirarIngrediente ()  {
        Ingrediente ing = cinta.tomar();
        if (ing != null) {
            System.out.println("Haz tirado el ingrediente: " + ing);
            cinta.avanzar(); 
        }
        if (cinta.getCantidadActual() <= 3) {
            while (!cinta.estaLleno()) {
                cinta.insertar(Ingrediente.generarAleatorio());
            }
        }
     }

        
        

    public void agregarIngredientePreparado(Ingrediente ing) {
        ingredientesPreparados.add(ing);
    }

    public List<Ingrediente> getIngredientesPreparados() {
        return ingredientesPreparados;
    }

    public void limpiarIngredientesPreparados() {
        ingredientesPreparados.clear();
    }

    public void agregarPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void agregarListenerTiempo(TiempoListener listener) {
        tiempoListeners.add(listener);
    }


    public interface TiempoListener {

        void tiempoActualizado(int segundosRestantes);
    }

    public Hamburguesa obtenerOrdenFrente() {
        if (!ordenes.estaVacia()) {
            return ordenes.obtenerOrdenes().get(0);
        }
        return null;
    }

    public void completarOrden() {
        ordenes.desencolar();
    }

}
