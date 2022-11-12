package com.info.info_v2_backend.email.application.port.output

interface SmtpSendPort {

    fun send(to: String, title: String, templatePath: String, models: Map<String, String>?, data: String?)
}