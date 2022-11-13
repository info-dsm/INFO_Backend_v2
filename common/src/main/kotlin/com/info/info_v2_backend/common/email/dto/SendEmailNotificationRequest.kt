package com.info.info_v2_backend.common.email.dto

data class SendEmailNotificationRequest (
    val targetEmail: String,
    val title: String,
    val data: Map<String, String>
)
