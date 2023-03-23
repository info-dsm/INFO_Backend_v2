package com.info.info_v2_backend.company.adapter.output.rest.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import feign.Response
import feign.codec.ErrorDecoder
import feign.codec.StringDecoder
import org.slf4j.LoggerFactory

class FeignErrorDecoder(
): ErrorDecoder {
    private val objectMapper = ObjectMapper()
    private val stringDecoder = StringDecoder()

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun decode(methodKey: String?, response: Response): Exception? {
        val response = parse(response)
        log.info(response?.message)
        return BusinessException(response?.message,
            ErrorCode.values().first { it.code == (response?.code ?: ErrorCode.FEIGN_ERROR.code) }
        )
    }

    private fun parse(response: Response): ErrorResponse? {
        val message = response.body().asInputStream().readAllBytes()
        val parsedMessageResponse = objectMapper.readValue(message, ErrorResponse::class.java)
        log.info(parsedMessageResponse.message)
        return parsedMessageResponse
    }
}