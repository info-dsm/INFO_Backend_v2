package com.info.info_v2_backend.employment.adapter.output.persistence

import com.info.info_v2_backend.employment.adapter.output.persistence.repository.EmploymentRepository
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import com.info.info_v2_backend.employment.application.port.output.SaveEmploymentPort
import com.info.info_v2_backend.employment.domain.Employment
import org.springframework.stereotype.Service

@Service
class EmploymentAdapter(
    private val employmentRepository: EmploymentRepository
): SaveEmploymentPort, LoadEmploymentPort {

    override fun loadEmploymentByCompanyAndStudent(companyNumber: String, studentEmail: String): Employment? {
        return employmentRepository.findByCompanyAndStudent(companyNumber, studentEmail).orElse(null)
    }

    override fun loadEmploymentByCompany(companyNumber: String): List<Employment> {
        return employmentRepository.findByCompany(companyNumber)
    }

    override fun saveEmployment(employment: Employment) {
        employmentRepository.save(employment)
    }


}