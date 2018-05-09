package excecoes;

import models.Pedido;

public class PoltronaOcupadaException extends ReservaException {

    public PoltronaOcupadaException(Pedido p) {
        super("a poltrona [" + p.getLinha() + ", " + p.getColuna() + "] se encontra ocupada");
    }

}
