package com.info.info_v2_backend.notice.adapter.input.rest.dto.response

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.NoticeOpenPeriodRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.MealSupportRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WelfareRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WorkTimeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess

data class MaximumNoticeResponse(
    val noticeId: String,
    val company: NoticeCompany,
    val classificationResponse: List<ClassificationResponse>,
    val detailBusinessDescription: String?,
    val numberOfEmployee: Int,
    val gradeCutLine: Int?,
    var interviewProcessList: Map<Int, String>,
    var languageList: List<LanguageResponse>,
    var technologyList: List<TechnologyResponse>,
    var certificateList: List<CertificateResponse>,
    val workTime: WorkTimeRequest,
    val mealSupport: MealSupportRequest,
    val welfare: WelfareRequest,
    val noticeOpenPeriod: NoticeOpenPeriodRequest,
    val needDocuments: String?,
    val otherFeatures: String?,
    val workPlace: WorkPlaceRequest,
    val applicantCount: Int,
    var attachmentFileList: MutableList<AttachmentResponse>,
    val isPersonalContact: Boolean

) {

    fun addAllAttachmentFileList(attachmentFileList: MutableList<AttachmentResponse>) {
        this.attachmentFileList = attachmentFileList
    }

    fun addAllInterviewProcessMap(interviewProcessMap: MutableMap<Int, InterviewProcess>) {
        val map: MutableMap<Int, String> = HashMap()
        interviewProcessMap.map {
            map.put(it.key, it.value.meaning)
        }
        this.interviewProcessList = map
    }

    fun addAllLanguageResponse(languageList: MutableList<LanguageResponse>) {
        this.languageList = languageList
    }

    fun addAllTechnologyResponse(technologyList: MutableList<TechnologyResponse>) {
        this.technologyList = technologyList
    }

    fun addAllCertificateResponse(certificateList: MutableList<CertificateResponse>) {
        this.certificateList = certificateList
    }

}
