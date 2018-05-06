package arquivo;

import models.SalaCinema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalaCinemaDAOImpl implements SalaCinemaDAO {

    /* Nome do arquivo onde se encontram as informações dos assentos da sala de cinema */
    private String nomeArquivo = "sala-cinema.txt";

    /**
     * Lê um arquivo contendo os assentos de uma sala de cinema e com isso gera um objeto do tipo SalaCinema
     * @return
     * @throws IOException
     */
    @Override
    public SalaCinema lerConfiguracoesDaSala() throws IOException {
        /* Inicializa leitores de arquivo */
        FileReader arq = new FileReader(nomeArquivo);
        BufferedReader lerArq = new BufferedReader(arq);

        /* Lê primeira linha para obter quantidade de filas e cadeiras */
        String linha = lerArq.readLine();
        String[] gambi1 = linha.split(" ");
        int numFilas = Integer.parseInt(gambi1[0]);
        int numCadeirasPorFila = Integer.parseInt(gambi1[1]);

        /* Iterando pelas linhas do arquivo e adiciona assentos na matriz */
        int filaAtual = 0;
        int[][] assentos = new int[numFilas][numCadeirasPorFila];
        while ((linha = lerArq.readLine()) != null) {
            String[] gambi = linha.split("");
            for (int c = 0; c < numCadeirasPorFila; c++)
                assentos[filaAtual][c] = Integer.parseInt(gambi[c]);
            filaAtual++;
        }

        /* Instancia objeto do tipo SalaCinema */
        SalaCinema salaCinema = new SalaCinema(assentos);

        /* Fecha o manipulador de arquivos */
        arq.close();

        /* Retorna o vetor de pedidos */
        return salaCinema;
    }

}
