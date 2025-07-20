package proyecto.juego;

import java.util.ArrayList;
import java.util.List;

public class Orden {

    private NodoOrden frente;
    private NodoOrden fin;
    private int capacidadMax = 3;
    private int tamanoActual;

    public Orden() {
        this.frente = null;
        this.fin = null;
        this.tamanoActual = 0;
    }

    public boolean estaLlena() {
        return tamanoActual >= capacidadMax;
    }

    public void encolar(Hamburguesa hamburguesa) {
        if (estaLlena()) {
            return;
        }

        NodoOrden nuevo = new NodoOrden(hamburguesa);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanoActual++;
    }

    public Hamburguesa verFrente() {
        if (frente == null) {
            return null;
        }
        return frente.hamburguesa;
    }

    public void desencolar() {
        if (frente == null) {
            return;
        }
        frente = frente.siguiente;
        tamanoActual--;
        if (frente == null) {
            fin = null;
        }
    }

   

    public List<Hamburguesa> obtenerOrdenes() {
        List<Hamburguesa> lista = new ArrayList<>();
        NodoOrden actual = frente;
        while (actual != null) {
            lista.add(actual.hamburguesa);
            actual = actual.siguiente;
        }
        return lista;
    }

    public void generarOrdenAleatoria() {
        if (!estaLlena()) {
            Hamburguesa nueva = Hamburguesa.generarAleatoria();
            encolar(nueva);
        }
    
    }
    

    

    public boolean estaVacia() {
        return tamanoActual == 0;
    }

}
