/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Ingrediente {

    private String nombre;

    public Ingrediente(String nombre) {
        this.nombre = nombre;
    }
    

    // Devuelve una lista de ingredientes para una hamburguesa sencilla
    public static List<Ingrediente> ingredientesSencilla() {
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("pan"));
        lista.add(new Ingrediente("carne"));
        return lista;
    }

    // Devuelve una lista de ingredientes para una hamburguesa con queso
    public static List<Ingrediente> ingredientesConQueso() {
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("pan"));
        lista.add(new Ingrediente("carne"));
        lista.add(new Ingrediente("queso"));
        return lista;
    }

    // Devuelve una lista de ingredientes para una hamburguesa clásica
    public static List<Ingrediente> ingredientesClasica() {
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("pan"));
        lista.add(new Ingrediente("carne"));
        lista.add(new Ingrediente("queso"));
        lista.add(new Ingrediente("lechuga"));
        return lista;
    }

      // Genera un ingrediente aleatorio entre pan, carne, queso o lechuga
    public static Ingrediente generarAleatorio() {
        String[] nombres = {"pan", "carne", "queso", "lechuga"};
        int indice = (int) (Math.random() * nombres.length);
        return new Ingrediente(nombres[indice]);
    }

    public String getNombre() {
        return nombre;
    }

    public String toString() {
        return nombre;
    }

     // Dos ingredientes se consideran iguales si tienen el mismo nombre
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingrediente other = (Ingrediente) obj;
        return nombre.equalsIgnoreCase(other.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

}
