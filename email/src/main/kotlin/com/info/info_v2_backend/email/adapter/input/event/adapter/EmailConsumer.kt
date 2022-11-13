package com.info.info_v2_backend.email.adapter.input.event.adapter

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.email.dto.SendEmailTextRequest
import com.info.info_v2_backend.email.application.port.input.SendEmailUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class EmailConsumer(
    private val sendEmailUsecase: SendEmailUsecase
) {

    @KafkaListener(topics = [KafkaTopics.SEND_EMAIL], containerFactory = "sendEmailNotificationRequestChangeListener")
    fun sendEmailNotification(
        request: SendEmailNotificationRequest
    ) {
        sendEmailUsecase.sendEmailNotificationCommand(
            request,
            "system"
        )
    }

}