package com.info.info_v2_backend.file.application.port.input.applies

import com.info.info_v2_backend.common.file.dto.response.FileResponse

interface LoadResumeUsecase {

    fun load(noticeId: String, studentEmail: String): FileResponse
}