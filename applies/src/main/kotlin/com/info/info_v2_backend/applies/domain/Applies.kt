package com.info.info_v2_backend.applies.domain

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.domain.company.AppliesCompany
import com.info.info_v2_backend.applies.domain.notice.AppliesNotice
import com.info.info_v2_backend.applies.domain.time.TimeEntity
import com.info.info_v2_backend.applies.domain.user.Applicant
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "applies")
class Applies(
    applicant: Applicant,
    notice: AppliesNotice,
    company: AppliesCompany,
    message: String?
): TimeEntity() {
    @Id
    @Column(name = "id", nullable = false)
    val id: String = UUID.randomUUID().toString()

    @Embedded
    var applicant: Applicant = applicant
        protected set

    @Embedded
    var notice: AppliesNotice = notice
        protected set

    @Embedded
    var company: AppliesCompany = company
        protected set

    @Column(name = "applies_status")
    @Enumerated(value = EnumType.STRING)
    var status: AppliesStatus = AppliesStatus.WAITING
        protected set

    @Column(name = "message")
    var message: String? = message
        protected set

    fun approve() {
        this.status = AppliesStatus.APPROVE
    }

    fun reject(message: String?) {
        this.status = AppliesStatus.REJECT
        this.message = message
    }

    fun toAppliesResponse(fileList: List<FileResponse>): AppliesResponse {
        return AppliesResponse(
            this.id,
            AppliesResponse.ApplierResponse(
                this.applicant.email,
                this.applicant.name
            ),
            AppliesResponse.AppliesNoticeResponse(
                this.notice.noticeId,
                this.notice.classificationList.split(",").map { it.trim() }
            ),
            AppliesResponse.AppliesCompanyResponse(
                this.company.companyName,
                this.company.companyNumber
            ),
            this.status,
            fileList,
            this.message
        )
    }
    fun toAppliesDto(): AppliesDto {
        return AppliesDto(
            this.id,
            this.applicant.toApplicantDto(),
            this.notice.toAppliesNoticeDto(),
            this.status,
            (this.createdAt?.toLocalDate())?: LocalDate.now()
        )
    }
}