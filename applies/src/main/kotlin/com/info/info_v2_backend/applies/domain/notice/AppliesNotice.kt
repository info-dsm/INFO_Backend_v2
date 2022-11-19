package com.info.info_v2_backend.applies.domain.notice

import com.info.info_v2_backend.common.applies.AppliesDto
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AppliesNotice(
    noticeId: String
) {
    @Column(name = "applies_notice_id", nullable = false)
    var noticeId: String = noticeId
        protected set

    fun toAppliesNoticeDto(): AppliesDto.AppliesNoticeDto {
        return AppliesDto.AppliesNoticeDto(
            this.noticeId
        )
    }
}