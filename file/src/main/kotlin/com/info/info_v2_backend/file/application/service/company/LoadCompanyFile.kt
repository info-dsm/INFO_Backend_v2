package com.info.info_v2_backend.file.application.service.company

import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.application.port.input.company.LoadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.output.company.LoadCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.stereotype.Service

@Service
class LoadCompanyFile(
    private val loadCompanyFilePort: LoadCompanyFilePort
): LoadCompanyFileUsecase {
    override fun load(fileId: String): CompanyFile? {
        return loadCompanyFilePort.load(fileId)
    }

    override fun loadPhotosByCompanyNumber(companyNumber: String): List<CompanyFileResponse> {
        return loadCompanyFilePort.loadPhotosByCompanyNumber(companyNumber).map {
            it.toCompanyFileResponse()
        }
    }


}
