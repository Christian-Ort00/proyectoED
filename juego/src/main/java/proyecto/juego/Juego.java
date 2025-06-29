/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package proyecto.juego;

/**
 *
 * @author Usuario
 */
public class Juego {

    public static void main(String[] args) {

        Cinta cinta = new Cinta();

        // Llenar con 5 ingredientes al inicio
        for (int i = 0; i < 5; i++) {
            cinta.insertar(Ingrediente.generarAleatorio());
        }

        System.out.println("🎬 Cinta inicial:");
        cinta.recorrer();

        // Iniciar el hilo que monitorea la cinta
        MonitorCinta monitor = new MonitorCinta(cinta);
        new Thread(monitor).start();

        try {
            Thread.sleep(3000); // esperar 3 segundos
            cinta.tomar();
            cinta.tomar(); // ahora quedan 3 → el monitor debería rellenar

        } catch (InterruptedException e) {
            System.out.println("❌ Error en el hilo principal.");
        }

        Orden ordenes = new Orden();

        while (true) {
            try {
                Thread.sleep(20000); // Esperar 20 segundos

                if (!ordenes.estaLlena()) {
                    Hamburguesa nueva = Hamburguesa.generarAleatoria(); // método static
                    ordenes.encolar(nueva);
                    System.out.println("✅ Nueva orden generada: " + nueva);
                } else {
                    System.out.println("⚠️ La cola de órdenes está llena.");
                }

                // Mostrar el frente actual de la cola
                Hamburguesa frente = null;
                try {
                    frente = ordenes.verFrente();
                } catch (Exception e) {
                    System.out.println("⚠️ Ocurrió un error al obtener el frente de la cola: " + e.getMessage());
                }

                if (frente != null) {
                    System.out.println("Frente actual: " + frente);
                } else {
                    System.out.println("No hay órdenes en la cola.");
                }

            } catch (InterruptedException e) {
                System.out.println("⛔ Hilo interrumpido.");
                break;
            }
        }

//        Hamburguesa ham1 = Hamburguesa.crearHamburguesaClasica();
//        Hamburguesa ham2 = Hamburguesa.crearHamburguesaSencilla();
//        Hamburguesa ham3 = Hamburguesa.crearHamburguesaConQueso();
//        
//        Orden ordenes = new Orden();
//        ordenes.encolar(ham3);
//        ordenes.encolar(ham2);
//        ordenes.encolar(ham1);
//        
//        
//        
//        
//        try{
//            System.out.println(ordenes.verFrente().toString());
//            ordenes.desencolar();
//            System.out.println(ordenes.verFrente().toString());
//            ordenes.desencolar();
//            System.out.println(ordenes.verFrente().toString());
//            
//        } catch(Exception e){
//             System.out.println("⚠️ Ocurrió un error al obtener el frente de la cola: " + e.getMessage());
//        }
//      
    }
}
