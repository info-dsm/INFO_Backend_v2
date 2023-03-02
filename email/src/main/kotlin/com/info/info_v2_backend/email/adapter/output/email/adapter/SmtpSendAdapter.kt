package com.info.info_v2_backend.email.adapter.output.email.adapter

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.email.adapter.output.email.configuration.MailProperty
import com.info.info_v2_backend.email.application.port.output.SmtpSendPort
import org.springframework.mail.MailException
import org.springframework.mail.MailParseException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import javax.mail.internet.MimeMessage

@Service
class SmtpSendAdapter(
    private val jms: JavaMailSender,
    private val templateEngine: TemplateEngine,
    private val mailProperty: MailProperty
): SmtpSendPort{

    override fun send(to: String, title: String, templatePath: String, models: Map<String, String>?, data: String?) {
        val message: MimeMessage = jms.createMimeMessage()

        val context = Context()
        var content = ""

        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(mailProperty.username)
        helper.setSubject(title)
        helper.setTo(to)

        models?.forEach(context::setVariable)
        models?.let {
            content = templateEngine.process(templatePath, context)
            helper.setText(content, true)
        }?:let {
            data?.let {
                helper.setText(it, false)
            }?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
        }


        async fetch(request) {

            send_request = new Request('https://api.mailchannels.net/tx/v1/send', {
            method: 'POST',
            headers: {
            'content-type': 'application/json',
        },
            body: JSON.stringify({
            personalizations: [
            {
                to: [{ email: 'test@example.com', name: 'Test Recipient' }],
            },
            ],
            from: {
                email: 'sender@example.com',
                name: 'Workers - MailChannels integration',
        },
            subject: 'Look! No servers',
            content: [
            {
                    type: 'text/plain',
                    value: 'And no email service accounts and all for free too!',
            },
            ],
        }),
        })
        },
    }
        jms.send(message)
    }

}