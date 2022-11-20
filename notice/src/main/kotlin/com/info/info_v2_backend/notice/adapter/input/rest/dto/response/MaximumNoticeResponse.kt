package com.info.info_v2_backend.notice.adapter.input.rest.dto.response

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.NoticeOpenPeriodRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.MealSupportRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WelfareRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WorkTimeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.domain.company.NoticeCompany

data class MaximumNoticeResponse(
    val noticeId: String,
    val company: NoticeCompany,
    val classificationResponse: ClassificationResponse,
    val detailBusinessDescription: String?,
    val numberOfEmployee: Int,
    val gradeCutLine: Int?,
    val languageSet: Set<LanguageResponse>,
    val technologySet: Set<TechnologyResponse>,
    val certificateList: List<CertificateResponse>,
    val workTime: WorkTimeRequest,
    val mealSupport: MealSupportRequest,
    val welfare: WelfareRequest,
    val noticeOpenPeriod: NoticeOpenPeriodRequest,
    val interviewProcessList: List<Map<Int, String>>,
    val needDocuments: String?,
    val otherFeatures: String?,
    val workPlace: WorkPlaceRequest,
    val applicantCount: Int,
    val attachmentFileList: List<AttachmentResponse>,
    val isPersonalContact: Boolean

)
