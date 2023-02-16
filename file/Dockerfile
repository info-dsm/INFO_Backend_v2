FROM eclipse-temurin:11-jre as builder
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]