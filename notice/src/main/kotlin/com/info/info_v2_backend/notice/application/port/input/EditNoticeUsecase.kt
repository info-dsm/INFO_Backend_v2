package com.info.info_v2_backend.notice.application.port.input

import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest

interface EditNoticeUsecase {

    fun edit(noticeId: String, request: EditNoticeRequest, companyNumber: String): PresignedUrlListResponse
}