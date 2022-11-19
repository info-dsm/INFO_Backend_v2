package com.info.info_v2_backend.employment.application.port.input

interface FailEmploymentUsecase {

    fun failEmployment(companyNumber: String, studentEmail: String)
}