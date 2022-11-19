package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.application.port.input.LoadNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LoadNotice(
    private val loadNoticePort: LoadNoticePort
): LoadNoticeUsecase {

    override fun loadMaximumNotice(noticeId: String): MaximumNoticeResponse {
        return (loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "Notice를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )).toMaximumNoticeResponse()
    }

    override fun loadMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse> {
        return loadNoticePort.loadOnDateAndApproveNoticeList(idx, size, LocalDate.now()).map {
            it.toMinimumNoticeResponse()
        }
    }

    override fun loadAvailableNotice(noticeId: String): NoticeDto? {
        val notice = loadNoticePort.loadNotice(noticeId)
        notice?.let {
            if (it.checkIsAvailableAppliesStatus())
                return it.toNoticeDto()
            return null
        }
        return null
    }

    override fun loadNoticeDto(noticeId: String): NoticeDto? {
        return loadNoticePort.loadNotice(noticeId)?.toNoticeDto()
    }


}