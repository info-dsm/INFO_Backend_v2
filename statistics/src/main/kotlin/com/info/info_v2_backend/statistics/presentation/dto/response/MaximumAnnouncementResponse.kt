package com.info.info_v2_backend.statistics.presentation.dto.response

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import java.time.LocalDateTime

data class MaximumAnnouncementResponse(
    val title: String,
    val content: String,
    val fileList: List<AnnouncementFileResponse>,
    val createdAt: LocalDateTime
)
