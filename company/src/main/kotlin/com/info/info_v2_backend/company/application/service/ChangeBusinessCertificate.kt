package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.company.application.port.input.ChangeBusinessCertificatePort
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import com.info.info_v2_backend.company.application.port.output.file.FilePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ChangeBusinessCertificate(
    private val filePort: FilePort,
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): ChangeBusinessCertificatePort {
    override fun change(businessCertificate: MultipartFile, companyNumber: String): String {
        val company = loadCompanyPort.loadCompany(companyNumber)
        var fileId = ""
        company.companyIntroduction.businessRegisteredCertificateFileId?.let {
            fileId = filePort.change(
                it,
                companyNumber,
                CompanyFileClassificationType.BUSINESS_CERTIFICATE,
                businessCertificate
            )
        }?:let {
            fileId = filePort.upload(companyNumber, CompanyFileClassificationType.BUSINESS_CERTIFICATE, businessCertificate)
        }
        company.companyIntroduction.changeCompanyIntroductionFile(
            fileId,
            CompanyFileClassificationType.BUSINESS_CERTIFICATE
        )
        saveCompanyPort.save(company)
        return fileId
    }

}