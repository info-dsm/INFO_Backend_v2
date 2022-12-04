package com.info.info_v2_backend.notice.domain.recruitmentBusiness

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.BigClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.domain.Notice
import javax.persistence.*

@Entity
@Table(name = "recruitment_small_classification")
class RecruitmentSmallClassification(
    name: String,
    bigClassification: RecruitmentBigClassification
) {

    @Id
    @Column(name = "notice_small_classification_name")
    val name: String = name


    @OneToMany(mappedBy = "smallClassification")
    val smallClassificationUsageList: MutableList<RecruitmentSmallClassificationUsage> = ArrayList()

    @ManyToOne
    @JoinColumn(name = "big_classifiction", nullable = false)
    val bigClassification: RecruitmentBigClassification = bigClassification

    fun toClassificationResponse(): ClassificationResponse {
        return ClassificationResponse(
            BigClassificationResponse(
                this.bigClassification.name
            ),
            this.name
        )
    }
}