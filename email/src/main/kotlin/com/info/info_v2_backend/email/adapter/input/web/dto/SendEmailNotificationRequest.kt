package com.info.info_v2_backend.email.adapter.input.web.dto

data class SendEmailNotificationRequest (
    val targetEmail: String,
    val title: String,
    val data: Map<String, String>
)
