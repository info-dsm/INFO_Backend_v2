package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileFeignClientFallbackFactory: FallbackFactory<FileFeignClient> {
    override fun create(cause: Throwable): FileFeignClient {
        return object : FileFeignClient {
            override fun upload(
                companyId: String,
                classification: CompanyFileClassificationType,
                file: MultipartFile
            ) {
                throw BusinessException("File Upload 중 오류가 발생했습니다.", ErrorCode.BAD_GATEWAY_ERROR)
            }


            override fun remove(companyNumber: String, fileId: String, classificationType: CompanyFileClassificationType) {
                throw BusinessException(cause.message, ErrorCode.BAD_GATEWAY_ERROR)
            }

            override fun loadCompanyFile(companyNumber: String): List<CompanyFileResponse> {
                TODO("Not yet implemented")
            }

        }
    }
}