package com.info.info_v2_backend.email.application.service

import com.info.info_v2_backend.common.email.EmailTemplateType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.user.UserEmailIdDto
import com.info.info_v2_backend.email.adapter.input.web.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.email.adapter.input.web.dto.SendEmailTextRequest
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

@Service
@Transactional
class SendEmail(
    private val emailUserPort: LoadEmailUserPort,
    private val emailRecordPersistencePort: EmailRecordPersistencePort,
    private val smtpSendPort: SmtpSendPort
): SendEmailUsecase {

    private fun command(targetEmail: String, emailContent: EmailContent) {
//        val target = emailUserPort.loadEmailUser(targetId)
//        val requesterId = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader("USER_ID")
//        val sender = emailUserPort.loadEmailUser(requesterId)
        val target = UserEmailIdDto(
            "1",
            "jinwoo794533@gmail.com"
        )
        val sender = UserEmailIdDto(
            "1",
            "jinwoo794533@gmail.com"
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

    override fun sendEmailTextCommand(command: SendEmailTextRequest) {
        return command(
            command.targetEmail,
            EmailContent(
                command.title,
                EmailTemplateType.TEXT,
                null,
                command.content
            )
        )
    }

    override fun sendEmailNotificationCommand(command: SendEmailNotificationRequest) {
        return command(
            command.targetEmail,
            EmailContent(
                command.title,
                EmailTemplateType.TEXT,
                command.data,
                null
            )
        )
    }

}