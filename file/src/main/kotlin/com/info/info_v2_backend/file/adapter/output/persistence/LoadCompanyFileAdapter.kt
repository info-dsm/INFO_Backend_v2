package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.adapter.output.persistence.repository.CompanyFileRepostiory
import com.info.info_v2_backend.file.adapter.output.persistence.repository.FileRepository
import com.info.info_v2_backend.file.application.port.output.LoadCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.stereotype.Service

@Service
class LoadCompanyFileAdapter(
    private val companyFileRepository: CompanyFileRepostiory
): LoadCompanyFilePort {
    override fun load(fileId: String): CompanyFile? {
        return companyFileRepository.findById(fileId).orElse(null)
    }

    override fun loadByCompanyNumber(companyNumber: String): List<CompanyFile> {
        return companyFileRepository.findByCompanyId(companyNumber)
    }
}