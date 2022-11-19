package com.info.info_v2_backend.employment.adapter.output.persistence.repository

import com.info.info_v2_backend.employment.domain.Employment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface EmploymentRepository: JpaRepository<Employment, String> {

    @Query(value = "select A from Employment as A where A.company.companyNumber = :companyNumber and A.student.studentEmail = :studentEmail")
    fun findByCompanyAndStudent(@Param(value = "companyNumber") companyNumber: String, @Param(value = "studentEmail") studentEmail: String): Optional<Employment>

    @Query(value = "select A from Employment as A where A.company.companyNumber = :companyNumber")
    fun findByCompany(@Param(value = "companyNumber") companyNumber: String): List<Employment>
}