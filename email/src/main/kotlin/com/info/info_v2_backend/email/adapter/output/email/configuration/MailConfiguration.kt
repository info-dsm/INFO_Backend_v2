package com.info.info_v2_backend.email.adapter.output.email.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class MailConfiguration(
    private val env: MailProperty
) {

    companion object {
        const val TLS_REQUIRED = "mail.smtp.starttls.required"
        const val TLS_ENABLE = "mail.smtp.starttls.enable"
        const val SMTP_AUTH = "mail.smtp.auth"
        const val SMTP_SOCKET_FACTORY_PORT= "mail.smtp.socketFactory.port"
        const val SMTP_SOCKET_FACTORY_FALlBACK = "mail.smtp.socketFactory.fallback"
        const val TRANSPORT_PROTOCOL="mail.transport.protocol"
    }

    @Bean
    fun mailSender(): JavaMailSender {
        val jms = JavaMailSenderImpl()
        jms.host = env.host
        jms.port = env.port
        jms.username = env.username
        jms.password = env.password
        jms.protocol = env.protocol
        jms.defaultEncoding = "UTF-8"

        val pt = Properties()
        pt.put(TLS_REQUIRED, true)
        pt.put(TLS_ENABLE, true)
        pt.put(SMTP_AUTH, true)
        pt.put(SMTP_SOCKET_FACTORY_PORT, 587);
        pt.put(SMTP_SOCKET_FACTORY_FALlBACK, false);
        pt.put(TRANSPORT_PROTOCOL, env.protocol);
        pt.put("mail.debug", true)

        jms.javaMailProperties = pt
        return jms
    }

}