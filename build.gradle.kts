import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar


buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.7.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.41")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.7.20")
    }
}



plugins {
    kotlin("jvm") version("1.7.20")
    id("org.springframework.boot") version("2.7.5")
    kotlin("plugin.spring") version("1.6.21")
    kotlin("plugin.jpa") version("1.6.21")
}

allprojects {
    group = "com.info"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

}

subprojects {
    apply(plugin = "java")

    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("org.springframework.data.redis.core.RedisHash")
        annotation("org.springframework.data.mongodb.core.mapping.Document")
        annotation("javax.persistence.Embeddable")
    }

    noArg {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("org.springframework.data.redis.core.RedisHash")
        annotation("org.springframework.data.mongodb.core.mapping.Document")
        annotation("javax.persistence.Embeddable")
    }


    dependencies {
        compileOnly("org.springframework.boot:spring-boot-autoconfigure")
        compileOnly("org.springframework.boot:spring-boot-autoconfigure-processor")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("io.micrometer:micrometer-registry-prometheus")
    }



    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
        all {
            exclude(group="org.springframework.boot", module="spring-boot-starter-logging")
        }
    }

}
val bootJar: BootJar by tasks
bootJar.enabled = false
val jar: Jar by tasks
jar.enabled = false


project(":common") {
    dependencies {
    }
}

project(":user") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":auth") {
    dependencies {
        implementation(project(":common"))
        implementation(project(":user"))
    }
}

project(":employment") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":notice") {
    dependencies {
        implementation(project(":common"))
    }
}


project(":email") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":comment") {
    dependencies {

    }
}


project(":company") {
    dependencies {
        implementation(project(":common"))
        implementation(project(":user"))
    }
}


project(":applies") {
    dependencies {
    }
}

project(":file") {
    dependencies {
    }
}

project(":apiGateway") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":eureka") {
    dependencies {
    }
}

project(":statistics"){
    dependencies {
    }
}
