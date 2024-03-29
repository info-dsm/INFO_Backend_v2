package com.info.info_v2_backend.applies.application.port.output.resume

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.multipart.MultipartFile

interface ResumePort {

    fun uploadResume(noticeId: String, studentEmail: String, @RequestBody request: GenerateFileListRequest): PresignedUrlListResponse
    fun loadAppliesResume(noticeId: String, studentEmail: String): List<FileResponse>
    fun removeResume(noticeId: String, studentEmail: String)
}