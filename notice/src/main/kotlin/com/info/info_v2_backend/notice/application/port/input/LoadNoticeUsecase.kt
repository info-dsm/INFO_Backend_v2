package com.info.info_v2_backend.notice.application.port.input

import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import org.springframework.data.domain.Page

interface LoadNoticeUsecase {

    fun loadMaximumNotice(noticeId: String): MaximumNoticeResponse
    fun loadMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse>
    fun loadAvailableNotice(noticeId: String): NoticeDto?
    fun loadNoticeDto(noticeId: String): NoticeDto?
}