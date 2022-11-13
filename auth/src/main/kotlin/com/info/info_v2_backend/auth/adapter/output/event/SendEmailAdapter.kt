package com.info.info_v2_backend.auth.adapter.output.event

import com.info.info_v2_backend.auth.application.port.output.SendEmailPort
import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.event.KafkaTopics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class SendEmailAdapter(
    private val emailSender: KafkaTemplate<String, SendEmailNotificationRequest>
): SendEmailPort {

    override fun send(request: SendEmailNotificationRequest) {
        emailSender.send(KafkaTopics.SEND_EMAIL, request)
    }


}