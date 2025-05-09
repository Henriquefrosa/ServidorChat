FROM openjdk:17

WORKDIR /app

# Copia o código-fonte
COPY src/ ./src

# Compila os arquivos .java
RUN mkdir out && javac -d out src/Servidor/Main.java src/Servidor/Conexao.java

# Copia o script de inicialização
COPY start.sh ./start.sh

# Dá permissão de execução
RUN chmod +x start.sh

EXPOSE 12345

CMD ["bash", "./start.sh"]
