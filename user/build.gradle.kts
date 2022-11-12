dependencies {

    //Common
    implementation(project(":commonEntity"))
    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
    //MySQL driver
    implementation("mysql:mysql-connector-java:8.0.31")
    //OpenFiegn
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-config:5.7.3")
    //Jwts
    implementation("io.jsonwebtoken:jjwt:0.9.1")
}