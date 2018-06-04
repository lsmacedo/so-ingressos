package models;

import arquivo.*;
import excecoes.EstruturaArquivoException;
import excecoes.PoltronaInexistenteException;
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
            throw new PoltronaInexistenteException(pedido);
        else if (assentos[linha][coluna] == 2)
            throw new PoltronaOcupadaException(pedido);
        else {
            assentos[linha][coluna] = 2;
            System.out.println("Assento reservado com sucesso! S2 - " + pedido);
        }
    }

    public void retirarReserva(Pedido pedido, boolean conseguiuReservar){
        /* Identificar pedido */
        int linha = pedido.getLinha(), coluna = pedido.getColuna();

        if (conseguiuReservar) {
            assentos[linha][coluna] = 1;
            System.out.println("Reserva retirada com sucesso. Pense bem antes de tirar a oportunidade de alguem que poderia estar usufruindo deste ingresso :( - " + pedido);
        }
    }

    public void imprimirSala(){
        for (int i=0; i<assentos.length; i++){
            System.out.print(toFixed(i, 2) + "  |");
            for (int j=0; j<assentos[i].length; j++){
                System.out.print(toFixed(assentos[i][j], 2) + " ");
            }
            System.out.println();
        }
        System.out.print("----");
        for (int i = 0; i < assentos[0].length*3; i++) {
            System.out.print("-");
        }
        System.out.println("");
        System.out.print("     ");
        for (int i = 0; i < assentos[0].length; i++) {
            System.out.print(toFixed(i, 2) + " ");
        }
    }

    public String toFixed(int num, int tam) {
        String r = "";
        String s = String.valueOf(num);
        for (int i = 0; i < tam - s.length(); i++)
            r += " ";
        r += s;
        return r;
    }

    /* Getters */
    public int getAssento(int fila, int cadeira) {
        return assentos[fila][cadeira];
    }


}
