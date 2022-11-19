package com.info.info_v2_backend.employment.application.port.output

import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus

interface LoadAppliesStudentPort {

    fun loadAppliesStudent(noticeId: String, studentEmail: String): AppliesDto?
}