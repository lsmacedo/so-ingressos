package models;

import arquivo.*;
import excecoes.EstruturaArquivoException;
import excecoes.PoltronaOcupadaException;
import excecoes.ReservaException;

import java.io.IOException;

public class SalaCinema {

    /* Matriz com as posições dos assentos na sala. Primeira posição se refere às filas e segunda às cadeiras */
    private int[][] assentos;

    /**
     * Construtor padrão
     * @param assentos
     */
    public SalaCinema(int[][] assentos) {
        this.assentos = assentos;
    }

    /**
     * Padrão Singleton. Gera um objeto do tipo SalaCinema com os dados presentes em arquivo
     * @return
     */
    public static SalaCinema getInstance() throws IOException, EstruturaArquivoException {
        SalaCinemaDAO dao = new SalaCinemaDAOImpl();
        return dao.lerConfiguracoesDaSala();
    }

    /**
     * Reserva um assento com base em um pedido.
     * @param pedido
     * @throws ReservaException
     */
    public void reservar(Pedido pedido) throws ReservaException {
        /* Identificar pedido */
        int linha = pedido.getLinha(), coluna = pedido.getColuna();
        /* Caso o assento esteja ocupado, lançar exceção */
        if (assentos[linha][coluna] == 0)
            throw new PoltronaOcupadaException();
        else {
            assentos[linha][coluna] = 0;
            System.out.println("Assento reservado com sucesso! S2");
        }
    }

    /* Getters */
    public int getAssento(int fila, int cadeira) {
        return assentos[fila][cadeira];
    }

}
