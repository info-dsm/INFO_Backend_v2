package com.info.info_v2_backend.applies.domain

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.domain.notice.AppliesNotice
import com.info.info_v2_backend.applies.domain.user.Applicant
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Applies(
    applicant: Applicant,
    notice: AppliesNotice
): TimeEntity() {
    @Id
    val id: String = UUID.randomUUID().toString()

    var applicant: Applicant = applicant
        protected set

    @Column(name = "applies_notice_id", nullable = false)
    var notice: AppliesNotice = notice
        protected set

    var status: AppliesStatus = AppliesStatus.WAITING
        protected set

    fun approve() {
        this.status = AppliesStatus.APPROVE
    }

    fun reject() {
        this.status = AppliesStatus.REJECT
    }

    fun toAppliesResponse(): AppliesResponse {
        return AppliesResponse(
            this.id,
            AppliesResponse.ApplierResponse(
                this.applicant.email,
                this.applicant.name
            ),
            this.notice.noticeId,
            this.status
        )
    }
    fun toAppliesDto(): AppliesDto {
        return AppliesDto(
            this.id,
            this.applicant.toApplicantDto(),
            this.notice.toAppliesNoticeDto(),
            this.status
        )
    }
}