package com.info.info_v2_backend.file.adapter.input.rest

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.UploadCompanyFileDto
import com.info.info_v2_backend.file.application.port.input.UploadCompanyFileUsecase
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(
    private val uploadCompanyFileUsecase: UploadCompanyFileUsecase
) {

    @PutMapping("/company")
    fun uploadCompanyFile(
        @RequestParam companyId: String,
        @RequestParam classification: CompanyFileClassificationType,
        @RequestPart(value = "file") file: MultipartFile
    ): String {
        return uploadCompanyFileUsecase.uploadCompanyFile(file, classification, companyId)
    }
}