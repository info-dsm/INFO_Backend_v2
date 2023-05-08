package com.info.info_v2_backend.company.application.port.input.company

interface MakeLeadingUsecase {

    fun makeLeading(companyNumber: String)
    fun cancelLeading(companyNumber: String)
}
