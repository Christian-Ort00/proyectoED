package proyecto.juego;

import java.util.ArrayList;
import java.util.List;

public class Orden {

    private NodoOrden frente;
    private NodoOrden fin;
    private int capacidadMax = 3;  // Capacidad máxima de la cola (número de órdenes que puede guardar)
    private int tamanoActual;

    public Orden() {
        this.frente = null;
        this.fin = null;
        this.tamanoActual = 0;
    }

    public boolean estaLlena() { // Verifica si la cola está llena
        return tamanoActual >= capacidadMax;
    }

     // Agrega una nueva hamburguesa a la cola (al final)
    public void encolar(Hamburguesa hamburguesa) {
        if (estaLlena()) {  // Si está llena, no hace nada
            return;
        }

        NodoOrden nuevo = new NodoOrden(hamburguesa);   // Crear un nuevo nodo con la hamburguesa
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanoActual++; 
        // Aumentar el tamaño actual de la cola
    }

    // Muestra la hamburguesa que está al frente de la cola (sin eliminarla)
    public Hamburguesa verFrente() {
        if (frente == null) {
            return null;
        }
        return frente.hamburguesa;
    }

      // Elimina la hamburguesa que está al frente de la cola
    public void desencolar() {
        if (frente == null) {
            return;
        }
        frente = frente.siguiente;    // Mover el frente al siguiente nodo
        tamanoActual--;
        if (frente == null) {
            fin = null;
        }
    }

   

    // Devuelve todas las órdenes en una lista
    public List<Hamburguesa> obtenerOrdenes() {
        List<Hamburguesa> lista = new ArrayList<>();
        NodoOrden actual = frente;
        while (actual != null) {   // Recorre la cola y agrega cada hamburguesa a la lista
            lista.add(actual.hamburguesa);
            actual = actual.siguiente;
        }
        return lista;
    }

     // Genera una hamburguesa aleatoria y la encola (si hay espacio)
    public void generarOrdenAleatoria() {
        if (!estaLlena()) {
            Hamburguesa nueva = Hamburguesa.generarAleatoria();
            encolar(nueva);
        }
    
    }
    

    

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return tamanoActual == 0;
    }

}
