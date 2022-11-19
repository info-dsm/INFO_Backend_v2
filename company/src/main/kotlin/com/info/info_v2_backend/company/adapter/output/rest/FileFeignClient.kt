package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.company.adapter.output.event.configuration.FormConfiguration
import com.info.info_v2_backend.company.adapter.output.rest.configuration.FeignAuthConfiguration
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "FILE-SERVICE", configuration = [FormConfiguration::class, FeignAuthConfiguration::class], fallbackFactory = FileFeignClientFallbackFactory::class)
interface FileFeignClient: CompanyFilePort {

    @PutMapping("/company", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    override fun upload(@RequestParam(name = "companyNumber") companyId: String, @RequestParam(name = "classification") classification: CompanyFileClassificationType, @RequestPart(value = "file") file: MultipartFile)
    @PatchMapping("/company", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Async
    override fun change(
        @RequestParam fileId: String,
        @RequestParam companyId: String,
        @RequestParam classification: CompanyFileClassificationType,
        @RequestPart file: MultipartFile
    )

    @GetMapping("/company/{companyNumber}")
    override fun loadCompanyFile(@PathVariable companyNumber: String): List<CompanyFileResponse>

    @DeleteMapping("/company/{companyNumber}/{fileId}")
    override fun remove(@PathVariable companyNumber: String, @PathVariable fileId: String, @RequestParam classificationType: CompanyFileClassificationType)
}