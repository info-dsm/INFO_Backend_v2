package com.info.info_v2_backend.email.application.service

import com.info.info_v2_backend.common.email.EmailTemplateType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.email.dto.SendEmailTextRequest
import com.info.info_v2_backend.common.auth.HeaderProperty
import com.info.info_v2_backend.email.application.port.input.SendEmailUsecase
import com.info.info_v2_backend.email.application.port.output.EmailRecordPersistencePort
import com.info.info_v2_backend.email.application.port.output.LoadEmailUserPort
import com.info.info_v2_backend.email.application.port.output.SmtpSendPort
import com.info.info_v2_backend.email.domain.EmailRecord
import com.info.info_v2_backend.email.domain.content.EmailContent
import com.info.info_v2_backend.email.domain.user.Sender
import org.springframework.mail.MailException
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Service
class SendEmail(
    private val emailUserPort: LoadEmailUserPort,
    private val emailRecordPersistencePort: EmailRecordPersistencePort,
    private val smtpSendPort: SmtpSendPort
): SendEmailUsecase {

    override fun command(targetEmail: String, emailContent: EmailContent, senderEmail: String?) {
        var sender = ""
        if (senderEmail == "system") {
            sender = "system"
        } else {
            sender = emailUserPort.loadEmailUser(
                senderEmail
                    ?: (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(
                        HeaderProperty.USER_EMAIL
                    )
            )?: throw BusinessException("사용자를 찾지 못했습니다. -> ${targetEmail}", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        }

        val target = emailUserPort.loadEmailUser(targetEmail)?: let {
            if (sender == "system") return@let targetEmail
            else throw BusinessException("사용자를 찾지 못했습니다. -> ${targetEmail}", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        }

        val record = emailRecordPersistencePort.save(
            EmailRecord(
                com.info.info_v2_backend.email.domain.user.Target(
                    target,
                ),
                Sender(
                    sender,
                ),
                emailContent
            )
        )

        try {
            smtpSendPort.send(
                target,
                record.content.title,
                record.content.templateType.templatePath,
                record.content.model,
                record.content.text
            )
        } catch (e: MailException) {
            record.fail()
            emailRecordPersistencePort.save(record)
            throw BusinessException(e.message, ErrorCode.BAD_GATEWAY_ERROR)
        }
        record.complete()
        emailRecordPersistencePort.save(record)
    }

    override fun sendEmailTextCommand(command: SendEmailTextRequest, senderEmail: String?) {
        return command(
            command.targetEmail,
            EmailContent(
                command.title,
                EmailTemplateType.TEXT,
                null,
                command.content
            ),
            senderEmail
        )
    }

    override fun sendEmailNotificationCommand(command: SendEmailNotificationRequest, senderEmail: String?) {
        return command(
            command.targetEmail,
            EmailContent(
                command.title,
                EmailTemplateType.NOTIFICATION,
                command.data,
                null
            ),
            senderEmail
        )
    }

}