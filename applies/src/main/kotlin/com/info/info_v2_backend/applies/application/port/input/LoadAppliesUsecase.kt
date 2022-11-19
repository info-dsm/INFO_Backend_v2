package com.info.info_v2_backend.applies.application.port.input

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import java.util.Optional

interface LoadAppliesUsecase {

    fun loadAppliesListByStatus(noticeId: String, status: AppliesStatus): List<AppliesResponse>
    fun loadApplies(noticeId: String, studentEmail: String): AppliesDto?
}