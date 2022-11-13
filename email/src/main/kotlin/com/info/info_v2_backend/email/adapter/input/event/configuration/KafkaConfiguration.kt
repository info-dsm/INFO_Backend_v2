package com.info.info_v2_backend.email.adapter.input.event.configuration

import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaConfiguration(
    private val kafkaProperty: KafkaProperty
) {

    private val bootstrapServers = kafkaProperty.kafkaServerAddress

    @Bean
    fun emailConsumerFactory(): ConsumerFactory<String, SendEmailNotificationRequest> {
        return DefaultKafkaConsumerFactory<String, SendEmailNotificationRequest>(
            consumerFactoryConfigs(),
            StringDeserializer(),
            JsonDeserializer(SendEmailNotificationRequest::class.java)
        )
    }

    @Bean
    fun sendEmailNotificationRequestChangeListener(): ConcurrentKafkaListenerContainerFactory<String, SendEmailNotificationRequest> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, SendEmailNotificationRequest>()
        factory.consumerFactory = emailConsumerFactory()
        return factory
    }

    private fun consumerFactoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap<String, Any>()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }


}