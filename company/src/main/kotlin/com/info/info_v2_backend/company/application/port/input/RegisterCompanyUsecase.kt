package com.info.info_v2_backend.company.application.port.input

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.RegisterCompanyRequest
import org.springframework.web.multipart.MultipartFile

interface RegisterCompanyUsecase {

    fun register(
        emailCheckCode: String,
        request: RegisterCompanyRequest,
        businessRegisteredCertificate: MultipartFile,
        companyIntroductionFile: List<MultipartFile>,
        companyLogo: MultipartFile,
        companyPhotoList: List<MultipartFile>
    )
}