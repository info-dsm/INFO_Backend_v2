package com.info.info_v2_backend.email.application.service

import com.info.info_v2_backend.common.email.EmailTemplateType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.email.dto.SendEmailTextRequest
import com.info.info_v2_backend.common.security.HeaderProperty
import com.info.info_v2_backend.email.application.port.input.SendEmailUsecase
import com.info.info_v2_backend.email.application.port.output.EmailRecordPersistencePort
import com.info.info_v2_backend.email.application.port.output.LoadEmailUserPort
import com.info.info_v2_backend.email.application.port.output.SmtpSendPort
import com.info.info_v2_backend.email.domain.EmailRecord
import com.info.info_v2_backend.email.domain.content.EmailContent
import com.info.info_v2_backend.email.domain.user.Sender
import org.springframework.mail.MailException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Service
@Transactional
class SendEmail(
    private val emailUserPort: LoadEmailUserPort,
    private val emailRecordPersistencePort: EmailRecordPersistencePort,
    private val smtpSendPort: SmtpSendPort
): SendEmailUsecase {

    private fun command(targetEmail: String, emailContent: EmailContent, senderEmail: String?) {
        val target = emailUserPort.loadEmailUser(targetEmail)
        val sender = emailUserPort.loadEmailUser(
            senderEmail?:
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(HeaderProperty.USER_EMAIL)
        )

        val record = emailRecordPersistencePort.save(
            EmailRecord(
                com.info.info_v2_backend.email.domain.user.Target(
                    target.userEmail,
                    target.userId
                ),
                Sender(
                    sender.userEmail,
                    sender.userId
                ),
                emailContent
            )
        )

        try {
            smtpSendPort.send(
                target.userEmail,
                record.content.title,
                record.content.templateType.templatePath,
                record.content.model,
                record.content.text
            )
        } catch (e: MailException) {
            record.fail()
            throw BusinessException(e.message, ErrorCode.BAD_GATEWAY)
        }
        record.complete()
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
                EmailTemplateType.TEXT,
                command.data,
                null
            ),
            senderEmail
        )
    }

}