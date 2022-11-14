package com.info.info_v2_backend.file

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.kafka.annotation.EnableKafka

@ConfigurationPropertiesScan
@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
@EnableKafka
class FileApplication
fun main(args: Array<String>) {
    runApplication<FileApplication>(*args)
}
