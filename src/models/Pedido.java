package models;

import arquivo.PedidosDAO;
import arquivo.PedidosDAOImpl;

import java.io.IOException;

public class Pedido {

    public static final int CONSULTA=1, RESERVA_NAO_COMPRA=2, RESERVA_COMPRA=3;
    private int tipoPedido, linha, coluna, tempoParaConcluir;

    public Pedido(int tipoPedido, int linha, int coluna, int tempoParaConcluir) {

    }

    public int getTipoPedido() {
        return this.tipoPedido;
    }

    public int getLinha() {
        return this.linha;
    }

    public int getColuna() {
        return this.coluna;
    }

    public int getTempoParaConcluir() {
        return this.tempoParaConcluir;
    }

    public static Pedido[] getInstance() throws IOException {
        PedidosDAO dao = new PedidosDAOImpl();
        return dao.lerPedidos();
    }

}
