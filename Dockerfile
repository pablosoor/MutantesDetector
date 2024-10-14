# Base image for metadata
FROM ubuntu:latest
LABEL authors="Pablo_Osorio"

# Build stage
FROM maven:3.8.5-openjdk-17 AS build

# Copiar el archivo pom.xml y el directorio de código fuente
COPY pom.xml ./
COPY src ./src

# Construir el proyecto
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
