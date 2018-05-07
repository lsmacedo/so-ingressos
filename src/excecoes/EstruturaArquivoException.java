package excecoes;

public class EstruturaArquivoException extends Exception {

    public EstruturaArquivoException(String nomeArquivo) {
        super("A estrutura do arquivo \"" + nomeArquivo + "\" se encontra incorreta");
    }

}
