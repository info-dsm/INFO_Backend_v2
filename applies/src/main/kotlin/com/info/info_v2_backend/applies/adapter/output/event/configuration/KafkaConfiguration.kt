package com.info.info_v2_backend.applies.adapter.output.event.configuration

import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.common.file.dto.UploadCompanyFileDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfiguration(
    private val kafkaProperty: KafkaProperty
) {

    private val bootstrapServers = kafkaProperty.kafkaServerAddress


    @Bean
    fun stringProducerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(stringProducerFactoryConfigs())
    }

    private fun stringProducerFactoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return configs
    }

    private fun factoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }


    @Bean
    fun stringKafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(stringProducerFactory())
    }




}