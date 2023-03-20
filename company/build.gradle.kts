val jar: Jar by tasks
jar.enabled = false

dependencies {
    implementation(project(":common"))
    implementation(project(":user"))
    //MySQL driver
    implementation("mysql:mysql-connector-java:8.0.31")
    //Kafka
    implementation("org.springframework.kafka:spring-kafka")
    //Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
    //OpenFiegn
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //Validation
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.4")
    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.5")
    //Annotation Processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    //Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-config:5.7.3")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.5")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.5")
    //Mongo
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}