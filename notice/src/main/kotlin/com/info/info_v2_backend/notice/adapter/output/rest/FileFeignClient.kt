package com.info.info_v2_backend.notice.adapter.output.rest

import com.info.info_v2_backend.common.file.dto.AttachmentResponse
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "FILE-SERVICE", fallbackFactory = FileFeignClientFallback::class)
interface FileFeignClient: FilePort {

    @PutMapping("/notice/{noticeId}/attachment", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    override fun saveFile(
        @PathVariable noticeId: String,
        @RequestPart attachment: List<MultipartFile>
    )

    @GetMapping("/notice/{noticeId}/attachment")
    override fun loadAttachmentList(
        @PathVariable noticeId: String
    ): List<AttachmentResponse>

}