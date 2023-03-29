package com.info.info_v2_backend.statistics.presentation.dto.response

import com.info.info_v2_backend.statistics.persistance.entity.announcement.AnnouncementType
import java.time.LocalDateTime

data class MinimumAnnouncementResponse(
    val title: String,
    val createdAt: LocalDateTime,
    val type: AnnouncementType
)
