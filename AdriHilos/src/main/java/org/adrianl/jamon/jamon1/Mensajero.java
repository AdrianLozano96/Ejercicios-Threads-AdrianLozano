package org.adrianl.jamon.jamon1;

import java.util.ArrayList;
import java.util.List;

//CONSUMIDOR
public class Mensajero extends Thread{

    //Por otro lado, tenemos el mensajero que sacará tandas de tres en tres jamon3
    //cada tres segundos si es posible y hay existencias en el secadero,
    //agrupándolos en un lote para llevarlos a la tienda.
    // Una vez sacado imprime el lote con su identificador.
    //Opcional: hacer otro monitor (tienda al que llevar las tandas de jamon3 para que los consuma un cliente.


    private Secadero secadero;
    private Tienda tienda;
    private int cantidad;   //Cantidad a consumir
    private int lote;   //Cantidad de cada lotes
    private List<Jamon>jamones = new ArrayList<>();

    public Mensajero(String nombre, Secadero secadero, Tienda tienda, int cantidad, int lote) {
        super(nombre);
        this.secadero = secadero;
        this.tienda = tienda;
        this.cantidad = cantidad;
        this.lote = lote;
    }

    @Override
    public void run() {
        for(int i=1; i<cantidad+1; i++){
            for(int j=0; j<lote; j++){
                Jamon jamon = secadero.consumir();
                jamon.setLote(i);
                jamones.add(jamon);
            }
            tienda.enviar(jamones);
            System.out.println(this.getName()+" ha empaquetado el lote "+i+" de "+ cantidad);
            jamones.forEach(System.out::println);
            jamones.clear();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El mensajero ha terminado su trabajo por hoy");
    }
}
