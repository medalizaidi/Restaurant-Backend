FROM openjdk:21-jdk
COPY target/spring-boot-app.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-app.jar"]