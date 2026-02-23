FROM openjdk:17-jdk-slim
# Maven puts the jar in the target folder
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]