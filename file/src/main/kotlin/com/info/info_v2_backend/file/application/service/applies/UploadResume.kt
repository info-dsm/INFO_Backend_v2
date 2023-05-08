package com.info.info_v2_backend.file.application.service.applies

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.UploadFileUsecase
import com.info.info_v2_backend.file.application.port.input.applies.UploadResumeUsecase
import com.info.info_v2_backend.file.application.port.output.RemoveFilePort
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.applies.LoadResumePort
import com.info.info_v2_backend.file.application.port.output.applies.SaveResumeFilePort
import com.info.info_v2_backend.file.domain.applies.Resume
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*

@Service
class UploadResume(
    private val saveResumeFilePort: SaveResumeFilePort,
    private val uploadFileUsecase: UploadFileUsecase
): UploadResumeUsecase {

    override fun uploadResume(
        request: GenerateFileRequest,
        noticeId: String,
        studentEmail: String
    ): PresignedUrlResponse {
        val dto = uploadFileUsecase.upload(request, "NOTICE/${noticeId}", "RESUME")
        val resume = Resume(
            dto,
            noticeId,
            studentEmail
        )

        saveResumeFilePort.save(resume)
        return PresignedUrlResponse(
            dto.authenticatedUri?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR),
            dto.fileName
        )
    }
}
