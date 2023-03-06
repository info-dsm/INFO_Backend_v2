package com.info.info_v2_backend.common.logs

import com.info.info_v2_backend.common.auth.Auth
import javax.servlet.http.HttpServletRequest

object LogFormat {

    fun ERROR(message: String): String {
        return "EMAIL: ${Auth.getUserEmail().toString()}, COMPANY_NUMBER: ${Auth.companyNumber()}, IS_TEACHER: ${Auth.checkIsTeacher()}, IS_SYSTEM: ${Auth.checkIsSystem()}, MESSAGE: $message"
    }

}