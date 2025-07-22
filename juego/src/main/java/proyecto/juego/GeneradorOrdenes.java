/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
public class GeneradorOrdenes implements Runnable{
    private Orden ordenes;
    
    public GeneradorOrdenes(Orden ordenes){
        this.ordenes = ordenes;
    }
    
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(20000);
                
                while(!ordenes.estaLlena()){
                    Hamburguesa nueva = Hamburguesa.generarAleatoria();
                    ordenes.encolar(nueva);
                    System.out.println("Nueva orden generada: "+ nueva);
                }
                
            }catch(InterruptedException e){
                System.out.println("Hilo Interrumpido");
                break;
            }
        }
    }
    
    
}
