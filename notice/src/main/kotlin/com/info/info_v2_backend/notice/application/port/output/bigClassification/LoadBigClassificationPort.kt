package com.info.info_v2_backend.notice.application.port.output.bigClassification

import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification

interface LoadBigClassificationPort {

    fun loadBigClassification(bigClassification: String): RecruitmentBigClassification?
}