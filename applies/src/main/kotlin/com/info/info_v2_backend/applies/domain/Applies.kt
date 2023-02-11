package com.info.info_v2_backend.applies.domain

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
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
    notice: AppliesNotice
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

    @Column(name = "applies_status")
    @Enumerated(value = EnumType.STRING)
    var status: AppliesStatus = AppliesStatus.WAITING
        protected set


    fun approve() {
        this.status = AppliesStatus.APPROVE
    }

    fun reject() {
        this.status = AppliesStatus.REJECT
    }

    fun toAppliesResponse(file: FileResponse): AppliesResponse {
        return AppliesResponse(
            this.id,
            AppliesResponse.ApplierResponse(
                this.applicant.email,
                this.applicant.name
            ),
            this.notice.noticeId,
            this.status,
            file
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