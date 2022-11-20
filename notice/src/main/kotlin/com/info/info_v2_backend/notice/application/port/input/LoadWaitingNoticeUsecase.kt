package com.info.info_v2_backend.notice.application.port.input

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import org.springframework.data.domain.Page

interface LoadWaitingNoticeUsecase {

    fun load(idx: Int, size: Int): Page<MinimumNoticeResponse>
}