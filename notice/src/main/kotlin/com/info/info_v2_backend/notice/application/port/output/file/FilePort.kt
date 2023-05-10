package com.info.info_v2_backend.notice.application.port.output.file

import com.info.info_v2_backend.common.file.dto.response.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse

interface FilePort {

    fun saveFile(noticeId: String, request: GenerateFileListRequest): PresignedUrlListResponse
    fun loadAttachmentList(noticeId: String): List<AttachmentResponse>
    fun removeFile(noticeId: String)
}
