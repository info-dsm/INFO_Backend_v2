package com.info.info_v2_backend.notice.application.service.change

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.notice.application.port.input.change.ChangeAttachmentUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import org.springframework.stereotype.Service


@Service
class ChangeAttachment(
    private val loadNoticePort: LoadNoticePort,
    private val filePort: FilePort
): ChangeAttachmentUsecase {
    override fun change(
        companyNumber: String,
        noticeId: String,
        request: GenerateFileListRequest
    ): PresignedUrlListResponse {
        val notice = loadNoticePort.loadNotice(noticeId)
            .takeIf {
                it?.company?.companyNumber == companyNumber
            }
            ?: throw BusinessException(
                "모집공고를 조회할 수 없습니다. -> $companyNumber, $noticeId",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        val urlList = filePort.saveFile(
            noticeId,
            request
        )

        filePort.removeFile(noticeId)
        return urlList
    }


}