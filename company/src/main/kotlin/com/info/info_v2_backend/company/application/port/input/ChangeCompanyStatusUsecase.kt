package com.info.info_v2_backend.company.application.port.input

interface ChangeCompanyStatusUsecase {

    fun change(companyNumber: String, sequence: Int)
}