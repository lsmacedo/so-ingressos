import excecoes.PoltronaInexistenteException;
import models.Pedido;
import models.SalaCinema;

import java.io.IOException;

public class Cliente extends Thread {

    private Pedido pedido;

    @Override
    public void run() {
        super.run();

        while ((pedido = Fila.getNext()) != null) {

            if (pedido.getTipoPedido() == Pedido.RESERVA_NAO_COMPRA || pedido.getTipoPedido() == Pedido.RESERVA_COMPRA)
                tentarReservar();
            if (pedido.getTipoPedido() == Pedido.CONSULTA)
                consultar();

        }

    }

    private void consultar(){
        if (assentoDisponivel())
            System.out.println("Poltrona disponível");
        else System.out.println("Poltrona já reservada");
    }

    private void tentarReservar(){

        if (assentoDisponivel())
            reservarAssento();
        else System.out.println("NEGADO FDP");

    }

    private synchronized void reservarAssento(){
        try {

            Fila.getSala().reservar(pedido);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean assentoDisponivel(){
        boolean assentoDisponivel = false;
        try {

            SalaCinema sala = SalaCinema.getInstance();
            int assento = sala.getAssento(pedido.getLinha(), pedido.getColuna());
            if (assento == 1) assentoDisponivel = true;
            else if (assento == 0){
                throw new PoltronaInexistenteException(pedido);

            }

        } catch (IOException e) {
            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return assentoDisponivel;
    }

}




