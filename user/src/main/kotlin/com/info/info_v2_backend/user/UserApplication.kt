package com.info.info_v2_backend.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.kafka.annotation.EnableKafka

@EnableDiscoveryClient
@EnableKafka
@EnableFeignClients
@ConfigurationPropertiesScan
@EnableJpaAuditing
@SpringBootApplication
class UserApplication
fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
