package com.info.info_v2_backend.notice.adapter.output.rest.configuration

import com.info.info_v2_backend.common.auth.HeaderProperty
import com.info.info_v2_backend.common.user.Role
import feign.RequestInterceptor
import feign.RequestTemplate
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {
       @Bean
    fun requestInterceptor(): RequestInterceptor? {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header(
                HeaderProperty.AUTH_LEVEL,
                Role.SYSTEM.mean)
        }
    }

    @Bean
    fun getErrorDecoder(): ErrorDecoder {
        return FeignErrorDecoder()
    }

}