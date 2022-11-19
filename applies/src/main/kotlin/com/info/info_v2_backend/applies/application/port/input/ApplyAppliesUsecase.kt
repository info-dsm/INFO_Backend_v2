package com.info.info_v2_backend.applies.application.port.input

import org.springframework.web.multipart.MultipartFile

interface ApplyAppliesUsecase {

    fun apply(noticeId: String, resume: MultipartFile, studentEmail: String)
}