dependencies {

    //Common
    implementation(project(":common"))
    // Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
    //OpenFiegn
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    //Hystrix
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:2.2.10.RELEASE")
    //Validation
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.4")
    //batch
    implementation("org.springframework.boot:spring-boot-starter-batch")
    //Mysql
    runtimeOnly("mysql:mysql-connector-java")
    //data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}