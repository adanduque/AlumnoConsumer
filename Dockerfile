#### CREACION DEL JAR ####
FROM maven:3-jdk-11-slim AS builder

WORKDIR /app
COPY ./pom.xml .
RUN mvn -e -B dependency:go-offline
COPY ./src ./src
RUN mvn -e -B -Dmaven.test.skip=true package

#### FASE FINAL DE LA IMAGEN ####
FROM openjdk:11-jdk-slim

WORKDIR /workspace

ENV host="localhost"
ENV port="27017"
ENV database="alumnos"
ENV port-kafka="9092"
ENV username=""
ENV password=""

##COPY out/artifacts/AlumnoNotas_jar/AlumnoNotas.jar app.jar


COPY --from=builder /app/target/Alumno*.jar app.jar

EXPOSE 8080
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar