package com.info.info_v2_backend.applies.adapter.output.rest

import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class FileFeignClientFallback: FallbackFactory<FileFeignClient> {

    override fun create(cause: Throwable?): FileFeignClient {
        return object : FileFeignClient {

        }
    }

}