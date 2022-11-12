package com.info.info_v2_backend.email.adapter.input.web.dto

data class SendEmailTextRequest(
    val targetEmail: String,
    val title: String,
    val content: String
)
