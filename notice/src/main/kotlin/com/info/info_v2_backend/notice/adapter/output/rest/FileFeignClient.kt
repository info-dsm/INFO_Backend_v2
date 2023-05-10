package com.info.info_v2_backend.notice.adapter.output.rest

import com.info.info_v2_backend.common.file.dto.response.AttachmentResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "FILE-SERVICE", fallbackFactory = FileFeignClientFallback::class)
interface FileFeignClient: FilePort {

    @PutMapping("/notice/{noticeId}/attachment")
    override fun saveFile(
        @PathVariable noticeId: String,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse

    @GetMapping("/notice/{noticeId}/attachment")
    override fun loadAttachmentList(
        @PathVariable noticeId: String
    ): List<AttachmentResponse>

    @DeleteMapping("/notice/{noticeId}/attachment")
    override fun removeFile(@PathVariable noticeId: String)

}
