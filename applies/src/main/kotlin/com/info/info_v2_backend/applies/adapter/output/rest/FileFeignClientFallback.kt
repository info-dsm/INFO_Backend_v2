package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileFeignClientFallback: FallbackFactory<FileFeignClient> {

    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {
            override fun uploadResume(noticeId: String, studentEmail: String, request: GenerateFileListRequest): PresignedUrlListResponse {
                throw BusinessException(null, ErrorCode.BAD_GATEWAY_ERROR)
            }

            override fun loadAppliesResume(noticeId: String, studentEmail: String): List<FileResponse> {
                return ArrayList()
            }

            override fun removeResume(noticeId: String, studentEmail: String) {
                return
            }

        }
    }

}