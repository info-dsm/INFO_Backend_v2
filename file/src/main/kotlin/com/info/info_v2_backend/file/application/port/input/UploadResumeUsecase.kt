package com.info.info_v2_backend.file.application.port.input

import org.springframework.web.multipart.MultipartFile

interface UploadResumeUsecase {

    fun uploadResume(resume: MultipartFile, noticeId: String)
}