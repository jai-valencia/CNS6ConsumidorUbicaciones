FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apk add --no-cache maven && \
    mvn clean package -DskipTests && \
    apk del maven

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Crear directorio para el wallet de Oracle
RUN mkdir -p /app/wallet

COPY --from=build /app/target/consumidor-ubicaciones-1.0.0.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
