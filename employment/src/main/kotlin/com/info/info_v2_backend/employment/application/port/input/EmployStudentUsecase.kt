package com.info.info_v2_backend.employment.application.port.input

interface EmployStudentUsecase {

    fun employ(studentEmail: String, companyNumber: String)
}
