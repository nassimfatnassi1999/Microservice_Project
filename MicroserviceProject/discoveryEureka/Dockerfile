# Use an OpenJDK Runtime as a parent image
FROM openjdk:21-slim
# Add Maintainer Info
LABEL maintainer="fatnassihnifi.nassim@esprit.tn"
# Use the COPY instruction to copy your Spring Boot application's JAR file from your project directory
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# Make port 8080 available to the world outside this container
EXPOSE 8099
# Run app.jar when the container launches
CMD ["java", "-jar", "app.jar"]