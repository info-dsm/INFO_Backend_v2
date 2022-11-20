package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.auth.HeaderProperty
import com.info.info_v2_backend.file.application.port.input.company.RemoveCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.output.company.LoadCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.RemoveFilePort
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Service
class RemoveCompanyFile(
    private val loadCompanyFilePort: LoadCompanyFilePort,
    private val removeFilePort: RemoveFilePort
): RemoveCompanyFileUsecase {

    override fun remove(companyNumber: String, fileId: String, classificationType: CompanyFileClassificationType) {
        val headerCompany = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(
            HeaderProperty.COMPANY_NUMBER)
        val file = loadCompanyFilePort.load(fileId)?: throw BusinessException("파일을 찾지 못했습니다. -> $fileId", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (companyNumber != headerCompany || file.companyNumber != companyNumber)
            throw BusinessException("소유자가 다릅니다. -> $companyNumber", ErrorCode.INVALID_INPUT_DATA_ERROR)

        removeFilePort.remove(
            ,
        )
    }


}