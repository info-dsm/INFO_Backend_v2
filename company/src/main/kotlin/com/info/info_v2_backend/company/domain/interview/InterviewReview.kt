package com.info.info_v2_backend.company.domain.interview

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.interviewReview.WriteInterviewReviewRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview.MaximumInterviewReviewResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview.MinimumInterviewReviewRespone
import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.user.domain.time.TimeEntity
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "interview_review")
class InterviewReview(
    student: InterviewReviewStudent,
    company: Company,
    appliedJobPart: String,
    interviewDate: LocalDate?,
    directorCount: Int?,
    interviewPlace: String?,
    interviewType: InterviewType,
    questionContent: String,
    interviewImpression: String,
    interviewCaution: String?
): TimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private val id: Long? = null

    @Embedded
    val student: InterviewReviewStudent = student

    @ManyToOne
    @JoinColumn(name = "company_number", nullable = false)
    val company: Company = company

    @Column(name = "applied_job_part", nullable = false, length = 50)
    var appliedJobPart: String = appliedJobPart
        protected set

    @Column(name = "interview_date", nullable = true, length = 10)
    var interviewDate: LocalDate? = interviewDate
        protected set

    @Column(name = "interview_director_count", nullable = true)
    var directorCount: Int? = directorCount
        protected set

    @Column(name = "interview_place", nullable = true, length = 30)
    var interviewPlace: String? = interviewPlace
        protected set

    @Column(name = "interview_type", nullable = false)
    @Enumerated(EnumType.STRING)
    var interviewType: InterviewType = interviewType
        protected set

    @Column(name = "question_content", nullable = false, length = 2000)
    var questionContent: String = questionContent
        protected set

    @Column(name = "interview_impression", nullable = false, length = 500)
    var interviewImpression: String = interviewImpression
        protected set

    @Column(name = "interview_caution", nullable = true, length = 500)
    var interviewCaution: String? = interviewCaution
        protected set


    fun edit(request: WriteInterviewReviewRequest) {
        this.appliedJobPart = request.appliedJobPart
        this.interviewDate = request.interviewDate
        this.directorCount = request.directorCount
        this.interviewPlace = request.interviewPlace
        this.interviewType = request.interviewType
        this.questionContent = request.interviewImpression
        this.interviewCaution = request.interviewCaution
    }

    fun toMaximumInterviewReview(): MaximumInterviewReviewResponse {
        return MaximumInterviewReviewResponse(
            this.id!!,
            this.student,
            this.company.companyNumber,
            this.appliedJobPart,
            this.interviewDate,
            this.directorCount,
            this.interviewPlace,
            this.questionContent,
            this.interviewImpression,
            this.interviewCaution,
            this.createdAt?.toLocalDate()?:LocalDate.now()
        )
    }

    fun toMinimumInterviewReview(): MinimumInterviewReviewRespone {
        return MinimumInterviewReviewRespone(
            this.id!!,
            this.company.companyNumber,
            this.student,
            this.interviewDate,
            this.createdAt?.toLocalDate()?:LocalDate.now()
        )
    }


}
