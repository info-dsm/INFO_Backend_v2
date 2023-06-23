package com.info.info_v2_backend.company.domain.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.TextScore

@Document(collection = "company_document")
class CompanyDocument(
    companyName: String,
    companyNumber: String
) {
    @Id
    var id: ObjectId = ObjectId.get()
        protected set
    @TextIndexed(weight = 10F)
    var companyName: String = companyName
        protected set

    @Field(name = "company_number")
    val companyNumber: String = companyNumber

    @TextScore
    var textScore: Float? = null
        protected set

    fun changeCompanyName(newName: String) {
        this.companyName = newName
    }

}
