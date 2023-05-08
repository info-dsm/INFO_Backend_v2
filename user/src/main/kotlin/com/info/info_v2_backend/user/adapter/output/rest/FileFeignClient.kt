package com.info.info_v2_backend.user.adapter.output.rest

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.common.file.dto.response.UserFileResponse
import com.info.info_v2_backend.user.application.port.output.UserFilePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@FeignClient(name = "FILE-SERVICE")
interface FileFeignClient: UserFilePort {


    @PostMapping("/user/{userEmail}")
    override fun upload(@RequestBody request: GenerateFileRequest, @PathVariable userEmail: String): PresignedUrlResponse

    @GetMapping("/user/{email}")
    override fun loadProfilePhoto(@PathVariable email: String): UserFileResponse?
}
