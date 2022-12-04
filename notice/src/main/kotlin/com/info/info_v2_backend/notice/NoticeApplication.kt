package com.info.info_v2_backend.notice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.kafka.annotation.EnableKafka

@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableKafka
@SpringBootApplication
class NoticeApplication
fun main(args: Array<String>) {
    runApplication<NoticeApplication>(*args)
}