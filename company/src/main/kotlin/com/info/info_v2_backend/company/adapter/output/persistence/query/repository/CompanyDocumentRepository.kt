package com.info.info_v2_backend.company.adapter.output.persistence.query.repository

import com.info.info_v2_backend.company.domain.document.CompanyDocument
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyDocumentRepository: MongoRepository<CompanyDocument, ObjectId> {

    fun findAllBy(textCriteria: TextCriteria, pageable: Pageable): Page<CompanyDocument>
    fun deleteByCompanyNumber(companyNumber: String)
}