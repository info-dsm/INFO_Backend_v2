package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.company.application.port.input.file.RegisterCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import org.springframework.stereotype.Service

@Service
class RegisterCompanyFile(
    private val saveCompanyPort: SaveCompanyPort,
    private val loadCompanyPort: LoadCompanyPort
): RegisterCompanyFileUsecase {

    override fun register(dto: RegisterCompanyFileDto) {
        val company = loadCompanyPort.loadCompany(dto.companyId)
            ?: throw BusinessException("CompanyNumber에 해당하는 회사를 찾지 못했습니다.", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        company.companyIntroduction.changeCompanyIntroductionFile(
            dto.fileId,
            dto.companyFileClassificationType
        )
        saveCompanyPort.save(
            company
        )
    }

}