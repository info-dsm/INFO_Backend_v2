package com.info.info_v2_backend.announcement.global.exception

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@RestControllerAdvice
class AnnouncementExceptionHandler: ResponseEntityExceptionHandler() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(BusinessException::class)
    private fun businessExceptionHandler(ex: BusinessException, request: WebRequest) : ResponseEntity<*> {
        var parsedMessage: String? = null
        ex.message?.let {
            parsedMessage = messageParser(it)
        }
        log.warn(parsedMessage)
        return handleExceptionInternal(ex, ErrorResponse(
            parsedMessage?:ex.errorCode.message,
            ex.errorCode
        ), HttpHeaders(), HttpStatus.valueOf(ex.errorCode.status), request);
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    @ResponseBody
    private fun validationExceptionHandler(ex: ValidationException): ResponseEntity<*> {
        log.warn(ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                ex.message,
                ErrorCode.INPUT_DATA_NOT_FOUND_ERROR
            )
        )
    }

    private fun messageParser(logMessage: String): String {
        return logMessage.substring(logMessage.indexOf("MESSAGE: ") + 9)
    }

}
