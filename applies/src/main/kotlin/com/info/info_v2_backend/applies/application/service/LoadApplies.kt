package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.application.port.input.LoadAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.load.LoadAppliesPort
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.stereotype.Service

@Service
class LoadApplies(
    private val loadAppliesPort: LoadAppliesPort
): LoadAppliesUsecase {

    override fun loadAppliesListByStatus(noticeId: String, status: AppliesStatus): List<AppliesResponse> {
        return loadAppliesPort.loadAppliesList(noticeId, status).map {
            it.toAppliesResponse()
        }
    }

    override fun loadApplies(noticeId: String, studentEmail: String): AppliesDto? {
        return loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)
            ?.toAppliesDto()
    }


}