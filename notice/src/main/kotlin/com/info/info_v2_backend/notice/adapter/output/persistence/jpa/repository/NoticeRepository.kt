package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface NoticeRepository: JpaRepository<Notice, String> {

    @Query(value = "select * from notice where notice_is_delete = false and company_number = :companyNumber", nativeQuery = true)
    fun findByCompany(@Param(value = "companyNumber") companyNumber: String): List<Notice>
}