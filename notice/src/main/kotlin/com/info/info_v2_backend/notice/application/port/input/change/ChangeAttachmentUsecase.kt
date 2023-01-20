package com.info.info_v2_backend.notice.application.port.input.change

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse

interface ChangeAttachmentUsecase {

    fun change(companyNumber: String, noticeId: String, request: GenerateFileListRequest): PresignedUrlListResponse
}