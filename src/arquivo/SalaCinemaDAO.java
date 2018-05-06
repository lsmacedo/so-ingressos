package arquivo;

import models.SalaCinema;

import java.io.IOException;

public interface SalaCinemaDAO {

    /**
     * Lê um arquivo contendo os assentos de uma sala de cinema e com isso gera um objeto do tipo SalaCinema
     * @return
     * @throws IOException
     */
    public SalaCinema lerConfiguracoesDaSala() throws IOException;

}
