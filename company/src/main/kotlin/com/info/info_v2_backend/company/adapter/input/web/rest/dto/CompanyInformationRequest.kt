package com.info.info_v2_backend.company.adapter.input.web.rest.dto

import com.info.info_v2_backend.company.domain.information.AddressInfo
import com.info.info_v2_backend.company.domain.information.CompanyInformation
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class CompanyInformationRequest (
    val homeAddress: AddressInfo,
    val agentAddress: AddressInfo?,
    val representativeName: String,
    val establishedAt: Int,
    @field:Max(100000)
    val workerCount: Int,
    @field:Min(0) @field:Max(1000000000000)
    val annualSales: Long
) {

    fun toCompanyInformation(): CompanyInformation {
        return CompanyInformation(
            this.homeAddress,
            this.agentAddress,
            this.representativeName,
            this.establishedAt,
            this.workerCount,
            this.annualSales
        )
    }

}