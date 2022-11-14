package com.info.info_v2_backend.company

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@ConfigurationPropertiesScan
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
class CompanyApplication
fun main(args: Array<String>) {
    runApplication<CompanyApplication>(*args)
}