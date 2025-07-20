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
public class Ingrediente {

    private String nombre;

    public Ingrediente(String nombre) {
        this.nombre = nombre;
    }

    public static List<Ingrediente> ingredientesSencilla() {
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("pan"));
        lista.add(new Ingrediente("carne"));
        return lista;
    }

    public static List<Ingrediente> ingredientesConQueso() {
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("pan"));
        lista.add(new Ingrediente("carne"));
        lista.add(new Ingrediente("queso"));
        return lista;
    }

    public static List<Ingrediente> ingredientesClasica() {
        List<Ingrediente> lista = new ArrayList<>();
        lista.add(new Ingrediente("pan"));
        lista.add(new Ingrediente("carne"));
        lista.add(new Ingrediente("queso"));
        lista.add(new Ingrediente("lechuga"));
        return lista;
    }

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

}
