package com.info.info_v2_backend.notice.domain

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.BigClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import com.info.info_v2_backend.notice.domain.support.Pay
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import com.info.info_v2_backend.notice.domain.support.MealSupport
import com.info.info_v2_backend.notice.domain.support.Welfare
import com.info.info_v2_backend.notice.domain.support.WorkTime
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import javax.persistence.*


@SQLDelete(sql = "UPDATE `notice` SET notice_is_delete = true WHERE id = ?")
@Where(clause = "notice_is_delete = false")
@Entity
@Table(name = "notice")
class Notice(
    id: String,
    company: NoticeCompany,

    bigClassification: RecruitmentBigClassification,
    smallClassification: RecruitmentSmallClassification,
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

): TimeEntity() {

    @Id
    @Column(name = "id", nullable = false)
    val id: String = id

    var company: NoticeCompany = company

    @ManyToOne
    @JoinColumn(
        name = "notice_big_classification",
        nullable = false
    )
    var bigClassification: RecruitmentBigClassification = bigClassification
        protected set

    @ManyToOne
    @JoinColumn(
        name = "notice_small_classification",
        nullable = false
    )
    var smallClassification: RecruitmentSmallClassification = smallClassification
        protected set

    @Column(
        name = "detail_business_description",
        nullable = true
    )
    var detailBusinessDescription: String? = detailBusinessDescription
        protected set


    @Column(name = "number_of_emplyee", nullable = false)
    var numberOfEmplyee: Int = numberOfEmployee
        protected set

    @Column(name = "recruitment_business_grade_cut_line", nullable = true)
    var gradeCutLine: Int? = gradeCutLine
        protected set


    @OneToMany(mappedBy = "notice", orphanRemoval = true)
    var languageUsageList: MutableSet<LanguageUsage> = HashSet()
        protected set

    @OneToMany(mappedBy = "notice", orphanRemoval = true)
    var technologyList: MutableSet<TechnologyUsage> = HashSet()
        protected set

    @OneToMany(mappedBy = "notice", orphanRemoval = true, cascade = [CascadeType.REMOVE])
    var needCertificateList: MutableSet<CertificateUsage> = HashSet()
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
    var interviewProcessMap: MutableMap<Int, InterviewProcess> = HashMap()
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

    @Column(name = "notice_is_conclude", nullable = false)
    var isConclude: Boolean = false
        protected set

    constructor(
        id: String,
        company: NoticeCompany,

        bigClassification: RecruitmentBigClassification,
        smallClassification: RecruitmentSmallClassification,
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
        isConclude: Boolean,
        createdAt: LocalDate,
        updatedAt: LocalDate
        ): this(
            id,
            company,
            bigClassification,
            smallClassification,
            detailBusinessDescription,
            numberOfEmployee,
            gradeCutLine,
            workTime,
            pay,
            mealSupport,
            welfare,
            noticeOpenPeriod,
            needDocuments,
            otherFeatures,
            workPlace,
            isPersonalContact,
        ) {

        var isDelete: Boolean = isDelete

        var approveStatus: NoticeWaitingStatus = approveStatus

        var applicantCount: Int = applicantCount

        var isConclude: Boolean = isConclude

        var createdAt: LocalDate = createdAt

        var updatedAt: LocalDate = updatedAt
    }

    fun changeInterviewProcess(interviewProcessMap: MutableMap<Int, InterviewProcess>) {
        this.interviewProcessMap = interviewProcessMap
    }

    fun changeCertificate(certificateList: MutableSet<CertificateUsage>) {
        this.needCertificateList = certificateList
    }

    fun addCount() {
        this.applicantCount++
    }

    fun minusCount() {
        this.applicantCount--
    }

    fun addNeedCertificate(certificate: CertificateUsage) {
        this.needCertificateList.add(certificate)
    }

    fun addLanguage(language: LanguageUsage) {
        this.languageUsageList.add(language)
    }

    fun addTechnology(technology: TechnologyUsage) {
        this.technologyList.add(technology)
    }

    fun conclude(){
        this.isConclude = true
    }

    fun approve(){
        this.approveStatus = NoticeWaitingStatus.APPROVE
    }

    fun checkIsAvailableAppliesStatus(): Boolean {
        if (this.isConclude) return false
        else if (this.isDelete) return false
        else if (this.isPersonalContact) return false
        return true
    }

    fun toNoticeDto(): NoticeDto {
        return NoticeDto(
            this.id,
            this.company.companyNumber
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

    fun toMinimumNoticeResponse(): MinimumNoticeResponse {
        return MinimumNoticeResponse(
            this.id,
            this.company,
            ClassificationResponse(
                BigClassificationResponse(
                    this.bigClassification.name
                ),
                this.smallClassification.name
            ),
            this.detailBusinessDescription,
            this.numberOfEmplyee,
            this.gradeCutLine,
            this.applicantCount,
            this.isPersonalContact
        )

    }

    fun toMaximumNoticeResponse(attachmentResponseList: List<AttachmentResponse>): MaximumNoticeResponse {
        return MaximumNoticeResponse(
            this.id,
            this.company,
            ClassificationResponse(
                BigClassificationResponse(
                    this.bigClassification.name
                ),
                this.smallClassification.name
            ),
            this.detailBusinessDescription,
            this.numberOfEmplyee,
            this.gradeCutLine,
            this.languageUsageList.map {
                LanguageResponse(
                    it.language.name
                )
            }.toSet(),
            this.technologyList.map {
                TechnologyResponse(
                    it.technology.name
                )
            }.toSet(),
            this.needCertificateList.map {
                CertificateResponse(
                    it.certificate.name
                )
            }.toList(),
            this.workTime.toWorkTimeRequest(),
            this.mealSupport.toMealSupportRequest(),
            this.welfare.toWelfare(),
            this.noticeOpenPeriod.toNoticeOpenPeriod(),
            this.interviewProcessMap.map {
                return@map mapOf(Pair(it.key, it.value.meaning))
            },
            this.needDocuments,
            this.otherFeatures,
            this.workPlace.toWorkPlaceRequest(),
            this.applicantCount,
            attachmentResponseList,
            this.isPersonalContact
        )
    }

    fun editNotice(r: EditNoticeRequest) {
        r.detailBusinessDescription?.let {
            this.detailBusinessDescription = it
        }
        r.numberOfEmployee?.let {
            this.numberOfEmplyee = it
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