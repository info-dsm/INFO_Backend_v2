package com.info.info_v2_backend.notice.domain

import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.*
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.BigClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import com.info.info_v2_backend.notice.domain.support.Pay
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import com.info.info_v2_backend.notice.domain.support.MealSupport
import com.info.info_v2_backend.notice.domain.support.Welfare
import com.info.info_v2_backend.notice.domain.support.WorkTime
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import com.info.info_v2_backend.notice.domain.time.TimeEntity
import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import javax.persistence.*


@SQLDelete(sql = "UPDATE `notice` SET notice_is_delete = true WHERE id = ?")
@Where(clause = "notice_is_delete = false")
@Entity
@Table(name = "notice")
class Notice(
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
    interviewProcessMap: MutableMap<Int, InterviewProcess>

): TimeEntity() {

    @Id
    @Column(name = "id", nullable = false)
    val id: String = id

    @Embedded
    var company: NoticeCompany = company

    @OneToMany(mappedBy = "notice")
    var smallClassificationUsageList: MutableList<RecruitmentSmallClassificationUsage> = ArrayList()
        protected set

    @Column(
        name = "detail_business_description",
        nullable = true
    )
    var detailBusinessDescription: String? = detailBusinessDescription
        protected set


    @Column(name = "number_of_emplyee", nullable = false)
    var numberOfEmployee: Int = numberOfEmployee
        protected set

    @Column(name = "recruitment_business_grade_cut_line", nullable = true)
    var gradeCutLine: Int? = gradeCutLine
        protected set


    @OneToMany(mappedBy = "notice", orphanRemoval = true)
    var languageUsage: MutableList<LanguageUsage> = ArrayList()
        protected set

    @OneToMany(mappedBy = "notice", orphanRemoval = true)
    var technologyUsage: MutableList<TechnologyUsage> = ArrayList()
        protected set

    @OneToMany(mappedBy = "notice", orphanRemoval = true, cascade = [CascadeType.REMOVE])
    var needCertificateUsage: MutableList<CertificateUsage> = ArrayList()
        protected set

    @Embedded
    var workTime: WorkTime = workTime
        protected set

    @Embedded
    var pay: Pay = pay
        protected set

    @Embedded
    var mealSupport: MealSupport = mealSupport
        protected set

    @Embedded
    var welfare: Welfare = welfare
        protected set

    @Embedded
    var noticeOpenPeriod: NoticeOpenPeriod = noticeOpenPeriod
        protected set


    @ElementCollection
    @MapKeyColumn(name = "interview_process_sequence")
    var interviewProcessMap: MutableMap<Int, InterviewProcess> = interviewProcessMap
        protected set

    @Column(name = "need_documents", nullable = true)
    var needDocuments: String? = needDocuments
        protected set


    @Column(name = "notice_other_features", nullable = true)
    var otherFeatures: String? = otherFeatures
        protected set

    @Embedded
    var workPlace: WorkPlace = workPlace

    @Column(name = "is_personal_contact", nullable = false)
    var isPersonalContact: Boolean = isPersonalContact

    @Column(name = "notice_is_delete", nullable = false)
    var isDelete: Boolean = false
        protected set

    @Column(name = "notice_is_approve", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var approveStatus: NoticeWaitingStatus = NoticeWaitingStatus.WAITING
        protected set

    @Column(name = "applicant_count")
    var applicantCount: Int = 0
        protected set


    fun changeSmallClassification(smallClassificationUsageList: MutableList<RecruitmentSmallClassificationUsage>) {
        this.smallClassificationUsageList = smallClassificationUsageList
    }

    fun changeInterviewProcess(interviewProcessMap: MutableMap<Int, InterviewProcess>) {
        this.interviewProcessMap = interviewProcessMap
    }

    fun updateCount(count: Int) {
        this.applicantCount += count
    }


    fun conclude(){
        this.noticeOpenPeriod = NoticeOpenPeriod(
            this.noticeOpenPeriod.startDate,
            LocalDate.now().minusDays(1)
        )
    }

    fun approve(){
        this.approveStatus = NoticeWaitingStatus.APPROVE
    }

    fun checkIsAvailableAppliesStatus(): Boolean {
        if (this.noticeOpenPeriod.endDate.isBefore(LocalDate.now())
            || this.noticeOpenPeriod.startDate.isAfter(LocalDate.now())) return false
        else if (this.isDelete) return false
        else if (this.isPersonalContact) return false
        return true
    }

    fun toNoticeDto(): NoticeDto {
        return NoticeDto(
            this.id,
            this.company.companyNumber,
            this.smallClassificationUsageList.map {
                it.smallClassification.name
            }.joinToString(",")
        )
    }
//
//    fun addAttachment(formAttachment: FormAttachment) {
//        this.formAttachmentList.add(formAttachment)
//    }
//
//    fun addRecruitmentBusiness(recruitmentBusiness: RecruitmentBusiness) {
//        this.recruitmentBusinessList.add(recruitmentBusiness)
//    }
//
//    fun addInterviewProcessAll(interviewProcessList: List<InterviewProcess>) {
//        interviewProcessList.map {
//            this.interviewProcessList.add(
//                InterviewProcessUsage(
//                    interviewProcessList.indexOf(it) + 1,
//                    it
//                )
//            )
//        }
//
//    }

//    fun changeInterviewProcess(key: Int, interviewProcess: InterviewProcess) {
//        this.interviewProcessList.firstOrNull { it.sequence == key }
//            ?.changeInterviewProcess(interviewProcess)
//            ?:this.interviewProcessList.add(
//                InterviewProcessUsage(
//                    this.interviewProcessList.size + 1,
//                    interviewProcess
//                )
//            )
//    }


    fun approveNotice() {
        this.approveStatus = NoticeWaitingStatus.APPROVE
    }

    fun toMinimumNoticeWithApproveStatusResponse(imageList: MutableList<String>): MinimumNoticeWithApproveStatusResponse {
        return MinimumNoticeWithApproveStatusResponse(
            this.toMinimumNoticeResponse(imageList),
            this.approveStatus
        )
    }

    fun toMinimumNoticeResponse(thumbnail: MutableList<String>): MinimumNoticeResponse {
        return MinimumNoticeResponse(
            this.id,
            MinimumNoticeResponse.MinimumNoticeCompany(
                this.company.companyNumber,
                this.company.companyName,
                thumbnail
            ),
            this.smallClassificationUsageList.map {
                smallClassification: RecruitmentSmallClassificationUsage ->
                ClassificationResponse(
                    BigClassificationResponse(
                        smallClassification.smallClassification.bigClassification.name
                    ),
                    smallClassification.smallClassification.name
                )
            },
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

    fun toAdminMaximumNoticeResponse(): AdminMaximumNoticeResponse {
        return AdminMaximumNoticeResponse(
            toMaximumNoticeResponse(),
            this.pay.toPayRequest()
        )
    }

    fun toMaximumNoticeResponse(): MaximumNoticeResponse {
        return MaximumNoticeResponse(
            this.id,
            this.company,
            this.smallClassificationUsageList.map {
                    smallClassification: RecruitmentSmallClassificationUsage ->
                ClassificationResponse(
                    BigClassificationResponse(
                        smallClassification.smallClassification.bigClassification.name
                    ),
                    smallClassification.smallClassification.name
                )
            },
            this.detailBusinessDescription,
            this.numberOfEmployee,
            this.gradeCutLine,
            this.interviewProcessMap.mapValues { it.value.meaning }.toMutableMap(),
            this.languageUsage.map {
                LanguageResponse(
                    it.language.name
                )
            },
            this.technologyUsage.map {
                TechnologyResponse(
                    it.technology.name
                )
            },
            this.needCertificateUsage.map {
                CertificateResponse(
                    it.certificate.name
                )
            }.toList(),
            this.workTime.toWorkTimeRequest(),
            this.mealSupport.toMealSupportRequest(),
            this.welfare.toWelfare(),
            this.noticeOpenPeriod.toNoticeOpenPeriod(),
            this.needDocuments,
            this.otherFeatures,
            this.workPlace.toWorkPlaceRequest(),
            this.applicantCount,
            this.isPersonalContact,
            ArrayList()
        )
    }

    fun editNotice(r: EditNoticeRequest) {
        r.detailBusinessDescription?.let {
            this.detailBusinessDescription = it
        }
        r.numberOfEmployee?.let {
            this.numberOfEmployee = it
        }
        r.gradeCutLine?.let {
            this.gradeCutLine = it
        }
        r.workTime?.let {
            this.workTime.editWorkTime(r.workTime)
        }
        r.pay?.let {
            this.pay
        }
        r.mealSupport?.let {
            this.mealSupport.editMealSupport(it)
        }
        r.welfare?.let {
            this.welfare.editWelfare(r.welfare)
        }
        r.needDocuments?.let {
            this.needDocuments = r.needDocuments
        }
        r.otherFeatures?.let {
            this.otherFeatures = r.otherFeatures
        }
        r.workPlace?.let {
            this.workPlace.editWorkPlace(r.workPlace)
        }
        r.isPersonalContact?.let {
            this.isPersonalContact = r.isPersonalContact
        }
    }

//    fun toNoticeWithApproveStatusResponse(): NoticeWithApproveStatusResponse {
//        return NoticeWithApproveStatusResponse(
//            this.toMaximumNoticeWithPayResponse(),
//            this.approveStatus
//        )
//    }
//
//    fun toMaximumNoticeWithPayResponse(): MaximumNoticeWithPayResponse {
//        return MaximumNoticeWithPayResponse(
//            this.toMaximumNoticeWithoutPayResponse(),
//            this.pay.toPayRequest()
//        )
//    }

}