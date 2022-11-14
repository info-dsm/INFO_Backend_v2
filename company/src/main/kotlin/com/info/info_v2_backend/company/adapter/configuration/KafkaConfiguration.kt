package com.info.info_v2_backend.company.adapter.configuration

import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.common.file.UploadCompanyFileDto
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
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
    fun contactorDtoProducerFactory(): ProducerFactory<String, ContactorDto> {
        return DefaultKafkaProducerFactory(factoryConfigs())
    }

    @Bean
    fun uploadCompanyFileDtoProducerFactory(): ProducerFactory<String, UploadCompanyFileDto> {
        return DefaultKafkaProducerFactory(factoryConfigs())
    }

    @Bean
    fun registerCompanyFileDtoConsumerFactory(): ConsumerFactory<String, RegisterCompanyFileDto> {
        return DefaultKafkaConsumerFactory(
            factoryConfigs(),
            StringDeserializer(),
            JsonDeserializer(RegisterCompanyFileDto::class.java)
        )
    }

    private fun factoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }

    @Bean
    fun contactorDtoKafkaTemplate(): KafkaTemplate<String, ContactorDto> {
        return KafkaTemplate(contactorDtoProducerFactory())
    }

    @Bean
    fun companyFileDtoTemplate(): KafkaTemplate<String, UploadCompanyFileDto> {
        return KafkaTemplate(uploadCompanyFileDtoProducerFactory())
    }

    @Bean
    fun registerCompanyFileDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, RegisterCompanyFileDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, RegisterCompanyFileDto>()
        factory.consumerFactory = registerCompanyFileDtoConsumerFactory()
        return factory
    }


}