FROM adoptopenjdk:8-jre-hotspot

# Add your application files to the image (e.g., JAR file)
COPY spring-log-elk-1.0.0.jar /app/spring-log-elk-1.0.0.jar

# Set the working directory
WORKDIR /app

# Run your Java application
CMD ["java", "-jar", "spring-log-elk-1.0.0.jar"]

EXPOSE 8080


