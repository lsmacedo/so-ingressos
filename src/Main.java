import models.Pedido;
import models.SalaCinema;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        try {
            Fila.iniciar();
            Fila.getSala().imprimirSala();
            System.out.println("\n");
            for (int i = 0; i < 10; i++) {
                Cliente c = new Cliente();
                c.start();
            }
        } catch ( IOException e ) {
            System.err.print("Uma exceção foi encontrada ao manipular arquivo: ");
            System.err.println(e.getMessage());
        } catch ( Exception e ) {
            System.err.println(e.getMessage());
        }

    }

}
