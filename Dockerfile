FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} workouts-1.jar
ENTRYPOINT ["java","-jar","/workouts-1.jar"]