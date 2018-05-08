import models.Pedido;
import models.SalaCinema;

import java.io.IOException;

public class Cliente extends Thread {

    private Pedido pedido;

    public Cliente(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void run() {
        super.run();

        if (pedido.getTipoPedido() == Pedido.RESERVA_NAO_COMPRA || pedido.getTipoPedido() == Pedido.RESERVA_COMPRA)
            tentarReservar();

        new Cliente(Main.pedidos[Main.i++]).start();
    }

    public void tentarReservar(){

        if (assentoDisponivel())
            reservarAssento();

    }

    public synchronized void reservarAssento(){
        try {

            SalaCinema sala = SalaCinema.getInstance();
            sala.reservar(pedido);

        } catch (IOException e) {
            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean assentoDisponivel(){
        try {

            SalaCinema sala = SalaCinema.getInstance();
            int assento = sala.getAssento(pedido.getLinha(), pedido.getColuna());
            if (assento == 1) return true;

        } catch (IOException e) {
            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}




