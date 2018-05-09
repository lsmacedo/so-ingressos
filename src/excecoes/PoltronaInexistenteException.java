package excecoes;

import models.Pedido;

public class PoltronaInexistenteException extends ReservaException {

    public PoltronaInexistenteException(Pedido p) {
        super("a poltrona [" + p.getLinha() + ", " + p.getColuna() + "] não existe");
    }

}
