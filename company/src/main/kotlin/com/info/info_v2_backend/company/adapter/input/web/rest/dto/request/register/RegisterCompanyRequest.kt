package com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.CompanyContactRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.CompanyInformationRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.CompanyNameRequest
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class RegisterCompanyRequest(
    val companyNumber: String,
    @field:Valid
    val companyNameRequest: CompanyNameRequest,
    @field:Valid
    val companyInformation: CompanyInformationRequest,
    @field:Valid
    val companyContact: CompanyContactRequest,
    @field:NotNull
    val businessAreaList: List<String>,
    @field:Size(
        max = 255,
        min = 10,
        message = "회사 소개는 10자 이상이여야합니다."
    )
    val introduction: String,
    val businessRegisteredCertificate: GenerateFileRequest,
    val companyIntroductionFile: GenerateFileListRequest?,
    val companyLogo: GenerateFileRequest?,
    val companyPhotoList: GenerateFileListRequest?
) {


}
