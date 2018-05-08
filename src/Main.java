import models.Pedido;
import models.SalaCinema;

import java.io.IOException;
import java.util.List;

public class Main {

    public static Pedido[] pedidos;
    public static int i = 0;

    public static void main(String[] args) {
//        try {
//            SalaCinema sala = SalaCinema.getInstance();
//            Pedido[] pedidos = Pedido.getInstance();
//
//            for (Pedido pedido : pedidos) {
//                if (pedido.getTipoPedido() == Pedido.RESERVA_NAO_COMPRA || pedido.getTipoPedido() == Pedido.RESERVA_COMPRA)
//                    sala.reservar(pedido);
//            }
//        } catch ( IOException e ) {
//            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
//            System.err.println(e.getMessage());
//        } catch ( Exception e ) {
//            System.err.println(e.getMessage());
//        }

        try {
            pedidos = Pedido.getInstance();

            for (int j = 0; j < 10; j++) {
                Cliente c = new Cliente(pedidos[i++]);
                c.start();
            }

        } catch ( IOException e ) {
            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
            System.err.println(e.getMessage());
        } catch ( Exception e ) {
            System.err.println(e.getMessage());
        }


    }

}
