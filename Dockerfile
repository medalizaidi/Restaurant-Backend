# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/my-backend-app.jar /app/my-backend-app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "my-backend-app.jar"]

# Expose the port the app runs on
EXPOSE 8080
