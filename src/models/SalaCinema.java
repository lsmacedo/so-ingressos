package models;

import arquivo.PedidosDAO;
import arquivo.PedidosDAOImpl;
import arquivo.SalaCinemaDAO;
import arquivo.SalaCinemaDAOImpl;
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
    public static SalaCinema getInstance() throws IOException {
        SalaCinemaDAO dao = new SalaCinemaDAOImpl();
        return dao.lerConfiguracoesDaSala();
    }

    /**
     * Reserva um assento com base em um pedido.
     * @param pedido
     * @throws ReservaException
     */
    public void reservar(Pedido pedido) throws ReservaException {
        throw new PoltronaOcupadaException();
    }

    /* Getters */
    public int getAssento(int fila, int cadeira) {
        return assentos[fila][cadeira];
    }

}
