FROM openjdk:11-jdk-slim
VOLUME /phonebook
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]