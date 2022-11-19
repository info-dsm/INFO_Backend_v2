val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = false
val jar: Jar by tasks
jar.enabled = true

dependencies {
    implementation("commons-fileupload:commons-fileupload:1.4")

}