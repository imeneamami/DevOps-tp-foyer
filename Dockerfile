FROM openjdk:17-jdk-alpine
EXPOSE 8089

# Copy the JAR file into the image
COPY tp-foyer-5.0.0.jar /app/tp-foyer-5.0.0.jar

# Set the working directory (optional but recommended)
WORKDIR /app

# Define the entry point
ENTRYPOINT ["java", "-jar", "tp-foyer-5.0.0.jar"]
