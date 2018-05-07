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
        } catch ( IOException e ) {
            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
            System.err.println(e.getMessage());
        } catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
    }

}
