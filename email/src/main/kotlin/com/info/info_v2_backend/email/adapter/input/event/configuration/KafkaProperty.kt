package com.info.info_v2_backend.email.adapter.input.event.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("kafka")
@ConstructorBinding
data class KafkaProperty(
    val kafkaServerAddress: String

)
