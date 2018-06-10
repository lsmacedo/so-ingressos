package arquivo;

import excecoes.EstruturaArquivoException;
import models.Pedido;
import models.TipoPedidoLOG;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

public interface PedidosDAO {

    /**
     * Lê um arquivo contendo os pedidos e devolve um vetor de Pedidos com base em seu conteúdo
     * @return
     * @throws IOException
     */
    Queue<Pedido> lerPedidos() throws IOException, EstruturaArquivoException;

    void escreverPedidos(List<Pedido> pedidos, TipoPedidoLOG tipoPedidoLOG) throws IOException;

}
