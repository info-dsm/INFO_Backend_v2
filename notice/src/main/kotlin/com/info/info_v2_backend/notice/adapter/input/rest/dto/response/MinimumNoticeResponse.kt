package com.info.info_v2_backend.notice.adapter.input.rest.dto.response

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace

data class MinimumNoticeResponse(
    val noticeId: String,
    val company: MinimumNoticeCompany,
    val classificationResponse: List<ClassificationResponse>,
    val detailBusinessDescription: String?,
    val numberOfEmployee: Int,
    val gradeCutLine: Int?,
    val applicantCount: Int,
    val isPersonalContact: Boolean,
    val noticeOpenPeriod: NoticeOpenPeriod,
    val workPlace: WorkPlaceRequest

) {

    data class MinimumNoticeCompany(
        val companyNumber: String,
        val companyName: String,
        val imageList: MutableList<String>
    )

}
