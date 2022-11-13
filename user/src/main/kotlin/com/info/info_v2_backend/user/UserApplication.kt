package com.info.info_v2_backend.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
@ConfigurationPropertiesScan
class UserApplication
fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}