FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE
ADD /target/spring-todo.jar spring-todo.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-todo-0.0.1-SNAPSHOT.jar"]