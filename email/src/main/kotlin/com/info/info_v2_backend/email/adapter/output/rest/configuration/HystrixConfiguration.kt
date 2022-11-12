package com.info.info_v2_backend.email.adapter.output.rest.configuration

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixCommandProperties
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HystrixConfiguration {

    @Bean
    fun defaultConfig(): Customizer<HystrixCircuitBreakerFactory>? {
        return Customizer { factory: HystrixCircuitBreakerFactory ->
            factory.configureDefault { id: String? ->
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
                    .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                            .withExecutionTimeoutInMilliseconds(5000)
                    )
            }
        }
    }
}