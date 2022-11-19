package com.info.info_v2_backend.apiGateway.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import org.springframework.core.ResolvableType
import org.springframework.core.annotation.Order
import org.springframework.core.codec.Hints
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono

@Component
@Order(-1)
class GatewayExceptionHandler(
    private val objectMapper: ObjectMapper
): WebExceptionHandler {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val response = exchange.response
        response.headers.contentType = MediaType.APPLICATION_JSON

        val errorResponse = when (ex) {
            is ResponseStatusException -> {
                println(ex.message)
                ErrorResponse(code = ErrorCode.FRAME_WORK_INTERNAL_ERROR)
            }
            is BusinessException -> {
                response.statusCode = HttpStatus.valueOf(ex.errorCode.status)
                ErrorResponse(code = ex.errorCode)
            }
            else -> {
                println(ex)
                response.statusCode = HttpStatus.valueOf(ErrorCode.UNDEFINED_ERROR.status)
                ErrorResponse(code = ErrorCode.UNDEFINED_ERROR)
            }
        }

        return response.writeWith(
            Jackson2JsonEncoder(objectMapper).encode(
                Mono.just(errorResponse),
                response.bufferFactory(),
                ResolvableType.forInstance(errorResponse),
                MediaType.APPLICATION_JSON,
                Hints.from(Hints.LOG_PREFIX_HINT, exchange.logPrefix)
            )
        )
    }


}