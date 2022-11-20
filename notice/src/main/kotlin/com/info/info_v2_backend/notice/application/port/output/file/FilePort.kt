package com.info.info_v2_backend.notice.application.port.output.file

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import org.springframework.web.multipart.MultipartFile

interface FilePort {

    fun saveFile(noticeId: String, file: MultipartFile)
    fun loadAttachmentList(noticeId: String): List<AttachmentResponse>
}