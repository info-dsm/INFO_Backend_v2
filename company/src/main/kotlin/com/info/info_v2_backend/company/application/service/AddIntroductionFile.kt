package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.file.FileConvert
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.company.application.port.input.file.AddCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AddIntroductionFile(
    private val companyFilePort: CompanyFilePort,
    private val loadCompanyPort: LoadCompanyPort
): AddCompanyFileUsecase {
    override fun add(file: MultipartFile, companyNumber: String, classificationType: CompanyFileClassificationType) {
        val company = loadCompanyPort.loadCompany(companyNumber)
        companyFilePort.upload(
            companyNumber,
            classificationType,
            FileConvert.fileToMultipartFileConvert(
                FileConvert.multipartFileToFileConvert(file, "company/src/main/resources/tmp")
            )
        )
    }

}