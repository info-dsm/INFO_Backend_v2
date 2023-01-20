package com.info.info_v2_backend.statistics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@ConfigurationPropertiesScan
@SpringBootApplication
class StatisticsApplication
fun main(args: Array<String>) {
    runApplication<StatisticsApplication>(*args)
}