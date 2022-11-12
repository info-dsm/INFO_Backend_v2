package com.info.info_v2_backend.apiGateway.exception

import com.info.info_v2_backend.common.exception.ErrorCode

class ErrorResponse(
    val message: String,
    val status: Int,
    val errors: List<FieldError> = emptyList(),
    val code: String,
) {

    constructor(
        message: String? = null,
        code: ErrorCode
    ) : this(
        message = message ?: code.message,
        status = code.status,
        code = code.code
    )
    class FieldError(
        val field: String,
        val value: String,
        val reason: String
    )
}

