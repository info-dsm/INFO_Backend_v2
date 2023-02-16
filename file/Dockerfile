FROM eclipse-temurin:11-jdk as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /tmp/

FROM eclipse-temurin:17-jdk-ubi9-minimal


COPY --from=builder /tmp/*.jar /tmp/

ENTRYPOINT ["java","-jar","/tmp/*.jar"]
