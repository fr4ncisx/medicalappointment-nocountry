FROM eclipse-temurin:21-jdk-alpine
ARG JAR_FILE=target/healthapp-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} healthapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "healthapp.jar"]
