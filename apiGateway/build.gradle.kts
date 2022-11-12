
dependencies {
    //Gateway
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:3.1.4")
    //MacOs-x86_64
    implementation(group = "io.netty", name = "netty-resolver-dns-native-macos", version = "4.1.85.Final", classifier = "osx-aarch_64")
    // Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.4")
}