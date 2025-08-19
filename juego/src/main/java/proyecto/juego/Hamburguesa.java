/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

import java.util.List;
import java.util.Objects;

;
/**
 *
 * @author Usuario
 */
public class Hamburguesa {
    private List<Ingrediente> ingredientes; // Ingredientes que forman la hamburguesa
    private int puntaje; // Puntaje que otorga al completarla
    private String tipo;
    
    private Hamburguesa(List<Ingrediente> ingredientes, int puntaje, String tipo){
        this.ingredientes=ingredientes;
        this.puntaje = puntaje;
        this.tipo= tipo;
    }


    
    
     // Métodos para crear hamburguesas específicas
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

    // Genera una hamburguesa aleatoria (puede ser cualquiera de las tres anteriores)
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
    
  public List<Ingrediente> getIngredientes() {
    return ingredientes;
}

public int getPuntaje() {
    return puntaje;
}

     // Representación en texto
    @Override
    public String toString(){
        return  tipo + " tiene " + ingredientes.toString();  
    }

     // Comparación entre hamburguesas
     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Hamburguesa otra = (Hamburguesa) obj;
        return tipo.equals(otra.tipo) && ingredientes.equals(otra.ingredientes);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(tipo, ingredientes);
    }
}


