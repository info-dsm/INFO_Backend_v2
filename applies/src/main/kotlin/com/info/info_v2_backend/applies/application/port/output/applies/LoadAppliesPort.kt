package com.info.info_v2_backend.applies.application.port.output.applies

import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus

interface LoadAppliesPort {

    fun loadAppliesList(noticeId: String, status: AppliesStatus?): List<Applies>
    fun loadApplies(appliesId: String): Applies?
    fun loadAppliesByNoticeAndStudentEmail(noticeId: String, studentEmail: String): Applies?
    fun loadEveryAppliesByStatus(status: AppliesStatus): List<Applies>
    fun loadAppliesByStudentEmail(studentEmail: String): List<Applies>
}