package com.info.info_v2_backend.employment.adapter.output.persistence.repository

import com.info.info_v2_backend.employment.domain.Employment
import com.info.info_v2_backend.employment.domain.company.EmploymentCompany
import com.info.info_v2_backend.employment.domain.notice.EmploymentNotice
import com.info.info_v2_backend.employment.domain.status.EmploymentStatus
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface EmploymentRepository: JpaRepository<Employment, String> {

    fun findByCompanyAndStudent(companyNumber: EmploymentCompany, studentEmail: EmployedStudent): Optional<Employment>
    fun findByCompany(companyNumber: EmploymentCompany): List<Employment>
    fun findByCompanyAndStatus(company: EmploymentCompany, status: EmploymentStatus): List<Employment>

}