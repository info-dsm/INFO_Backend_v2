package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyFileRepostiory: JpaRepository<CompanyFile, String> {

    fun findByCompanyId(companyNumber: String): List<CompanyFile>
    fun deleteByCompanyFileClassificationAndCompanyId(classificationType: CompanyFileClassificationType, companyNumber: String)
}