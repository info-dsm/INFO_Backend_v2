package com.info.info_v2_backend.notice.application.port.input

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest

interface CreateNoticeUsecase {

    fun create(companyNumber: String, request: CreateNoticeRequest)
}