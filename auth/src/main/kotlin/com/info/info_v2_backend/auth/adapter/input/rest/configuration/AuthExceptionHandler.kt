package com.info.info_v2_backend.auth.adapter.input.rest.configuration

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import com.info.info_v2_backend.common.logs.LogFormat
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@RestControllerAdvice
@RestController
class AuthExceptionHandler: ResponseEntityExceptionHandler() {


    @ExceptionHandler(BusinessException::class)
    private fun businessExceptionHandler(e: BusinessException): ResponseEntity<*> {
        return ResponseEntity.status(e.errorCode.status).body(
            ErrorResponse(
                e.message,
                e.errorCode
            )
        )
    }

    @ExceptionHandler(value = [ConstraintViolationException::class, MethodArgumentNotValidException::class])
    @ResponseBody
    private fun validationExceptionHandler(e: ValidationException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                e.message?.substring(0, e.message?.indexOf("MESSAGE:")?:0),
                ErrorCode.INPUT_DATA_NOT_FOUND_ERROR
            )
        )
    }

}