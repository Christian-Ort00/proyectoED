package proyecto.juego;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {

    // Variables principales del juego
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
        this.tiempoRestante = 300; // El juego dura 300 segundos (5 minutos)
        this.tiempoListeners = new ArrayList<>();
    }

    public void generarOrdenInicial() {     // Genera una orden inicial de hamburguesa
        ordenes.generarOrdenAleatoria();
    }

    public void llenarCintaInicial() {   // Llena la cinta con ingredientes al inicio del juego
        while (!cinta.estaLleno()) {
            cinta.insertar(Ingrediente.generarAleatorio());
        }
    }

     // Inicia el juego (tiempo, monitoreo de cinta y generación de órdenes)
    public void iniciarJuego() {
        Thread temporizador = new Thread(() -> {  // Hilo que controla el temporizador del juego
            while (tiempoRestante > 0) {
                try {
                    Thread.sleep(1000);
                    tiempoRestante--;
                    for (TiempoListener listener : tiempoListeners) {
                        listener.tiempoActualizado(tiempoRestante);
                    }
                } catch (InterruptedException e) {
                    break; // Si se interrumpe, se detiene el temporizador
                }
            }
        });
        temporizador.start(); // Se inicia el temporizador
        

        // Hilo que se encarga de revisar si la cinta necesita más ingredientes
        MonitorCinta monitor = new MonitorCinta(cinta);
        new Thread(monitor).start();
        
        // Hilo que genera nuevas órdenes de hamburguesas cada cierto tiempo
        GeneradorOrdenes generadorOrdenes= new GeneradorOrdenes(ordenes);
        Thread hiloGenerador = new Thread(generadorOrdenes);
        hiloGenerador.start();
        
    }

    // Devuelve la lista de órdenes activas
    public List<Hamburguesa> obtenerOrdenesActivas() {
        return ordenes.obtenerOrdenes();
    }
    

    // Devuelve la lista de ingredientes actuales en la cinta
    public List<Ingrediente> obtenerIngredientesCinta() {
        return cinta.obtenerIngredientes();
    }
    

     // Toma un ingrediente de la cinta y rellena si hay pocos
    public Ingrediente tomarIngrediente()  {
        Ingrediente ing = cinta.tomar();
        if (cinta.getCantidadActual() <= 3) {
            while (!cinta.estaLleno()) {
                cinta.insertar(Ingrediente.generarAleatorio());
            }
        }
        return ing;
    }

    // Permite tirar un ingrediente (descartarlo de la cinta)
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

    
    // Valida si los ingredientes preparados por el jugador coinciden con la orden actual
    public boolean validarOrden() {
        Hamburguesa ordenActual = obtenerOrdenFrente();
        if (ordenActual == null) return false;

        List<Ingrediente> ordenIngredientes = ordenActual.getIngredientes();
        if (ingredientesPreparados.size() != ordenIngredientes.size()) return false;

        for (int i = 0; i < ordenIngredientes.size(); i++) {     // Compara ingrediente por ingrediente
            if (!ingredientesPreparados.get(i).equals(ordenIngredientes.get(i))) {
                return false;
            }
        }
        return true;
    }

     public void tirarUltimoIngrediente() {    // Quita el último ingrediente que el jugador colocó
        if (!ingredientesPreparados.isEmpty()) {
            Ingrediente eliminado = ingredientesPreparados.remove(ingredientesPreparados.size() - 1);
            System.out.println("Ingrediente eliminado: " + eliminado);
        }
    }
    
    public void agregarIngredientePreparado(Ingrediente ing) {  // Agrega un ingrediente a la preparación del jugador
        ingredientesPreparados.add(ing);
    }

    public List<Ingrediente> getIngredientesPreparados() {  // Devuelve los ingredientes que el jugador ha colocado
        return ingredientesPreparados;
    }

    public void limpiarIngredientesPreparados() { // Limpia todos los ingredientes preparados (para reiniciar la orden)
        ingredientesPreparados.clear();
    }

    public void agregarPuntaje(int puntos) { 
    // Aumenta el puntaje del jugador
        this.puntaje += puntos;
    }

    public int getPuntaje() { // Devuelve el puntaje actual
        return this.puntaje;
    }

    public void agregarListenerTiempo(TiempoListener listener) {
        tiempoListeners.add(listener);
    }


    public interface TiempoListener {

        void tiempoActualizado(int segundosRestantes); 
    // Interfaz que notifica cuando cambia el tiempo
    }

     // Devuelve la primera orden de la lista (la que está en frente)
    public Hamburguesa obtenerOrdenFrente() {
        if (!ordenes.estaVacia()) {
            return ordenes.obtenerOrdenes().get(0);
        }
        return null;
    }

     // Completa (elimina) la orden que está al frente de la cola
    public void completarOrden() {
        ordenes.desencolar();
    }

}
