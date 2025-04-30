# Usando uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o arquivo JAR gerado para o contêiner
COPY target/App-0.0.1-SNAPSHOT.jar app.jar


# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
