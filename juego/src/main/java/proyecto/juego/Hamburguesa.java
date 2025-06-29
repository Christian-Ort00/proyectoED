/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

import java.util.List;

;
/**
 *
 * @author Usuario
 */
public class Hamburguesa {
    private List<Ingrediente> ingredientes;
    private int puntaje;
    private String tipo;
    
    private Hamburguesa(List<Ingrediente> ingredientes, int puntaje, String tipo){
        this.ingredientes=ingredientes;
        this.puntaje = puntaje;
        this.tipo= tipo;
    }


    
    
    
    public static Hamburguesa crearHamburguesaSencilla(){
        List<Ingrediente> ingredientes = Ingrediente.ingredientesSencilla();
        return new Hamburguesa(ingredientes,5,"Sencilla");
    }
    public static Hamburguesa crearHamburguesaConQueso(){
        List<Ingrediente> ingredientes = Ingrediente.ingredientesConQueso();
        return new Hamburguesa(ingredientes,10,"Con Queso");
    }
    public static Hamburguesa crearHamburguesaClasica(){
        List<Ingrediente> ingredientes = Ingrediente.ingredientesClasica();
        return new Hamburguesa(ingredientes,15,"Clasica");
    }
    
    public static Hamburguesa generarAleatoria(){
        int tipo=(int)(Math.random()*3);
        switch(tipo){
            case 0:
                return Hamburguesa.crearHamburguesaClasica();
            case 1:
                return Hamburguesa.crearHamburguesaConQueso();
            case 2:
                return Hamburguesa.crearHamburguesaSencilla();
            default:
                return Hamburguesa.crearHamburguesaSencilla();
        }
    }
    
    
    
   
    
    @Override
    public String toString(){
        return "La hamburguea es "+ tipo + "y tiene " + ingredientes.toString();  
    }
    
}
