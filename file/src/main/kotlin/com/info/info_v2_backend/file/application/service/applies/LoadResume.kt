package com.info.info_v2_backend.file.application.service.applies

import com.info.info_v2_backend.common.file.dto.response.FileResponse
import com.info.info_v2_backend.file.application.port.input.applies.LoadResumeUsecase
import com.info.info_v2_backend.file.application.port.output.applies.LoadResumePort
import org.springframework.stereotype.Service


@Service
class LoadResume(
    private val loadResumePort: LoadResumePort
): LoadResumeUsecase {
    override fun load(noticeId: String, studentEmail: String): List<FileResponse> {
        return loadResumePort.load(noticeId, studentEmail).map { it.toFileResponse() }.toList()
    }


}
