FROM maven:latest as builder
RUN mvn clean install
COPY pom.xml .
COPY ./src ./src
COPY ./pom.xml ./pom.xml


FROM openjdk:8-jdk-alpine


COPY target/*.jar spring-app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","spring-app.jar"]
