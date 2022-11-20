package com.info.info_v2_backend.common.notice

import com.info.info_v2_backend.common.file.dto.FileDto

data class NoticeFileDto (
    val dto: FileDto,
    val noticeId: String
)