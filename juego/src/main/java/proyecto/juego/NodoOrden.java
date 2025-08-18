/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
public class NodoOrden {
       Hamburguesa hamburguesa;  // La hamburguesa que se guarda en este nodo
       NodoOrden siguiente;
       
       public NodoOrden(Hamburguesa hamburguesa){
           this.hamburguesa=hamburguesa; // Guardamos la hamburguesa
           this.siguiente=null; // Al inicio no apunta a otro nodo
       }
       
       
     
       
}
