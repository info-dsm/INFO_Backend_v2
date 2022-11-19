package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.applies.AppliesDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class AppliesFeignClientFallback: FallbackFactory<AppliesFeignClient> {
    override fun create(cause: Throwable?): AppliesFeignClient {
        return object : AppliesFeignClient{
            override fun loadAppliesStudent(noticeId: String, studentEmail: String): AppliesDto? {
                return null
            }

        }
    }
}