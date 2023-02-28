package com.info.info_v2_backend.common.exception

import com.info.info_v2_backend.common.logs.LogFormat

open class BusinessException(
    message: String?,
    val errorCode: ErrorCode
) : RuntimeException() {
    override val message: String? = message?.let {LogFormat.ERROR(it)}
}