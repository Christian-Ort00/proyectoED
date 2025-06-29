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
    Ingrediente ingrediente;
    NodoCinta siguiente;
    
    public NodoCinta(Ingrediente ingrediente){
        this.ingrediente=ingrediente;
        this.siguiente=null;
    }
}
