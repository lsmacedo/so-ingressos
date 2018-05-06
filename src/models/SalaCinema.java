package models;

import arquivo.PedidosDAO;
import arquivo.PedidosDAOImpl;
import arquivo.SalaCinemaDAO;
import arquivo.SalaCinemaDAOImpl;
import excecoes.PoltronaOcupadaException;
import excecoes.ReservaException;

import java.io.IOException;

public class SalaCinema {

    private int[][] assentos;

    public SalaCinema(int[][] assentos) {
        this.assentos = assentos;
    }

    public int getAssento(int fila, int cadeira) {
        return assentos[fila][cadeira];
    }

    public void reservar(Pedido pedido) throws ReservaException {
        throw new PoltronaOcupadaException();
    }

    /**
     * Gera um objeto do tipo SalaCinema com os dados presentes em arquivo
     * @return
     */
    public static SalaCinema getInstance() throws IOException {
        SalaCinemaDAO dao = new SalaCinemaDAOImpl();
        return dao.lerConfiguracoesDaSala();
    }

}
