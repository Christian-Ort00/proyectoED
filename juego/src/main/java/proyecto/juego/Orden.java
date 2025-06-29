/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
public class Orden {

    private NodoOrden frente;
    private NodoOrden fin;
    private int capacidadMax;
    private int tamanoActual;

    public Orden() {
        this.frente = null;
        this.fin = null;
        this.tamanoActual = 0;
        this.capacidadMax = 3;
    }

    public boolean estaLlena() {
        return tamanoActual >= capacidadMax;
    }

    public void encolar(Hamburguesa hamburguesa) {

        if (!estaLlena()) {
            NodoOrden nuevo = new NodoOrden(hamburguesa);
            if (fin != null) {
                fin.siguiente = nuevo;
            }
            fin = nuevo;
            if (frente == null) {
                frente = nuevo;
            }
            tamanoActual++;
        }

    }

    public Hamburguesa desencolar() throws Exception {
        if (frente == null) {
            throw new Exception("La cola esta vacia");
        }
        Hamburguesa dato = frente.hamburguesa;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return dato;
    }
    
    public Hamburguesa verFrente() throws Exception{
        if(frente==null){
            throw new Exception("La cola esta vacia");
            
        }
        return frente.hamburguesa;
    }
    
    public boolean estaVacia(){
        return frente==null;
    }
    

}
