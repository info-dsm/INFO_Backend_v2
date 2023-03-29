package com.info.info_v2_backend.statistics.infra.feign

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class FileFeignClientFallback: FallbackFactory<FileFeignClient> {
    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {
            override fun createAnnouncementFile(
                announcementId: Long,
                request: GenerateFileListRequest
            ): PresignedUrlListResponse {
                throw BusinessException(
                    errorCode = ErrorCode.FEIGN_ERROR
                )
            }

            override fun getAnnouncementFileList(announcementId: Long): List<AnnouncementFileResponse> {
                return ArrayList()
            }
        }
    }
}