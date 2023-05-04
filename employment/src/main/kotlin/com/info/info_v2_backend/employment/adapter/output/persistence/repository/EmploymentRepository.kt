package com.info.info_v2_backend.employment.adapter.output.persistence.repository

import com.info.info_v2_backend.employment.domain.Employment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface EmploymentRepository: JpaRepository<Employment, String> {

    @Query("select * from employment where employment_company_number = :companyNumber and employed_student_email = :studentEmail", nativeQuery = true)
    fun findByCompanyAndStudent(@Param("companyNumber") companyNumber: String, @Param("studentEmail") studentEmail: String): Optional<Employment>
    @Query("select * from employment where employment_company_number = :companyNumber", nativeQuery = true)
    fun findByCompany(@Param("companyNumber") companyNumber: String): List<Employment>
    @Query("select * from employment where employment_company_number = :companyNumber and employment_status = :status", nativeQuery = true)
    fun findByCompanyAndStatus(@Param("companyNumber") company: String, @Param("status") status: String): List<Employment>
    @Query("select * from employment where generation_class_num = :classNum and created_at >= DATE_FORMAT(MAKEDATE(:year, 1), '%Y-01-01 00:00:00') and created_at < DATE_FORMAT(MAKEDATE(:year + 1, 1) , '%Y-01-01 00:00:00')", nativeQuery = true)
    fun findByClassNumAndYear(@Param("classNum") classNum: Int, @Param("year") year: Int): List<Employment>
}
