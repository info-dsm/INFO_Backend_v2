package com.info.info_v2_backend.company.domain.introduction


import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CompanyIntroduction(
    introduction: String,
) {

    var introduction: String = introduction
        protected set

    @Column(name = "company_business_certificate_file_id")
    var businessRegisteredCertificateFileId: String? = null
        protected set

    @Column(name = "company_logo_file_id")
    var companyLogoFileId: String? = null
        protected set

    fun changeIntroduction(introduction: String) {
        this.introduction = introduction
    }

    fun changeCompanyIntroductionFile(
        fileId: String,
        classification: CompanyFileClassificationType
    ) {
        if (classification == CompanyFileClassificationType.BUSINESS_CERTIFICATE) {
            this.businessRegisteredCertificateFileId = fileId
        } else if (classification == CompanyFileClassificationType.COMPANY_LOGO) {
            this.companyLogoFileId = fileId
        }
    }

}