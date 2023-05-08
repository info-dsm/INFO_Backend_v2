package com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository

import com.info.info_v2_backend.company.domain.interview.InterviewReview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface InterviewReviewRepository: JpaRepository<InterviewReview, Long> {

    @Query(nativeQuery = true, value = "select * from interview_review where company_number = :companyNumber")
    fun findByCompanyNumber(@Param("companyNumber") companyNumber: String): List<InterviewReview>

}
