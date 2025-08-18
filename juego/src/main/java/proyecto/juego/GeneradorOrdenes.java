/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
// Clase que genera órdenes de hamburguesas cada cierto tiempo
public class GeneradorOrdenes implements Runnable{
    private Orden ordenes;
    
    public GeneradorOrdenes(Orden ordenes){
        this.ordenes = ordenes;
    }
    
    
    @Override
    public void run(){
         // Ciclo infinito para estar generando órdenes
        while(true){
            try{
                Thread.sleep(20000); // Espera 20 segundos antes de generar nuevas órdenes
                
                while(!ordenes.estaLlena()){
                    Hamburguesa nueva = Hamburguesa.generarAleatoria();   // Se crea una hamburguesa aleatoria
                    ordenes.encolar(nueva); // Se agrega la hamburguesa a la cola de órdenes
                    System.out.println("Nueva orden generada: "+ nueva);  // Mensaje en consola para saber que se creó la orden
                }
                
            }catch(InterruptedException e){
                // Si el hilo es interrumpido, se muestra un mensaje y se rompe el ciclo
                System.out.println("Hilo Interrumpido");
                break;
            }
        }
    }
    
    
}
