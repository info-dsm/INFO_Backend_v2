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
    //OpenFiegn
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //AWS
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
    //Kafka
    implementation("org.springframework.kafka:spring-kafka")
    //Multipartfile
    implementation("commons-fileupload:commons-fileupload:1.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.5")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.5")

}