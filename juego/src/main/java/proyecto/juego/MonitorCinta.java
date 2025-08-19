/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 * Clase que se encarga de "vigilar" la cinta de ingredientes.
 * Cada cierto tiempo revisa si hay pocos ingredientes y agrega más.
 * @author Usuario
 */
public class MonitorCinta implements Runnable {
    private Cinta cinta;
    
    public MonitorCinta(Cinta cinta){
        this.cinta=cinta;
        
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(1000); // Espera 1 segundo antes de revisar la cinta
                
                 // Si hay 3 o menos ingredientes en la cinta
                if(cinta.getCantidadActual()<=3){
                    System.out.println("HAY SUFICIENTES INGREDIENTES");
                    while(!cinta.estaLleno()){ // Agrega ingredientes hasta que la cinta quede llena
                        Ingrediente nuevo = Ingrediente.generarAleatorio();
                        cinta.insertar(nuevo);
                        System.out.println("Se agrego"+nuevo.toString());
                    }
                }
                
                
            }catch(InterruptedException e){ // Si el hilo es interrumpido, muestra un mensaje y termina el bucle
                System.out.println("Hilo detenido");
                break;
            }
        }
    }
    
    
    
    
}
