FROM openjdk:11.0.5-jdk-slim
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/produto-api.jar
COPY ${JAR_FILE} produto-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/produto-api.jar"]