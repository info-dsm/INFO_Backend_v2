package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.company.adapter.output.event.configuration.FormConfiguration
import com.info.info_v2_backend.company.application.port.output.file.FilePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "FILE-SERVICE", configuration = [FormConfiguration::class], fallbackFactory = FileFeignClientFallbackFactory::class)
interface FileFeignClient: FilePort {

    @PutMapping("/company", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    override fun upload(@RequestParam(name = "companyId") companyId: String, @RequestParam(name = "classification") classification: CompanyFileClassificationType, @RequestPart(value = "file") file: MultipartFile): String
    @PatchMapping("/company", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    override fun change(
        @RequestParam fileId: String,
        @RequestParam companyId: String,
        @RequestParam classification: CompanyFileClassificationType,
        @RequestPart file: MultipartFile
    ): String

    @DeleteMapping
    override fun remove(@RequestParam companyId: String, @RequestParam fileId: String)
}