package com.info.info_v2_backend.notice.adapter.configuration

import com.info.info_v2_backend.common.notice.UpdateNoticeAppliesCountDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import java.io.Serializable

@Configuration
class KafkaConfiguration(
    private val env: Environment
) {

    private val bootstrapServers = env.get("spring.kafka.bootstrap-servers")?: "localhost:9092"

    @Bean
    fun updateNoticeApplicantCountConsumer(): ConsumerFactory<String, UpdateNoticeAppliesCountDto> {
        return DefaultKafkaConsumerFactory(
            consumerFactoryConfigs(),
            StringDeserializer(),
            JsonDeserializer(UpdateNoticeAppliesCountDto::class.java)
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
    fun updateNoticeApplicantCountChangeListener(): ConcurrentKafkaListenerContainerFactory<String, UpdateNoticeAppliesCountDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, UpdateNoticeAppliesCountDto>()
        factory.consumerFactory = updateNoticeApplicantCountConsumer()
        return factory
    }


    fun stringProducerFactoryConfigs(): Map<String, Serializable> =
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
        )

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(DefaultKafkaProducerFactory<String, String>(stringProducerFactoryConfigs()))
    }


}