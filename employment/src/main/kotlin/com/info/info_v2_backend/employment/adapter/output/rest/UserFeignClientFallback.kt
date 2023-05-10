package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.user.StudentDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class UserFeignClientFallback: FallbackFactory<UserFeignClient> {
    override fun create(cause: Throwable?): UserFeignClient {
        return object : UserFeignClient{
            override fun loadStudent(studentEmail: String): StudentDto? {
                return null
            }
        }
    }
}
