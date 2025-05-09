package Servidor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws IOException {
        var servidor = new ServerSocket(10000);
        Socket socket = new Socket();
        System.out.println("Servidor iniciado na 12345!");
        ArrayList<Conexao> clientes = new ArrayList<>();
        while (true) {
            socket = servidor.accept();
            System.out.println("Conexão estabelecida!");
            new Conexao(socket).start();
        }
    }
}
