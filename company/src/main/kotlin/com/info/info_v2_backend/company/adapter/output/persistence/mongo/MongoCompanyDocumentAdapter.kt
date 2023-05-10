package com.info.info_v2_backend.company.adapter.output.persistence.mongo

import com.info.info_v2_backend.company.adapter.output.persistence.mongo.repository.CompanyDocumentRepository
import org.springframework.stereotype.Service


@Service
class MongoCompanyDocumentAdapter(
    private val companyDocumentRepository: CompanyDocumentRepository
){
//
//    override fun search(idx: Int, size: Int, query: String): Page<CompanyDocument> {
//        return companyDocumentRepository.findAllBy(
//            TextCriteria.forDefaultLanguage().matchingAny(query),
//            PageRequest.of(idx, size, Sort.by("createdAt"))
//        )
//    }
//
//    override fun remove(companyNumber: String) {
//        return companyDocumentRepository.deleteByCompanyNumber(companyNumber)
//    }
//
//    override fun save(document: CompanyDocument) {
//        companyDocumentRepository.save(
//            document
//        )
//    }
}
