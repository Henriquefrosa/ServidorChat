package Servidor;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Conexao extends Thread {
    private Socket socket;

    public Conexao(final Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Envia mensagem para o cliente perguntando o username
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            saida.println("Bem-vindo! Qual é o seu nome de usuário?");

            // Lê resposta do cliente
            Scanner entrada = new Scanner(socket.getInputStream());
            String username = entrada.nextLine();

            // Obtém IP e data/hora da conexão
            String ip = socket.getInetAddress().getHostAddress();
            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Grava as informações no arquivo
            String info = String.format("Usuário: %s | IP: %s | Data: %s%n", username, ip, dataHora);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("conexoes.txt", true))) {
                writer.write(info);
            }

            // Opcional: exibir no servidor também
            System.out.println(info);

            // Continua recebendo mensagens do cliente (opcional)
            while (entrada.hasNextLine()) {
                System.out.println(username + ": " + entrada.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }
}
