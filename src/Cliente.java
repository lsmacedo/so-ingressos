import arquivo.PedidosDAO;
import arquivo.PedidosDAOImpl;
import excecoes.PoltronaInexistenteException;
import excecoes.PoltronaOcupadaException;
import excecoes.ReservaException;
import models.Pedido;
import models.SalaCinema;
import models.TipoPedidoLOG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Thread {

    private List<Pedido> vendidos = new ArrayList<>();
    private List<Pedido> negados = new ArrayList<>();

    private Pedido pedido;
    private boolean conseguiuReservar;

    @Override
    public void run(){
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
                    escreverLOG();
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
        }

    }

    private synchronized void reservarAssento(){
        try {

            Fila.getSala().reservar(pedido);
            vendidos.add(pedido);
            conseguiuReservar = true;

        } catch (ReservaException e) {
            negados.add(pedido);
            conseguiuReservar = false;
            System.err.println(e.getMessage());
        }
    }

    private synchronized void retirarReserva(boolean conseguiuReservar){

        if (Fila.getSala().retirarReserva(pedido, conseguiuReservar))
            negados.add(pedido);

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

    private void escreverLOG(){
        try {
            PedidosDAO dao = new PedidosDAOImpl();
            dao.escreverPedidos(vendidos, TipoPedidoLOG.PEDIDO_VENDIDO);
            dao.escreverPedidos(negados, TipoPedidoLOG.PEDIDO_NEGADO);
        }catch (IOException e){
            System.out.println("Erro ao escrever o LOG");
        }
    }


}




