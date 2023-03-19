package com.info.info_v2_backend.auth.adapter.input.rest.configuration

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import feign.FeignException
import feign.Request
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.handler.DispatcherServletWebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@RestControllerAdvice
class AuthExceptionHandler: ResponseEntityExceptionHandler() {


    @ExceptionHandler(BusinessException::class)
    private fun businessExceptionHandler(ex: BusinessException, request: WebRequest) : ResponseEntity<*> {
        return handleExceptionInternal(ex, ErrorResponse(
            ex.message,
            ex.errorCode
        ), HttpHeaders(), HttpStatus.valueOf(ex.errorCode.status), request);
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
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