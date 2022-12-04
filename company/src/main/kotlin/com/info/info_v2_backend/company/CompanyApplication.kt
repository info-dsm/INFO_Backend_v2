package com.info.info_v2_backend.company

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableAsync

@ConfigurationPropertiesScan
@EnableFeignClients
@EnableCircuitBreaker
@EnableMongoRepositories
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableAsync
@EnableKafka
@SpringBootApplication
class CompanyApplication
fun main(args: Array<String>) {
    runApplication<CompanyApplication>(*args)
}