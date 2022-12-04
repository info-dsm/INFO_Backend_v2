package com.info.info_v2_backend.applies.application.port.output.resume

import com.info.info_v2_backend.common.file.dto.response.FileResponse
import org.springframework.web.multipart.MultipartFile

interface ResumePort {

    fun uploadResume(noticeId: String, studentEmail: String, resume: MultipartFile)
    fun loadAppliesResume(noticeId: String, studentEmail: String): FileResponse
}