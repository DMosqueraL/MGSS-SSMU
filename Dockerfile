# Stage 1: Build the application with Maven
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Create a smaller image with only the JAR file
FROM openjdk:17-alpine

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE='pdn'

COPY --from=builder /app/target/apiserviciotransporte-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]