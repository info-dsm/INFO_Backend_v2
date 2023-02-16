package com.info.info_v2_backend.company.domain.introduction


import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.UploadCompanyFileDto
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class CompanyIntroduction(
    introduction: String,
) {

    @Column(length = 1000)
    var introduction: String = introduction
        protected set


    fun changeIntroduction(introduction: String) {
        this.introduction = introduction
    }

}