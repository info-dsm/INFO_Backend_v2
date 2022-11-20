package com.info.info_v2_backend.notice.application.port.input

import org.springframework.web.multipart.MultipartFile

interface ChangeAttachmentUsecase {

    fun change(companyNumber: String, noticeId: String, attachmentList: List<MultipartFile>)
}