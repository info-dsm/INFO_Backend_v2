val jar: Jar by tasks
jar.enabled = false

dependencies {
    //Annotation Processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    //Kafka
    implementation("org.springframework.kafka:spring-kafka")
    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //MySQL driver
    implementation("mysql:mysql-connector-java:8.0.31")
    //Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
    //OpenFiegn
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //Validation
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.4")
    //Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    //Mailing
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.5")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.5")
    //log4j2
    implementation("org.apache.logging.log4j:log4j-api:2.16.0")
    implementation("org.apache.logging.log4j:log4j-core:2.16.0")

}