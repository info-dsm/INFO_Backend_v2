package com.info.info_v2_backend.file.application.port.output.company

interface ChangeCompanyStatusPort {

    fun change(companyNumber: String, sequence: Int)
}