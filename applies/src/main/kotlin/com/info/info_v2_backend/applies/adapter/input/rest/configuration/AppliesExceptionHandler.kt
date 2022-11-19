package com.info.info_v2_backend.applies.adapter.input.rest.configuration

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ValidationException

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

    @ExceptionHandler(ValidationException::class)
    fun validationExceptionHandler(e: ValidationException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                e.message,
                ErrorCode.INPUT_DATA_NOT_FOUND_ERROR
            )
        )
    }

}