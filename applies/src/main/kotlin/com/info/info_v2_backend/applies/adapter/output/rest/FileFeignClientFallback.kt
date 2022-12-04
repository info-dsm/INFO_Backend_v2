package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileFeignClientFallback: FallbackFactory<FileFeignClient> {

    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {
            override fun uploadResume(noticeId: String, studentEmail: String, resume: MultipartFile) {
                throw BusinessException("File을 업로드하던 중 오류가 발생했습니다. -> ${resume.originalFilename}", ErrorCode.BAD_GATEWAY_ERROR)
            }

            override fun loadAppliesResume(noticeId: String, studentEmail: String): FileResponse {
                throw BusinessException(null, ErrorCode.BAD_GATEWAY_ERROR)
            }

        }
    }

}