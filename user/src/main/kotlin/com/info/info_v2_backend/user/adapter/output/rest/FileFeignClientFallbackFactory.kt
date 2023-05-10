package com.info.info_v2_backend.user.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.common.file.dto.response.UserFileResponse
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class FileFeignClientFallbackFactory: FallbackFactory<FileFeignClient> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {
            override fun upload(request: GenerateFileRequest, userEmail: String): PresignedUrlResponse {
                throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR)
            }

            override fun loadProfilePhoto(email: String): UserFileResponse? {
                return null
            }


        }
    }
}
