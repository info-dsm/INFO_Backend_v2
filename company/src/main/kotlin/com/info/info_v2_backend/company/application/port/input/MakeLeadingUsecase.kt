package com.info.info_v2_backend.company.application.port.input

interface MakeLeadingUsecase {

    fun makeLeading(companyNumber: String)
    fun cancelLeading(companyNumber: String)
}