package com.info.info_v2_backend.notice.adapter.output.rest.configuration

import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class FormConfiguration {
    @Bean
    fun multipartFormEncoder(): Encoder {
        return SpringFormEncoder(SpringEncoder {
            HttpMessageConverters(
                RestTemplate().messageConverters
            )
        })
    }
}