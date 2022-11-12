package com.info.info_v2_backend.email

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@ConfigurationPropertiesScan
@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
class EmailApplication
fun main(args: Array<String>) {
    runApplication<EmailApplication>(*args)
}
