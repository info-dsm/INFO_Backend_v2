package com.info.info_v2_backend.auth.adapter.output.rest.configuration

import com.info.info_v2_backend.common.auth.HeaderProperty
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Configuration
class FeignAuthConfiguration {

    @Bean
    fun requestInterceptor(): RequestInterceptor? {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header(
                HeaderProperty.AUTH_LEVEL, (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
                        ).request.getHeader(HeaderProperty.AUTH_LEVEL))
        }
    }

}