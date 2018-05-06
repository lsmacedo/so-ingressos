package excecoes;

public class PoltronaOcupadaException extends ReservaException {

    public PoltronaOcupadaException() {
        super("esta poltrona se encontra ocupada");
    }

}
