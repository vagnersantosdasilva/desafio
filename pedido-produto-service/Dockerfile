# Etapa 1: Build
FROM maven:3.8.6-jdk-17-alpine AS builder
WORKDIR /app

# Copiar os arquivos pom.xml e src para o diretório de trabalho
COPY pom.xml ./
COPY src ./src

# Executar o Maven para construir o projeto
RUN mvn package -DskipTests

# Etapa 2: Runtime
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copiar o JAR gerado da etapa de build
COPY --from=builder /app/target/pedido-produto-service.jar app.jar

# Expor a porta (ajuste de acordo com a sua aplicação)
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]

#EXPOSE 8080
#
#CMD ["java", "-jar", "picpaysimples-0.0.1-SNAPSHOT.jar"]
# Dockerfile para pedido-produto-service
# Dockerfile para pedido-produto-service
#FROM maven:3.8.6-jdk-17 AS build
#
## Define o diretório de trabalho
#WORKDIR /app
#
## Copia o arquivo pom.xml e os arquivos do projeto
#COPY pom.xml .
#COPY src ./src
#
## Resolve as dependências e constrói o projeto
#RUN mvn clean package
#
## Define a imagem base para a execução
#FROM openjdk:17-jdk-alpine
#
## Define o diretório de trabalho
#WORKDIR /app
#
## Copia o JAR gerado do estágio de construção
#COPY --from=build /app/target/pedido-produto-service.jar /app/pedido-produto-service.jar
#
## Define o comando para executar a aplicação
#CMD ["java", "-jar", "pedido-produto-service.jar"]