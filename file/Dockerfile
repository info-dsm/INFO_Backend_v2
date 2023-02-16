FROM eclipse-temurin:17-jdk-ubi9-minimal
ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /

ENTRYPOINT ["java","-jar","/*.jar"]
