package com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit


data class EditCompanyRequest (
    val companyName: String?,
    val companyInformationRequest: EditCompanyInformationRequest?,
    val introduction: String?
)