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
            // Cria os streams de entrada e saída
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            Scanner entrada = new Scanner(socket.getInputStream());

            // Envia mensagem para o cliente
            saida.println("Bem-vindo! Qual é o seu nome de usuário?");

            // Verifica se o cliente realmente enviou uma linha
            if (!entrada.hasNextLine()) {
                System.out.println("Conexão recebida, mas sem entrada. Encerrando...");
                socket.close(); // Encerra a conexão
                return;
            }

            // Lê username
            String username = entrada.nextLine();

            // Obtém IP e data/hora
            String ip = socket.getInetAddress().getHostAddress();
            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Grava no arquivo
            String info = String.format("Usuário: %s | IP: %s | Data: %s%n", username, ip, dataHora);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("conexoes.txt", true))) {
                writer.write(info);
            }

            // Mostra no console
            System.out.println(info);

            // Continua lendo mensagens (opcional)
            while (entrada.hasNextLine()) {
                String mensagem = entrada.nextLine();
                System.out.println(username + ": " + mensagem);
            }

        } catch (IOException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }

}
