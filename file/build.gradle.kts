val jar: Jar by tasks
jar.enabled = false

dependencies {
    implementation(project(":common"))
    //Annotation Processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //MySQL driver
    implementation("mysql:mysql-connector-java:8.0.31")
    //Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
    //OpenFeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //Kafka
    implementation("org.springframework.kafka:spring-kafka")
    //actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.5")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.5")
    //aws
    implementation(platform("software.amazon.awssdk:bom:2.17.230"))
    implementation("software.amazon.awssdk:s3")
}