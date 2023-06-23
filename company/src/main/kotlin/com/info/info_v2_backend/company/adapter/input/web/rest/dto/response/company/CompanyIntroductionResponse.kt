package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.company

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse


data class CompanyIntroductionResponse(
    var introduction: String,
    var businessCertificate: CompanyFileResponse,
    var companyIntroductionFile: MutableList<CompanyFileResponse>,
    var companyLogo: CompanyFileResponse,
    var companyPhotoList: MutableList<CompanyFileResponse>

) {

    fun changeBusinessCertificate(companyFile: CompanyFileResponse) {
        this.businessCertificate = companyFile
    }

    fun addCompanyIntroductionFile(companyFile: CompanyFileResponse) {
        this.companyIntroductionFile.add(companyFile)
    }

    fun changeCompanyLogo(companyFile: CompanyFileResponse) {
        this.companyLogo = companyFile
    }

    fun addCompanyPhoto(companyFile: CompanyFileResponse) {
        this.companyPhotoList.add(companyFile)
    }

    fun addFile(fileResponse: CompanyFileResponse) {
        when (fileResponse.companyFileClassificationType) {
            CompanyFileClassificationType.BUSINESS_CERTIFICATE -> this.businessCertificate = fileResponse
            CompanyFileClassificationType.COMPANY_INTRODUCTION -> this.companyIntroductionFile.add(fileResponse)
            CompanyFileClassificationType.COMPANY_LOGO -> this.companyLogo = fileResponse
            CompanyFileClassificationType.COMPANY_PHOTO -> this.companyPhotoList.add(fileResponse)
        }
    }
}

