package com.info.info_v2_backend.common.auth

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

object Auth {
    fun companyNumber(): String? {
        return (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)?.request?.getHeader(
            HeaderProperty.COMPANY_NUMBER)
    }
    private fun authLevel(): String? {
        return (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)?.request?.getHeader(
            HeaderProperty.AUTH_LEVEL)
    }
    fun checkCompanyNumber(companyNumber: String): String {
        return companyNumber().takeIf { it == companyNumber }?: throw BusinessException("다른 회사에 대한 작업은 불가능합니다. -> $companyNumber", ErrorCode.NO_AUTHORIZATION_ERROR)
    }
    fun checkIsTeacher(): Boolean {
        return authLevel() == "TEACHER"
    }
    fun checkIsSystem(): Boolean {
        return authLevel() == "SYSTEM"
    }
    fun getUserEmail(): String? {
        return (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(
            HeaderProperty.USER_EMAIL
        )
    }
}