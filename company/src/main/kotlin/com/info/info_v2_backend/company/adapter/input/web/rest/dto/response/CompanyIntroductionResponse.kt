package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response

import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse


data class CompanyIntroductionResponse(
    var introduction: String,
    var businessCertificate: CompanyFileResponse?,
    var companyIntroductionFile: MutableList<CompanyFileResponse>,
    var companyLogo: CompanyFileResponse?,
    var companyPhotoList: MutableList<CompanyFileResponse>

) {
    constructor(introduction: String) : this(introduction, null, ArrayList(), null, ArrayList())

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
}

