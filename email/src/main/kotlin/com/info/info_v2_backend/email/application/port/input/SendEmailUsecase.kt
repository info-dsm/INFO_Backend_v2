package com.info.info_v2_backend.email.application.port.input

import com.info.info_v2_backend.email.adapter.input.web.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.email.adapter.input.web.dto.SendEmailTextRequest

interface SendEmailUsecase {

    fun sendEmailTextCommand(command: SendEmailTextRequest)
    fun sendEmailNotificationCommand(command: SendEmailNotificationRequest)

}