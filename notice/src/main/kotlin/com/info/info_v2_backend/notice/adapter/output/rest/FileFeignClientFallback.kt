package com.info.info_v2_backend.notice.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.notice.adapter.output.rest.FileFeignClient
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileFeignClientFallback: FallbackFactory<FileFeignClient> {
    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {
            override fun saveFile(noticeId: String, file: MultipartFile) {
                throw BusinessException("파일을 업로드하는 중 오류가 발생했습니다.", ErrorCode.BAD_GATEWAY_ERROR)
            }

            override fun loadAttachmentList(noticeId: String): List<AttachmentResponse> {
                return ArrayList()
            }

        }
    }


}