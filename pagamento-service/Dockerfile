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
COPY --from=builder /app/target/pagamento-service.jar app.jar

# Expor a porta (ajuste de acordo com a sua aplicação)
EXPOSE 8081

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
