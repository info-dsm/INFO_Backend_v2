package com.info.info_v2_backend.applies.application.port.output.load

import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus

interface LoadAppliesPort {

    fun loadAppliesList(noticeId: String, status: AppliesStatus): List<Applies>
    fun loadApplies(appliesId: String): Applies?
    fun loadAppliesByNoticeAndStudentEmail(noticeId: String, studenEmail: String): Applies?
}