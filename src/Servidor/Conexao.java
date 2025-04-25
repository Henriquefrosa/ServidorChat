package Servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Conexao extends Thread {
    private Socket socket;

    public Conexao(final Socket socket) {
        this.socket = socket;
    }

    public void run() {
        Scanner saida = null;
        try {
            saida = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (saida.hasNextLine()) {
            System.out.println(saida.nextLine());
        }
    }
}