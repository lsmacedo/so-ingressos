package arquivo;

import excecoes.EstruturaArquivoException;
import models.Pedido;
import models.TipoPedidoLOG;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;

public class PedidosDAOImpl implements PedidosDAO {

    /* Nome do arquivo onde se encontram os pedidos */
    private String nomeArquivo = "pedidos.txt";

    /**
     * Lê um arquivo contendo os pedidos e devolve um vetor de Pedidos com base em seu conteúdo
     * @return
     * @throws IOException
     */
    public Queue<Pedido> lerPedidos() throws IOException, EstruturaArquivoException {
        try {

            /* Inicializa leitores de arquivo */
            FileReader arq = new FileReader(nomeArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            /* Lê primeira linha para obter quantidade de pedidos */
            String linha = lerArq.readLine();
            int numPedidos = Integer.parseInt(linha);

            /* Cria estrutura de dados para armazenar os pedidos */
            Queue<Pedido> pedidos = new LinkedList<>();

            /* Iterando pelas linhas do arquivo e adiciona pedidos na fila */
            while ((linha = lerArq.readLine()) != null) {
                String[] gambi = linha.split(" ");
                int tp = Integer.parseInt(gambi[0]);
                int l = Integer.parseInt(gambi[1]);
                int c = Integer.parseInt(gambi[2]);
                int tc = Integer.parseInt(gambi[3]);
                Pedido p = new Pedido(tp, l, c, tc);
                pedidos.add(p);
            }

            /* Fecha o manipulador de arquivos */
            arq.close();

            /* Retorna o vetor de pedidos */
            return pedidos;
        }
        catch (NullPointerException | NumberFormatException e) {
            throw new EstruturaArquivoException(nomeArquivo);
        }
    }

    @Override
    public void escreverPedidos(List<Pedido> pedidos, TipoPedidoLOG tipoPedido) throws IOException {

        FileWriter fileWriter = new FileWriter(tipoPedido.getNomeArquivo());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Pedido p : pedidos){
            printWriter.println(tipoPedido.getNomeLOG() + ", PEDIDO " + p.getNumeroPedido() + ", POLTRONA " + p.getLinha() + " " + p.getColuna());
        }

        printWriter.close();
        fileWriter.close();

    }
}
