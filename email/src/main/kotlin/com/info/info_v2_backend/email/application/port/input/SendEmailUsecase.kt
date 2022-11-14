package com.info.info_v2_backend.email.application.port.input

import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.email.dto.SendEmailTextRequest
import com.info.info_v2_backend.email.domain.content.EmailContent

interface SendEmailUsecase {

    fun command(targetEmail: String, emailContent: EmailContent, senderEmail: String?)
    fun sendEmailTextCommand(command: SendEmailTextRequest, senderEmail: String?)
    fun sendEmailNotificationCommand(command: SendEmailNotificationRequest, senderEmail: String?)

}