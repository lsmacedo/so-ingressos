import excecoes.ReservaException;
import models.Pedido;
import models.SalaCinema;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            SalaCinema sala = SalaCinema.getInstance();
            Pedido[] pedidos = Pedido.getInstance();

            for (Pedido pedido : pedidos) {
                if (pedido.getTipoPedido() == Pedido.RESERVA_COMPRA)
                    sala.reservar(pedido);
            }
        } catch ( ReservaException e ) {
            System.err.println(e.getMessage());
        } catch ( IOException e) {
            System.err.println("Erro inesperado ao manipular arquivos:");
            System.err.println(e.getMessage());
        }
    }

}
