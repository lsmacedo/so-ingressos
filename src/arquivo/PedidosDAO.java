package arquivo;

import models.Pedido;

import java.io.IOException;

public interface PedidosDAO {

    /**
     * Lê um arquivo contendo os pedidos e devolve um vetor de Pedidos com base em seu conteúdo
     * @return
     * @throws IOException
     */
    public Pedido[] lerPedidos() throws IOException;

}
