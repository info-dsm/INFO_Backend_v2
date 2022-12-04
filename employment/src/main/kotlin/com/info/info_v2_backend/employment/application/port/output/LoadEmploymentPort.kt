package com.info.info_v2_backend.employment.application.port.output

import com.info.info_v2_backend.employment.domain.Employment

interface LoadEmploymentPort {

    fun loadEmploymentByCompanyAndStudent(companyNumber: String, studentEmail: String): Employment?
    fun loadEmploymentByCompany(companyNumber: String): List<Employment>
    fun loadConfirmedEmploymentByCompany(companyNumber: String): List<Employment>
}