package models;

public enum TipoPedidoLOG {

        PEDIDO_VENDIDO("pedido-vendido.txt", "VENDIDO"),
        PEDIDO_NEGADO("pedido-negado.txt", "NEGADO");

        public String nomeArquivo;
        public String nomeLOG;

    TipoPedidoLOG(String nomeArquivo, String nomeLOG) {
        this.nomeArquivo = nomeArquivo;
        this.nomeLOG = nomeLOG;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getNomeLOG() {
        return nomeLOG;
    }
}
