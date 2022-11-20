package com.info.info_v2_backend.applies.application.port.output.upload

import org.springframework.web.multipart.MultipartFile

interface UploadResumePort {

    fun uploadResume(noticeId: String, studentEmail: String, resume: MultipartFile)
}