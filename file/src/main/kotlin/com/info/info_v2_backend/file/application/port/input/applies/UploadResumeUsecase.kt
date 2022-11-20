package com.info.info_v2_backend.file.application.port.input.applies

import org.springframework.web.multipart.MultipartFile

interface UploadResumeUsecase {

    fun uploadResume(resume: MultipartFile, noticeId: String, studentEmail: String)
}