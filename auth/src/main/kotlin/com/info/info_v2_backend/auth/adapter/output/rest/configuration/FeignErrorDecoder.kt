package com.info.info_v2_backend.auth.adapter.output.rest.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.exception.ErrorResponse
import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.LoggerFactory
import java.lang.Exception


class FeignErrorDecoder(
): ErrorDecoder {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun decode(methodKey: String?, response: Response): Exception {
        val response = parse(response)
        log.info(response?.message)
        log.info("DDDDDDDDD")
        return BusinessException(response?.message,
            ErrorCode.values().filter { it.code == (response?.code ?: ErrorCode.FEIGN_ERROR.code) }.first()
        )
    }

    private fun parse(response: Response): ErrorResponse? {
        return runCatching {
            objectMapper.readValue(response.body().asInputStream(), ErrorResponse::class.java)
        }.getOrNull()
    }

}