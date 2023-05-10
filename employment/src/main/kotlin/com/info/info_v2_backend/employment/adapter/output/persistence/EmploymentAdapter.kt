package com.info.info_v2_backend.employment.adapter.output.persistence

import com.info.info_v2_backend.common.employment.EmploymentStatus
import com.info.info_v2_backend.employment.adapter.output.persistence.repository.EmploymentRepository
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import com.info.info_v2_backend.employment.application.port.output.SaveEmploymentPort
import com.info.info_v2_backend.employment.domain.Employment
import org.springframework.stereotype.Service

@Service
class EmploymentAdapter(
    private val employmentRepository: EmploymentRepository
): SaveEmploymentPort, LoadEmploymentPort {
    override fun loadEmploymentByClassNumAndYear(classNum: Int, year: Int): List<Employment> {
        return employmentRepository.findByClassNumAndYear(classNum, year)
    }

    override fun loadEmploymentByCompanyAndStudent(companyNumber: String, studentEmail: String): Employment? {
        return employmentRepository.findByCompanyAndStudent(companyNumber, studentEmail).orElse(null)
    }

    override fun loadEmploymentByCompany(companyNumber: String): List<Employment> {
        return employmentRepository.findByCompany(companyNumber)
    }

    override fun loadConfirmedEmploymentByCompany(companyNumber: String): List<Employment> {
        return employmentRepository.findByCompanyAndStatus(companyNumber, EmploymentStatus.RECRUITMENT_CONFIRMED.name)
    }

    override fun saveEmployment(employment: Employment) {
        employmentRepository.save(employment)
    }


}
