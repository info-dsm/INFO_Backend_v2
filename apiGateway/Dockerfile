FROM openjdk:11-jdk as builder
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} /app.jar

FROM eclipse-temurin:17-jdk-ubi9-minimal



COPY --from=builder /app.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
