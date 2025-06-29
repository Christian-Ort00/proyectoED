/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.juego;

/**
 *
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
                Thread.sleep(1000);
                
                
                if(cinta.getCantidadActual()<=3){
                    System.out.println("HAY SUFICIENTES INGREDIENTES");
                    while(!cinta.estaLleno()){
                        Ingrediente nuevo = Ingrediente.generarAleatorio();
                        cinta.insertar(nuevo);
                        System.out.println("Se agrego"+nuevo.toString());
                    }
                }
                
                
            }catch(InterruptedException e){
                System.out.println("Hilo detenido");
                break;
            }
        }
    }
    
    
    
    
}
