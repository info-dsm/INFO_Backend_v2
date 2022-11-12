package com.info.info_v2_backend.common.exception

open class BusinessException(
    override val message: String? = null,
    val errorCode: ErrorCode
) : RuntimeException()