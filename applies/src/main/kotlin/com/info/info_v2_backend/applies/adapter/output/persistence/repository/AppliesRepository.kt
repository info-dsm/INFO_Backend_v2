package com.info.info_v2_backend.applies.adapter.output.persistence.repository

import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.applies.domain.status.AppliesStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface AppliesRepository: JpaRepository<Applies, String> {
    @Query(value = "delete from Applies where notice.noticeId = :noticeId and applicant.email = :userEmail")
    fun deleteByNoticeAndApplicant(noticeId: String, userEmail: String): Optional<Applies>

    @Query(value = "select A from Applies as A where A.notice.noticeId = :noticeId and A.status = :status")
    fun findByNoticeAndStatus(@Param(value = "noticeId")noticeId: String, @Param(value = "status") status: AppliesStatus): List<Applies>

    @Query(value = "select A from Applies as A where A.notice.noticeId = :noticeId and A.applicant.email = :studentEmail")
    fun findByNoticeAndApplicant(@Param(value = "noticeId") noticeId: String, @Param(value = "studentEmail") studentEmail: String): Optional<Applies>
}