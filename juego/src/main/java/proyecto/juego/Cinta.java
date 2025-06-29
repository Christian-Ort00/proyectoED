/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
public class Cinta {

    NodoCinta cabeza;
    NodoCinta ultimo;
    int cantidadMax;
    int cantidadActual;

    public Cinta() {
        this.cabeza = null;
        this.ultimo = null;
        this.cantidadMax = 5;
        this.cantidadActual = 0;
    }

    public boolean estaLleno() {
        return cantidadActual >= cantidadMax;
    }

    public void insertar(Ingrediente ingrediente) {
        if (!estaLleno()) {
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
        }else{
            System.out.println("Esta lleno!");
        }

    }
    
    public int getCantidadActual(){
        return cantidadActual;
    }

    public void recorrer() {
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
            return;

        }
        NodoCinta actual = cabeza;
        do {
            System.out.println(actual.ingrediente + "=>");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(cabeza)");
    }
    
    public Ingrediente tomar(){
        if(cabeza==null){
            System.out.println("No hya ingredientes");
            return null;
        }
        Ingrediente tomado=cabeza.ingrediente;
        if(cabeza==ultimo){
            cabeza=null;
            ultimo=null;
        }else{
            cabeza=cabeza.siguiente;
            ultimo.siguiente=cabeza;
        }
        cantidadActual--;
        System.out.println("Se tomo"+ tomado);
        return tomado;
    }

}
