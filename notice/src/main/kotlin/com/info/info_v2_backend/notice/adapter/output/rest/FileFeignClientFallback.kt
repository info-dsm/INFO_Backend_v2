package com.info.info_v2_backend.notice.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.response.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class FileFeignClientFallback: FallbackFactory<FileFeignClient> {
    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {
            override fun saveFile(noticeId: String, request: GenerateFileListRequest): PresignedUrlListResponse {
                throw BusinessException("파일을 업로드하는 중 오류가 발생했습니다.", ErrorCode.BAD_GATEWAY_ERROR)
            }

            override fun loadAttachmentList(noticeId: String): List<AttachmentResponse> {
                return ArrayList()
            }

            override fun removeFile(noticeId: String) {
                return
            }

        }
    }


}
