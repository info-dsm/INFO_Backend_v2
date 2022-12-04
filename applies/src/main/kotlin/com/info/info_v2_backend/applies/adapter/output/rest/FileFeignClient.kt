package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.applies.application.port.output.resume.ResumePort
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "FILE-SERVICE", fallbackFactory = FileFeignClientFallback::class)
interface FileFeignClient: ResumePort {

    @PutMapping("/applies/{noticeId}/{studentEmail}/resume")
    @Async
    override fun uploadResume(@PathVariable noticeId: String, @PathVariable studentEmail: String, @RequestPart resume: MultipartFile)

    @GetMapping("/applies/{noticeId}/{studentEmail}/resume")
    override fun loadAppliesResume(@PathVariable noticeId: String, @PathVariable studentEmail: String): FileResponse

}