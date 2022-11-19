package com.info.info_v2_backend.notice.application.port.output.bigClassification

import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification

interface SaveBigClassificationPort {

    fun saveBigClassification(bigClassification: RecruitmentBigClassification): RecruitmentBigClassification
}