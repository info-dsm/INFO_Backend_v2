package com.info.info_v2_backend.notice.application.port.output.file

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import org.springframework.web.multipart.MultipartFile

interface FilePort {

    fun saveFile(noticeId: String, request: GenerateFileListRequest): PresignedUrlListResponse
    fun loadAttachmentList(noticeId: String): List<AttachmentResponse>
}