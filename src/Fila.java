import excecoes.EstruturaArquivoException;
import models.Pedido;
import models.SalaCinema;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Fila {

    private static Queue<Pedido> pedidos;
    private static SalaCinema sala;

    public static void iniciar() throws IOException, EstruturaArquivoException {
        pedidos = Pedido.getInstance();
        sala = SalaCinema.getInstance();
    }

    public static synchronized Pedido getNext(){
        return pedidos.poll();
    }

    public static synchronized SalaCinema getSala() {
        return sala;
    }

}
