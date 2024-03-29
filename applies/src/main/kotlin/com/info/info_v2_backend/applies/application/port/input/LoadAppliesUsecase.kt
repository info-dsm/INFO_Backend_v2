package com.info.info_v2_backend.applies.application.port.input

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.data.domain.Page

interface LoadAppliesUsecase {

    fun loadAppliesListByStatus(companyNumber: String, noticeId: String, status: AppliesStatus?): List<AppliesResponse>
    fun loadApplies(noticeId: String, studentEmail: String): AppliesDto?
    fun loadAppliesListByStatus(status: AppliesStatus, idx: Int, size: Int): Page<AppliesResponse>
    fun loadAppliesListByStudentEmail(studentEmail: String): List<AppliesResponse>
}