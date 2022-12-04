package com.info.info_v2_backend.common.notice

import com.info.info_v2_backend.common.file.dto.response.FileResponse

data class NoticeFileDto (
    val dto: FileResponse,
    val noticeId: String
)