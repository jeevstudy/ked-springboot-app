# Use Eclipse Temurin instead of the deprecated openjdk image
FROM eclipse-temurin:17-jre-alpine
# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
# Copy the jar we just built in the Maven stage
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]