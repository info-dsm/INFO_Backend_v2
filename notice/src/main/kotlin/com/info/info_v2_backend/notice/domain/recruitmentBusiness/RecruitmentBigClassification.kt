package com.info.info_v2_backend.notice.domain.recruitmentBusiness

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.BigClassificationResponse
import com.info.info_v2_backend.notice.domain.Notice
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class RecruitmentBigClassification(
    name: String
) {

    @Id
    @Column(name = "notice_big_classification_name", nullable = false)
    val name: String = name

    @OneToMany(mappedBy = "bigClassification")
    var  noticeList: MutableList<Notice> = ArrayList()
        protected set

    @OneToMany
    var smallClassificationList: MutableList<RecruitmentSmallClassification>  = ArrayList()
        protected set

    fun toBigClassificationResponse(): BigClassificationResponse {
        return BigClassificationResponse(
            this.name
        )
    }

}