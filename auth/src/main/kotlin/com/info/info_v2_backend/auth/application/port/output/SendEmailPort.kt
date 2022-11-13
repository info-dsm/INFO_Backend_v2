package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest

interface SendEmailPort {

    fun send(request: SendEmailNotificationRequest)
}