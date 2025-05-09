FROM openjdk:17

WORKDIR /app

COPY src/ ./src

# Compila os arquivos Java
RUN mkdir out && javac -d out src/Servidor/Main.java src/Servidor/Conexao.java

EXPOSE 12345

CMD ["bash", "./start.sh"]
