package com.info.info_v2_backend.common.email.dto

data class SendEmailTextRequest(
    val targetEmail: String,
    val title: String,
    val content: String
)
