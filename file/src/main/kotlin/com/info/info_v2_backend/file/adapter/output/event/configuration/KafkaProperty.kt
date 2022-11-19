package com.info.info_v2_backend.file.adapter.output.event.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConfigurationProperties(value = "kafka")
@ConstructorBinding
class KafkaProperty(
    val kafkaServerAddress: String

)