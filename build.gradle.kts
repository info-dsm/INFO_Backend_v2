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


    dependencies {
        compileOnly("org.springframework.boot:spring-boot-autoconfigure")
        compileOnly("org.springframework.boot:spring-boot-autoconfigure-processor")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
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
    }

}
val bootJar: BootJar by tasks
bootJar.enabled = false

project(":common") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    }
}

project(":user") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":board") {
    dependencies {
        implementation(project(":common"))
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
        implementation(project(":common"))
    }
}

project(":noticeQuery") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":company") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":companyQuery") {
    dependencies {
        implementation(project(":common"))
    }
}

project(":applies") {
    dependencies {
        compileOnly(project(":common"))
    }
}

project(":aws") {
    dependencies {
        compileOnly(project(":common"))
    }
}

