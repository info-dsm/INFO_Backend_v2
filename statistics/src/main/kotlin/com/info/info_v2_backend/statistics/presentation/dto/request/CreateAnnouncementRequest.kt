package com.info.info_v2_backend.statistics.presentation.dto.request

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest

data class CreateAnnouncementRequest (
    val title: String,
    val content: String,
    val fileList: GenerateFileListRequest
)