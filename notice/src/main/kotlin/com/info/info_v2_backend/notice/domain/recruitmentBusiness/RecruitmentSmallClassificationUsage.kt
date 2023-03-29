package com.info.info_v2_backend.notice.domain.recruitmentBusiness

import com.info.info_v2_backend.notice.domain.Notice
import java.util.UUID
import javax.persistence.*


@Entity
class RecruitmentSmallClassificationUsage(
    notice: Notice,
    smallClassification: RecruitmentSmallClassification
) {

    @Id
    private val id: String = UUID.randomUUID().toString()

    @ManyToOne
    @JoinColumn(name = "notice_id", nullable = false)
    val notice = notice

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "small_classification_id", nullable = false)
    val smallClassification = smallClassification



}