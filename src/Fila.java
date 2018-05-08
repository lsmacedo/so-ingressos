import models.Pedido;

import java.util.LinkedList;
import java.util.Queue;

public class Fila {

    private static Queue<Pedido> pedidos;

    public Fila() {
        pedidos = new LinkedList<>();
    }

    public static synchronized Pedido getNext(){
        return pedidos.poll();
    }

}
