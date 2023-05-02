package com.info.info_v2_backend.company.application.port.input.company

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit.EditCompanyRequest

interface EditCompanyUsecase {

    fun editCompany(companyNumber: String, request: EditCompanyRequest)
}
