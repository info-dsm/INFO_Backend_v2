
dependencies {
    implementation(project(":common"))
    implementation(project(":commonEntity"))
    //MySQL driver
    implementation("mysql:mysql-connector-java:8.0.31")
    //Kafka
    implementation("org.springframework.kafka:spring-kafka")
    //Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
    //OpenFiegn
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    implementation("io.github.openfeign.form:feign-form:3.8.0")
    implementation("io.github.openfeign.form:feign-form-spring:3.8.0")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //Validation
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.4")
    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.5")
    //Annotation Processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

}