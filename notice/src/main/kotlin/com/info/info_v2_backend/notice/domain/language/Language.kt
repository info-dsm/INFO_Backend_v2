package com.info.info_v2_backend.notice.domain.language

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Language(
    name: String
) {

    @Id
    @Column(name = "language_name", nullable = false)
    val name: String = name


    @OneToMany
    var languageUsage: MutableList<LanguageUsage> = ArrayList()
        protected set

    fun toLanguageResponse(): LanguageResponse {
        return LanguageResponse(
            this.name
        )
    }

}