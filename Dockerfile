FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /appli
COPY . .
RUN ./gradlew build

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /appli/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
