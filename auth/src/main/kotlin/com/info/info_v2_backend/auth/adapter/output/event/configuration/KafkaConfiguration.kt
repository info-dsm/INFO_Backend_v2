package com.info.info_v2_backend.auth.adapter.output.event.configuration

import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfiguration(
    private val kafkaProperty: KafkaProperty
) {

    private val bootstrapServers = kafkaProperty.kafkaServerAddress

    @Bean
    fun teacherDtoProducerFactory(): ProducerFactory<String, SaveTeacherDto> {
        return DefaultKafkaProducerFactory(factoryConfigs())
    }

    @Bean
    fun studentDtoProducerFactory(): ProducerFactory<String, SaveStudentDto> {
        return DefaultKafkaProducerFactory(factoryConfigs())
    }

    @Bean
    fun sendEmailNotificationRequestProducerFactory():
            ProducerFactory<String, SendEmailNotificationRequest> {
        return DefaultKafkaProducerFactory(factoryConfigs())
    }

    private fun factoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap()
        configs[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }

    @Bean
    fun teacherDtoKafkaTemplate(): KafkaTemplate<String, SaveTeacherDto> {
        return KafkaTemplate(teacherDtoProducerFactory())
    }

    @Bean
    fun studentDtoKafkaTemplate(): KafkaTemplate<String, SaveStudentDto> {
        return KafkaTemplate(studentDtoProducerFactory())
    }

    @Bean
    fun sendEmailNotificationRequestKafkaTemplate(): KafkaTemplate<String, SendEmailNotificationRequest> {
        return KafkaTemplate(sendEmailNotificationRequestProducerFactory())
    }


}