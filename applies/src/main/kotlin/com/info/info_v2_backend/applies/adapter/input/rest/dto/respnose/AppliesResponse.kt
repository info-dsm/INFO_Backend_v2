package com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose

import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.common.file.dto.response.FileResponse

data class AppliesResponse(
    val appliesId: String,
    val applier: ApplierResponse,
    val noticeId: String,
    val status: AppliesStatus,
    val resumeList: FileResponse

) {
    data class ApplierResponse (
        val email: String,
        val name: String
    )
}
