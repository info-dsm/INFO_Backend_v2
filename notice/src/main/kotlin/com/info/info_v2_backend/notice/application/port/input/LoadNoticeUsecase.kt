package com.info.info_v2_backend.notice.application.port.input

import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.AdminMaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeWithApproveStatusResponse
import org.springframework.data.domain.Page

interface LoadNoticeUsecase {

    fun loadMaximumNotice(noticeId: String): MaximumNoticeResponse
    fun loadAdminMaximunNotice(noticeId: String): AdminMaximumNoticeResponse

    fun loadNotEndedMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse>
    fun loadEndedMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse>

    fun loadCompanyMinimumNoticeList(companyNumber: String): List<MinimumNoticeResponse>
    fun loadCompanyMinimumNoticeWithApproveStatusList(companyNumber: String): List<MinimumNoticeWithApproveStatusResponse>

    fun loadCustomNoticeList(userEmail: String, idx: Int, size: Int): Page<MinimumNoticeResponse>

    fun loadAvailableNotice(noticeId: String): NoticeDto?
    fun loadNoticeDto(noticeId: String): NoticeDto?

    fun searchNotice(companyName: String?, smallClassification: String?, idx: Int, size: Int): Page<MinimumNoticeResponse>

}
