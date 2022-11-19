package com.info.info_v2_backend.employment.application.port.input

interface ConfirmEmploymentUsecase {

    fun confirmEmployment(companyNumber: String, studentEmail: String)
}