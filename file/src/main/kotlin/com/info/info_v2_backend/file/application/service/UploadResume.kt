package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
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
    private val uploadFilePort: UploadFilePort,
    private val removeFilePort: RemoveFilePort,
    private val loadResumePort: LoadResumePort
): UploadResumeUsecase {

    override fun uploadResume(
        request: GenerateFileRequest,
        noticeId: String,
        studentEmail: String
    ): PresignedUrlResponse {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.getPresignedUrl(request.fileName, request.contentType, "NOTICE/${noticeId}", "RESUME/${fileId}")
        val authUrl = dto.fileUrl
        dto.removeParameter()
        val resume = Resume(
            fileId,
            dto,
            noticeId,
            studentEmail
        )

        saveResumeFilePort.save(resume)
        return PresignedUrlResponse(
            authUrl,
            dto.fileName
        )
    }
}