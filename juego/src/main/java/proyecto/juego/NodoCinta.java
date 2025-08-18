/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
public class NodoCinta {
    Ingrediente ingrediente;  // Ingrediente que contiene este nodo
    NodoCinta siguiente;  // Referencia al siguiente nodo en la cinta
    
    public NodoCinta(Ingrediente ingrediente){
        this.ingrediente=ingrediente; // Guarda el ingrediente en el nodo
        this.siguiente=null;  // Al inicio no apunta a nadie
    }
}
