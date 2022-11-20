package com.info.info_v2_backend.file.application.port.input.notice

import org.springframework.web.multipart.MultipartFile

interface UploadAttachmentUsecase {

    fun uploadAttachment(attachment: MultipartFile, noticeId: String)
}