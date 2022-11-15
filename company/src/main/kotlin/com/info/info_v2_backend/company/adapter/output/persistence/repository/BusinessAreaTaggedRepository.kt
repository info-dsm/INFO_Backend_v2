package com.info.info_v2_backend.company.adapter.output.persistence.repository

import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTaggedIdClass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BusinessAreaTaggedRepository: JpaRepository<BusinessAreaTagged, BusinessAreaTaggedIdClass> {

    @Query(value = "select A from BusinessAreaTagged as A, BusinessArea as B where A.company.companyNumber = B.id and A.company.companyNumber = :companyNumber")
    fun selectAllByCompanyNumberJPQL(@Param(value = "companyNumber") companyNumber: String): List<BusinessAreaTagged>
}