package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.application.port.input.EditNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import org.springframework.stereotype.Service

@Service
class EditNotice(
    private val loadNoticePort: LoadNoticePort,
    private val saveNoticePort: SaveNoticePort
): EditNoticeUsecase {

    override fun edit(noticeId: String, request: EditNoticeRequest, companyNumber: String) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "Notice를 찾지 못했습니다. -> $noticeId",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        if (notice.company.companyNumber == companyNumber)
            throw BusinessException(
                "회사 정보가 올바르지 않습니다. -> $companyNumber",
                ErrorCode.INVALID_INPUT_DATA_ERROR
            )
        notice.editNotice(request)
        saveNoticePort.saveNotice(notice)

    }
}