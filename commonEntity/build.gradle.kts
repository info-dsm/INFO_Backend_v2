val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = false
val jar: Jar by tasks
jar.enabled = true

dependencies {
    //JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

}