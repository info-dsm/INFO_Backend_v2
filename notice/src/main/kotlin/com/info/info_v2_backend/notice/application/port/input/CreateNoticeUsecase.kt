package com.info.info_v2_backend.notice.application.port.input

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import org.springframework.web.multipart.MultipartFile

interface CreateNoticeUsecase {

    fun create(companyNumber: String, request: CreateNoticeRequest, attachment: List<MultipartFile>)
}