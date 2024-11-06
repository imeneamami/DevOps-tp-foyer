

# Start with a base image that includes OpenJDK 17
FROM openjdk:17-jdk-alpine

# Expose the port that your Spring Boot application will run on
EXPOSE 8089

# Add the JAR file from the target directory to the container
ADD target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar

# Set the entry point to run the application using the JAR file
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]