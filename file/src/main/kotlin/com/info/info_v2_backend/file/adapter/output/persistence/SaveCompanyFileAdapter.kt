package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.file.adapter.output.persistence.repository.CompanyFileRepostiory
import com.info.info_v2_backend.file.application.port.output.save.SaveCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.stereotype.Service

@Service
class SaveCompanyFileAdapter(
    private val companyFileRepository: CompanyFileRepostiory
): SaveCompanyFilePort {

    override fun save(file: CompanyFile) {
        companyFileRepository.save(file)
    }


}