FROM openjdk:11-jdk as builder
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} /app.jar

FROM openjdk:18-jdk-slim

COPY --from=builder /app.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
