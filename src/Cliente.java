import excecoes.PoltronaInexistenteException;
import excecoes.PoltronaOcupadaException;
import models.Pedido;
import models.SalaCinema;

import java.io.IOException;

public class Cliente extends Thread {

    private Pedido pedido;
    boolean conseguiuReservar;

    @Override
    public void run() {
        super.run();

        while ((pedido = Fila.getNext()) != null) {

            boolean ehUltimoPedido = Fila.isEmpty();

            if (pedido.getTipoPedido() == Pedido.RESERVA_NAO_COMPRA || pedido.getTipoPedido() == Pedido.RESERVA_COMPRA) {
                tentarReservar();
            }
            if (pedido.getTipoPedido() == Pedido.CONSULTA)
                consultar();

            try {
                this.sleep(pedido.getTempoParaConcluir());

                if (pedido.getTipoPedido() == pedido.RESERVA_NAO_COMPRA){
                    retirarReserva(conseguiuReservar);
                }

                if (ehUltimoPedido) {
                    this.sleep(1000);
                    System.out.println("\n");
                    Fila.getSala().imprimirSala();
                }
            } catch (InterruptedException e){
                System.err.println("Erro ao colocar thread para dormir: " + e.getMessage());
            }

        }

    }

    private void consultar(){
        try {
            if (!assentoDisponivel())
                throw new PoltronaOcupadaException(pedido);
        }catch (PoltronaOcupadaException e){
            System.err.println(e.getMessage());
        }
    }

    private void tentarReservar(){

        if (assentoDisponivel()) {
            reservarAssento();
            conseguiuReservar = true;
        }
        else {
            conseguiuReservar = false;
            System.err.println("Assento não disponível");
        }

    }

    private synchronized void reservarAssento(){
        try {

            Fila.getSala().reservar(pedido);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private synchronized void retirarReserva(boolean tentativaReservaFalhou){
        try {

            Fila.getSala().retirarReserva(pedido, tentativaReservaFalhou);

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




