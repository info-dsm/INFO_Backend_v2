package com.info.info_v2_backend.company.adapter.output.persistence.query

import com.info.info_v2_backend.company.domain.document.CompanyDocument
import com.info.info_v2_backend.company.adapter.output.persistence.query.repository.CompanyDocumentRepository
import com.info.info_v2_backend.company.application.port.output.document.RemoveCompanyDocumentPort
import com.info.info_v2_backend.company.application.port.output.document.SaveCompanyDocumentPort
import com.info.info_v2_backend.company.application.port.output.document.SearchCompanyDocumentPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.stereotype.Service


@Service
class MongoCompanyDocumentAdapter(
    private val companyDocumentRepository: CompanyDocumentRepository
): SearchCompanyDocumentPort, RemoveCompanyDocumentPort, SaveCompanyDocumentPort {

    override fun search(idx: Int, size: Int, query: String): Page<CompanyDocument> {
        return companyDocumentRepository.findAllBy(
            TextCriteria.forDefaultLanguage().matchingAny(query),
            PageRequest.of(idx, size, Sort.by("createdAt"))
        )
    }

    override fun remove(companyNumber: String) {
        return companyDocumentRepository.deleteByCompanyNumber(companyNumber)
    }

    override fun save(document: CompanyDocument) {
        companyDocumentRepository.save(
            document
        )
    }
}