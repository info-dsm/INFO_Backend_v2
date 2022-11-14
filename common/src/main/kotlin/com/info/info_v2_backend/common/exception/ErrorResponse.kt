package com.info.info_v2_backend.common.exception

class ErrorResponse(
    val message: String,
    val status: Int,
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
}

