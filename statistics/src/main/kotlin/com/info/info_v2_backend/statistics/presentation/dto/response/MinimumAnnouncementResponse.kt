package com.info.info_v2_backend.statistics.presentation.dto.response

import java.time.LocalDateTime

data class MinimumAnnouncementResponse(
    val title: String,
    val createdAt: LocalDateTime
)
