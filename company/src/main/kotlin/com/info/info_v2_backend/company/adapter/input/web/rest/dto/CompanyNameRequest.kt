package com.info.info_v2_backend.company.adapter.input.web.rest.dto

import com.info.info_v2_backend.company.domain.name.CompanyName

data class CompanyNameRequest (
    val companyNumber: String,
    val companyName: String
) {
    fun toCompanyName(): CompanyName{
        return CompanyName(
            this.companyName,
            this.companyNumber
        )
    }
}