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
    fun findByCompanyNumber(@Param(value = "companyNumber") companyNumber: String): List<Notice>

    @Query(value = "select * from notice n " +
            "where n.company_name like %:companyName% " +
            "and n.notice_is_delete = false " +
            "and curdate() between n.start_date and n.end_date " +
            "and n.notice_is_approve = 'APPROVE'",
        countQuery = "select count(*) from notice n " +
                "where n.company_name like %:companyName% " +
                "and n.notice_is_delete = false " +
                "and curdate() between n.start_date and n.end_date " +
                "and n.notice_is_approve = 'APPROVE'",
        nativeQuery = true
    )
    fun findByCompanyName(@Param(value = "companyName") companyName: String, pageable: Pageable): Page<Notice>

    @Query(value = "select n.* from notice n, recruitment_small_classification_usage b " +
            "where n.company_name like %:companyName% " +
            "and n.notice_is_delete = false " +
            "and curdate() between n.start_date and n.end_date " +
            "and n.notice_is_approve = 'APPROVE' " +
            "and n.id = b.notice_id " +
            "and b.small_classification_id = :smallClassification " +
            "order by n.created_at desc",
        countQuery = "select count(n.*) from notice n, recruitment_small_classification_usage b " +
                "where n.company_name like %:companyName% " +
                "and n.notice_is_delete = false " +
                "and curdate() between n.start_date and n.end_date " +
                "and n.notice_is_approve = 'APPROVE' " +
                "and n.id = b.notice_id " +
                "and b.small_classification_id = :smallClassification " +
                "order by n.created_at desc",
        nativeQuery = true
    )
    fun findByCompanyNameAndSmallClassification(
        @Param(value = "companyName") companyName: String,
        @Param(value = "smallClassification") smallClassification: String,
        pageable: Pageable
    ): Page<Notice>

    @Query(value = "select count(*) from notice where curdate() between start_date and end_date and notice_is_approve = 'APPROVE' and notice_is_delete = 'false'", nativeQuery = true)
    fun countOpenNotice(): Int

    @Query(value = "select a.* " +
            "from notice a, recruitment_small_classification_usage b " +
            "where b.notice_id = a.id " +
            "and curdate() between a.start_date and a.end_date " +
            "and b.small_classification_id = :smallClassification " +
            "and notice_is_approve = 'APPROVE' " +
            "and notice_is_delete = false order by a.created_at desc",
        countQuery = "select count(*) " +
                "from notice a where notice_is_approve = 'APPROVE' " +
                "and curdate() between a.start_date and a.end_date " +
                "and notice_is_delete = false", nativeQuery = true)
    fun findBySmallClassification(
        @Param(value = "smallClassification")
        smallClassification: String,
        pageable: Pageable
    ): Page<Notice>

}
