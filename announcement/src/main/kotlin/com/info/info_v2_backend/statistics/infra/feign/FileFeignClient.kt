package com.info.info_v2_backend.statistics.infra.feign

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "FILE-SERVICE", fallbackFactory = FileFeignClientFallback::class)
interface FileFeignClient {

    @PostMapping("/announcement/{announcementId}")
    fun createAnnouncementFile(
        @PathVariable announcementId: Long,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse

    @GetMapping("/announcement/{announcementId}")
    fun getAnnouncementFileList(
        @PathVariable announcementId: Long
    ): List<AnnouncementFileResponse>
}