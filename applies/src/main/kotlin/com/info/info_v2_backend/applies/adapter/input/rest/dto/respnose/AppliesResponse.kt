package com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose

import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.file.dto.response.FileResponse

data class AppliesResponse(
    val appliesId: String,
    val applier: ApplierResponse,
    val notice: AppliesNoticeResponse,
    val company: AppliesCompanyResponse,
    val status: AppliesStatus,
    val resumeList: List<FileResponse>,
    val message: String?

) {
    data class ApplierResponse (
        val email: String,
        val name: String
    )
    data class AppliesNoticeResponse(
        val noticeId: String,
        val smallClassificationList: List<String>
    )
    data class AppliesCompanyResponse (
        val companyName: String,
        val companyNumber: String
    )

}
