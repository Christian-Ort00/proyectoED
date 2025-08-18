/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Cinta {

    NodoCinta cabeza; // Primer nodo
    NodoCinta ultimo; // Ultimo nodo
    int cantidadMax;  // Maxima cantidad de ingredientes que se pueden colocar en la cinta
    int cantidadActual; //Cantidad actual de ingredientes en la cinta

    public Cinta() {
        this.cabeza = null;
        this.ultimo = null;
        this.cantidadMax = 5; // la cinta puede tener maximo 5 ingredientes
        this.cantidadActual = 0;
    }

    //Verifica si la cinta esta llena 
    public boolean estaLleno() {
        return cantidadActual >= cantidadMax;
    }

    // Inserta un ingrediente en la cinta
    public void insertar(Ingrediente ingrediente) {
        if (!estaLleno()) {  // Solo se puede insertar si no está llena
            NodoCinta nuevo = new NodoCinta(ingrediente);
            if (cabeza == null) {
                cabeza = nuevo;
                ultimo = nuevo;
                nuevo.siguiente = cabeza;
            } else {
                nuevo.siguiente = cabeza;
                ultimo.siguiente = nuevo;
                ultimo = nuevo;
            }
            cantidadActual++;
        } else {
            System.out.println("Esta lleno!");
        }

    }

     // Devuelve la cantidad actual de ingredientes
    public int getCantidadActual() {
        return cantidadActual;
    }

    // Recorre e imprime todos los ingredientes de la cinta
    public void recorrer() {
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
            return;

        }
        NodoCinta actual = cabeza;
        do {
            System.out.println(actual.ingrediente + "=>");
            actual = actual.siguiente;
        } while (actual != cabeza); // Se detiene cuando vuelve al inicio
        System.out.println("(cabeza)");
    }

    
    
    // Toma el primer ingrediente de la cinta
    public Ingrediente tomar() {
        if (cabeza == null) {
            System.out.println("No hay ingredientes");
            return null;
        }
        Ingrediente tomado = cabeza.ingrediente; // Guarda el ingrediente a sacar

        if (cabeza == ultimo) { // Si solo hay un nodo, la cinta queda vacía
            cabeza = null;
            ultimo = null;
        } else { // Si hay más de uno, avanza la cabeza y ajusta el último
            cabeza = cabeza.siguiente;
            ultimo.siguiente = cabeza;
        }
        cantidadActual--;
        System.out.println("Se tomo " + tomado);
        return tomado;
    }

    public void avanzar() { // Hace avanzar la cinta
        if (cabeza != null && ultimo != null) {
            cabeza = cabeza.siguiente;
            ultimo = ultimo.siguiente;
        }
    }

    // Devuelve una lista con todos los ingredientes de la cinta
    public List<Ingrediente> obtenerListaIngredientes() {
        List<Ingrediente> lista = new ArrayList<>();
        if (cabeza == null) {
            return lista; // Si está vacía, devuelve lista vacía
        }

        NodoCinta actual = cabeza;
        do {
            lista.add(actual.ingrediente);
            actual = actual.siguiente;
        } while (actual != cabeza);

        return lista;
    }
    public List<Ingrediente> obtenerIngredientes() { // Otra forma de obtener todos los ingredientes 
            List<Ingrediente> ingredientes = new ArrayList<>();
            if (cabeza == null) return ingredientes;
            NodoCinta actual = cabeza;
            do {
                ingredientes.add(actual.ingrediente);
                actual = actual.siguiente;
            } while (actual != cabeza);
            return ingredientes;
        }

}

