package com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit

import com.info.info_v2_backend.company.domain.information.AddressInfo
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern

data class EditCompanyInformationRequest(
    val homeAddress: AddressInfo?,
    val agentAddress: AddressInfo?,
    val representativeName: String?,
    val establishedAt: Int?,
    @field:Max(100000)
    val workerCount: Int?,
    @field:Min(0) @field:Max(1000000000000)
    val annualSales: Long?,
    @field:Pattern(
        regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$",
        message = "전화번호는 반드시 02 or 0xx-xxxx-xxxx 조합이여야합니다."
    )
    val companyPhone: String?
)
