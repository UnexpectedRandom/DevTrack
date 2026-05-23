FROM maven:3.9.6-eclipse-temurin-21-alpine AS build_stage
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build_stage /app/target/*.jar devtrack-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "devtrack-app.jar"]