package arquivo;

import excecoes.EstruturaArquivoException;
import models.Pedido;

import java.io.IOException;
import java.util.Queue;

public interface PedidosDAO {

    /**
     * Lê um arquivo contendo os pedidos e devolve um vetor de Pedidos com base em seu conteúdo
     * @return
     * @throws IOException
     */
    public Queue<Pedido> lerPedidos() throws IOException, EstruturaArquivoException;

}
