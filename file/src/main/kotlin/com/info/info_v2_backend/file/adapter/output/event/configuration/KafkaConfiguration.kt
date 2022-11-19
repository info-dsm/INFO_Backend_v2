package com.info.info_v2_backend.file.adapter.configuration

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
    fun uploadCompanyFileDtoConsumerFactory(): ConsumerFactory<String, UploadCompanyFileDto> {
        return DefaultKafkaConsumerFactory(
            consumerFactoryConfigs(),
            StringDeserializer(),
            JsonDeserializer(UploadCompanyFileDto::class.java)
        )
    }

    @Bean
    fun registerCompanyFileDtoProducerFactory(): ProducerFactory<String, RegisterCompanyFileDto> {
        return DefaultKafkaProducerFactory<String, RegisterCompanyFileDto> (
            producerFactoryConfigs(),
        )
    }

    private fun consumerFactoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap<String, Any>()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }

    private fun producerFactoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap<String, Any>()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }

    @Bean
    fun uploadCompanyFileDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, UploadCompanyFileDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, UploadCompanyFileDto>()
        factory.consumerFactory = uploadCompanyFileDtoConsumerFactory()
        return factory
    }

    @Bean
    fun registerCompanyFileDtoKafkaTemplate(): KafkaTemplate<String, RegisterCompanyFileDto> {
        return KafkaTemplate(registerCompanyFileDtoProducerFactory())
    }


}