package com.info.info_v2_backend.applies.adapter.output.persistence.repository

import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface AppliesRepository: JpaRepository<Applies, String> {

    @Query(nativeQuery = true, value = "select * from applies where applies_notice_id = :noticeId and applies_status = :status")
    fun findByNoticeAndStatus(@Param(value = "noticeId") noticeId: String, @Param(value = "status") status: String): List<Applies>

    @Query(nativeQuery = true, value = "select * from applies where applies_notice_id = :noticeId")
    fun findByNotice(@Param(value = "noticeId") noticeId: String): List<Applies>

    @Query(nativeQuery = true, value = "select * from applies where id = :id limit 1")
    override fun findById(@Param(value = "id") id: String): Optional<Applies>

    @Modifying
    @Query(nativeQuery = true, value = "delete from applies where applies_notice_id = :noticeId and applicant_email = :applicantEmail")
    fun deleteByIdAndAndApplicant(@Param(value = "noticeId") noticeId: String, @Param(value = "applicantEmail") applicantEmail: String)

    @Query(
        nativeQuery = true,
        value = "select * from applies where applies_status = :status order by created_at desc",
        countQuery = "select count(*) from applies where applies_status = :status"
    )
    fun findByStatus(@Param(value = "status") status: String, pageable: Pageable): Page<Applies>

    @Query(nativeQuery = true, value = "select * from applies where applicant_email = :studentEmail")
    fun findByApplicant(@Param(value = "studentEmail") studentEmail: String): List<Applies>

    @Query(nativeQuery = true, value = "select * from applies where applicant_email = :studentEmail and applies_notice_id = :noticeId")
    fun findByNoticeAndApplicant(@Param(value = "noticeId") noticeId: String, @Param(value = "studentEmail") studentEmail: String): Optional<Applies>

}