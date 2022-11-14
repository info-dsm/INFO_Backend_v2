package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyFileDtoUsecase
import com.info.info_v2_backend.company.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.SaveCompanyPort
import org.springframework.stereotype.Service

@Service
class RegisterCompanyFileDto(
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): RegisterCompanyFileDtoUsecase {
    override fun registerCompanyFile(file: RegisterCompanyFileDto) {
        val company = loadCompanyPort.loadCompany(
            file.companyId
        )
        file.companyFileClassificationType
        company.companyIntroduction.changeCompanyIntroductionFile(
            file.fileId,
            file.companyFileClassificationType
        )
        saveCompanyPort.save(company)
    }


}