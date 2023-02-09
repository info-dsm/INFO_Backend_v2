package com.info.info_v2_backend.applies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync


//@SpringBootApplication Annotation should be lowest
@EnableJpaAuditing
@EnableFeignClients
@EnableDiscoveryClient
@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication
class AppliesApplication
fun main(args: Array<String>) {
    runApplication<AppliesApplication>(*args)
}