package com.info.info_v2_backend.applies.adapter.input.rest.configuration

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AppliesExceptionHandler {


    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandler(e: BusinessException): ResponseEntity<*> {
        return ResponseEntity.status(e.errorCode.status).body(
            ErrorResponse(
                e.message,
                e.errorCode
            )
        )
    }

}