FROM maven:3.8.6-alpine AS builder

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/picpaysimples-0.0.1-SNAPSHOT.jar ./


VOLUME /app


EXPOSE 8080

CMD ["java", "-jar", "picpaysimples-0.0.1-SNAPSHOT.jar"]