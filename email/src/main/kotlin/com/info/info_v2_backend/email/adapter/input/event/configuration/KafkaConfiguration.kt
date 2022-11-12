package com.info.info_v2_backend.email.adapter.input.event.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaConfiguration(
    private val kafkaProperty: KafkaProperty
) {

    private val bootstrapServers = kafkaProperty.kafkaServerAddress

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory<String, String>(configs)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }


}