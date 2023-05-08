package com.info.info_v2_backend.notice.domain.noticePreference

import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.ManyToOne


@Entity
@IdClass(NoticePreferenceIdClass::class)
class NoticePreference(
    smallClassification: RecruitmentSmallClassification,
    userEmail: String
) {

    @Id
    @ManyToOne
    val smallClassification: RecruitmentSmallClassification = smallClassification

    @Id
    val userEmail: String = userEmail

}
