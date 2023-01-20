package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeWithApproveStatusResponse
import com.info.info_v2_backend.notice.application.port.input.LoadNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.LoadWithConditionPort
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LoadNotice(
    private val loadNoticePort: LoadNoticePort,
    private val loadWithConditionPort: LoadWithConditionPort,
    private val filePort: FilePort
): LoadNoticeUsecase {

    override fun loadMaximumNotice(noticeId: String): MaximumNoticeResponse {
        val maximumNoticeResponse = (loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "Notice를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
                ).toMaximumNoticeResponse()
        maximumNoticeResponse.addAllAttachmentFileList(filePort.loadAttachmentList(noticeId).toMutableList())
        return maximumNoticeResponse
    }

    override fun loadNotEndedMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse> {
        return loadWithConditionPort.loadBeforeEndDateAndStatusNoticeList(idx, size, LocalDate.now(), NoticeWaitingStatus.APPROVE)
    }

    override fun loadEndedMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse> {
        return loadWithConditionPort.loadAfterEndDateAndStatusNoticeList(idx, size, LocalDate.now(), NoticeWaitingStatus.APPROVE)
    }

    override fun loadCompanyMinimumNoticeList(companyNumber: String): List<MinimumNoticeResponse> {
        return loadNoticePort.loadNoticeByCompany(companyNumber).filter {
            it.approveStatus == NoticeWaitingStatus.APPROVE
        }.map {
            it.toMinimumNoticeResponse()
        }
    }

    override fun loadCompanyMinimumNoticeWithApproveStatusList(companyNumber: String): List<MinimumNoticeWithApproveStatusResponse> {
        return loadNoticePort.loadNoticeByCompany(companyNumber).map {
            it.toMinimumNoticeWithApproveStatusResponse()
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