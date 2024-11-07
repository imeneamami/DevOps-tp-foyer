FROM openjdk:17-jdk-alpine
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]
