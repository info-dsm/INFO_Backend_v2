package com.info.info_v2_backend.announcement.presentation.dto.request

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.announcement.persistance.entity.AnnouncementType

data class CreateAnnouncementRequest (
    val title: String,
    val content: String,
    val fileList: GenerateFileListRequest,
    val type: AnnouncementType
)
