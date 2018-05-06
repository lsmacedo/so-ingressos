package excecoes;

public abstract class ReservaException extends Exception {

    public ReservaException(String mensagemExcecao) {
        super("Uma exceção foi encontrada ao efetuar a reserva: " + mensagemExcecao);
    }

}
