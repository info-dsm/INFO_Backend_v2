package com.info.info_v2_backend.notice.adapter.input.rest.dto.request

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay.PayRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.MealSupportRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WelfareRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WorkTimeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import javax.validation.Valid

data class CreateNoticeRequest (
    val bigClassification: String,
    val smallClassification: String,
    val numberOfEmployee: Int,
    val detailBusinessDescription: String?,
    val gradeCutLine: Int?,
    val needCertificateList: List<String>,
    val languageList: List<String>,
    val technologyList: List<String>,
    @field:Valid
    val workTime: WorkTimeRequest,
    val pay: PayRequest,
    @field:Valid
    val mealSupport: MealSupportRequest,
    @field:Valid
    val welfare: WelfareRequest,
    val noticeOpenPeriod: NoticeOpenPeriodRequest,
    val interviewProcessList: List<InterviewProcess>,
    val needDocuments: String?,
    val otherFeatures: String?,
    val workPlace: WorkPlaceRequest,
    val isPersonalContact: Boolean,

)
