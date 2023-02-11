package com.info.info_v2_backend.notice.adapter.output.persistence.jdbc.mapper.vo

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import com.info.info_v2_backend.notice.domain.support.MealSupport
import com.info.info_v2_backend.notice.domain.support.Pay
import com.info.info_v2_backend.notice.domain.support.Welfare
import com.info.info_v2_backend.notice.domain.support.WorkTime
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.OneToMany

class NoticeVo(
    id: String,
    company: NoticeCompany,
    detailBusinessDescription: String?,
    numberOfEmployee: Int,
    gradeCutLine: Int?,

    workTime: WorkTime,
    pay: Pay,

    mealSupport: MealSupport,
    welfare: Welfare,

    noticeOpenPeriod: NoticeOpenPeriod,

    needDocuments: String?,

    otherFeatures: String?,
    workPlace: WorkPlace,
    isPersonalContact: Boolean,
    isDelete: Boolean,
    approveStatus: NoticeWaitingStatus,
    applicantCount: Int,
    createdAt: LocalDate,
    updatedAt: LocalDate,
) {
    val id = id

    val company = company

    val detailBusinessDescription = detailBusinessDescription

    val numberOfEmployee = numberOfEmployee

    val gradeCutLine = gradeCutLine

    val workTime = workTime

    val pay = pay

    val mealSupport = mealSupport

    val welfare = welfare

    val noticeOpenPeriod: NoticeOpenPeriod = noticeOpenPeriod

    val noticeWaitingStatus = noticeOpenPeriod

    val needDocuments = needDocuments

    val otherFeatures = otherFeatures

    val workPlace = workPlace

    val isPersonalContact = isPersonalContact

    val isDelete = isDelete

    val approveStatus = approveStatus

    val applicantCount = applicantCount

    val createdAt = createdAt

    val updatedAt = updatedAt

    fun toMinimumNoticeResponse(
        classificationResponseList: MutableList<ClassificationResponse>,
        thumbnailList: MutableList<String>
    ): MinimumNoticeResponse {
        return MinimumNoticeResponse(
            this.id,
            MinimumNoticeResponse.MinimumNoticeCompany(
                this.company.companyNumber,
                this.company.companyName,
                thumbnailList
            ),
            classificationResponseList,
            this.detailBusinessDescription,
            this.numberOfEmployee,
            this.gradeCutLine,
            this.applicantCount,
            this.isPersonalContact,
            this.noticeOpenPeriod,
            WorkPlaceRequest(
                this.workPlace.isSameWithCompanyAddress,
                this.otherFeatures
            )
        )

    }


    fun toMaximumNoticeResponse(
        classificationResponseList: MutableList<ClassificationResponse>,
        interviewProcessMap: MutableMap<Int, InterviewProcess>,
        languageList: MutableList<LanguageResponse>,
        technologyList: MutableList<TechnologyResponse>,
        needCertificateList: MutableList<CertificateResponse>,
        attachmentList: MutableList<AttachmentResponse>
    ): MaximumNoticeResponse {
        return MaximumNoticeResponse(
            this.id,
            this.company,
            classificationResponseList,
            this.detailBusinessDescription,
            this.numberOfEmployee,
            this.gradeCutLine,
            interviewProcessMap.mapValues { it.value.meaning },
            languageList,
            technologyList,
            needCertificateList,
            this.workTime.toWorkTimeRequest(),
            this.mealSupport.toMealSupportRequest(),
            this.welfare.toWelfare(),
            this.noticeOpenPeriod.toNoticeOpenPeriod(),
            this.needDocuments,
            this.otherFeatures,
            this.workPlace.toWorkPlaceRequest(),
            this.applicantCount,
            attachmentList,
            this.isPersonalContact
        )
    }

}