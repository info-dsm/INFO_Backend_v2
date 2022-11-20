package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.applies.application.port.output.upload.UploadResumePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "FILE-SERVICE", fallbackFactory = FileFeignClientFallback::class)
interface FileFeignClient: UploadResumePort {

    @PutMapping("/applies/{noticeId}/{studentEmail}/resume")
    @Async
    override fun uploadResume(@PathVariable noticeId: String, @PathVariable studentEmail: String, @RequestPart resume: MultipartFile)


}