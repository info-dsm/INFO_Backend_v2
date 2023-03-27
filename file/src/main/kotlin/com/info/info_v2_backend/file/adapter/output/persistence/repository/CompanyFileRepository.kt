package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface CompanyFileRepository: JpaRepository<CompanyFile, String> {

    fun findByCompanyNumber(companyNumber: String): List<CompanyFile>

    @Query(nativeQuery = true, value = "select * from file a, company_file b where b.company_number = :companyNumber and a.file_id = b.file_id and company_file_classification in (2, 3)")
    fun findCompanyPhotosByCompanyNumber(@Param(value = "companyNumber") companyNumber: String): List<CompanyFile>
    @Transactional
    @Modifying
    fun deleteByCompanyFileClassificationAndCompanyNumber(classificationType: CompanyFileClassificationType, companyNumber: String)
}