package models;

import excecoes.EstruturaArquivoException;
import arquivo.PedidosDAO;
import arquivo.PedidosDAOImpl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Pedido {

    /*
    Constantes relacionadas ao tipo de pedido
    CONSULTA = Somente consulta
    RESERVA_NAO_COMPRA = Consulta, reserva e não compra
    RESERVA_COMPRA = Consulta, reserva e compra
    */
    public static final int CONSULTA=1, RESERVA_NAO_COMPRA=2, RESERVA_COMPRA=3;
    /* Atributos do pedido */
    private int tipoPedido, linha, coluna, tempoParaConcluir;

    /**
     * Construtor padrão
     * @param tipoPedido
     * @param linha
     * @param coluna
     * @param tempoParaConcluir
     */
    public Pedido(int tipoPedido, int linha, int coluna, int tempoParaConcluir) {
        this.tipoPedido = tipoPedido;
        this.linha = linha;
        this.coluna = coluna;
        this.tempoParaConcluir = tempoParaConcluir;
    }

    /* Padrão Singleton. Gera vetor de pedidos com base na leitura do arquivo */
    public static Queue<Pedido> getInstance() throws IOException, EstruturaArquivoException{
        PedidosDAO dao = new PedidosDAOImpl();
        return dao.lerPedidos();
    }

    /* Getters */
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

    @Override
    public String toString() {
        return "Linha: " + linha + " | Coluna: " + coluna;
    }

}
