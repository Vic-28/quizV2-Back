FROM openjdk:latest
ARG WAR_FILE=target/*.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java", "-jar", "/app.war"]
EXPOSE 8080