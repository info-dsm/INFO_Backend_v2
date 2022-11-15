package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.company.application.port.input.introduction.AddIntroductionFileUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.file.FilePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AddIntroductionFile(
    private val uploadFilePort: FilePort,
    private val loadCompanyPort: LoadCompanyPort
): AddIntroductionFileUsecase {
    override fun add(file: MultipartFile, companyNumber: String): String {
        val company = loadCompanyPort.loadCompany(companyNumber)
        return uploadFilePort.upload(
            companyNumber,
            CompanyFileClassificationType.COMPANY_INTRODUCTION,
            file
        )
    }
}