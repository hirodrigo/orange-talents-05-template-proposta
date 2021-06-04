## Builder Image
FROM maven:3.6.3-jdk-11 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package
ENV MYSQL_HOST=jdbc:mysql://mysql:3306/proposta?useTimezone=true&serverTimezone=UTC

## Runner Image
FROM openjdk:11
COPY --from=builder /usr/src/app/target/proposta-0.0.1-SNAPSHOT.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]