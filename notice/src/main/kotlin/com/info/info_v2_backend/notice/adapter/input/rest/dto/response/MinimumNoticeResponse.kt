package com.info.info_v2_backend.notice.adapter.input.rest.dto.response

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.domain.company.NoticeCompany

data class MinimumNoticeResponse(
    val noticeId: String,
    val company: NoticeCompany,
    val classificationResponse: List<ClassificationResponse>,
    val detailBusinessDescription: String?,
    val numberOfEmployee: Int,
    val gradeCutLine: Int?,
    val applicantCount: Int,
    val isPersonalContact: Boolean

) {

}
