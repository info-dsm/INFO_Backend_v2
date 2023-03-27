package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.file.adapter.output.persistence.repository.CompanyFileRepository
import com.info.info_v2_backend.file.application.port.output.company.LoadCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.company.RemoveCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.company.SaveCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.stereotype.Service

@Service
class CompanyFileAdapter(
    private val companyFileRepository: CompanyFileRepository
): LoadCompanyFilePort, SaveCompanyFilePort, RemoveCompanyFilePort {
    override fun load(fileId: String): CompanyFile? {
        return companyFileRepository.findById(fileId).orElse(null)
    }

    override fun loadPhotosByCompanyNumber(companyNumber: String): List<CompanyFile> {
        return companyFileRepository.findCompanyPhotosByCompanyNumber(companyNumber)
    }

    override fun save(file: CompanyFile) {
        companyFileRepository.save(file)
    }

    override fun remove(classificationType: CompanyFileClassificationType, companyNumber: String) {
        companyFileRepository.deleteByCompanyFileClassificationAndCompanyNumber(classificationType, companyNumber)
    }

}