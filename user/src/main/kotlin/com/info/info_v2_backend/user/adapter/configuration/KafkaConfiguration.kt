package com.info.info_v2_backend.user.adapter.configuration

import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfiguration(
    private val kafkaProperty: KafkaProperty
) {

    private val bootstrapServers = kafkaProperty.kafkaServerAddress

    @Bean
    fun teacherConsumer(): ConsumerFactory<String, TeacherDto> {
        return DefaultKafkaConsumerFactory(
            consumerFactoryConfigs(),
            StringDeserializer(),
            JsonDeserializer(TeacherDto::class.java)
        )
    }

    @Bean
    fun studentConsumer(): ConsumerFactory<String, StudentDto> {
        return DefaultKafkaConsumerFactory(
            consumerFactoryConfigs(),
            StringDeserializer(),
            JsonDeserializer(StudentDto::class.java)
        )
    }


    private fun consumerFactoryConfigs(): MutableMap<String, Any> {
        val configs: MutableMap<String, Any> = HashMap<String, Any>()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return configs
    }


    @Bean
    fun teacherDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, TeacherDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, TeacherDto>()
        factory.consumerFactory = teacherConsumer()
        return factory
    }

    @Bean
    fun studentDtoChangeListener(): ConcurrentKafkaListenerContainerFactory<String, StudentDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, StudentDto>()
        factory.consumerFactory = studentConsumer()
        return factory
    }

}