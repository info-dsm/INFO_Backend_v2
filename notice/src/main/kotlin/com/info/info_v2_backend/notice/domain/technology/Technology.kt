package com.info.info_v2_backend.notice.domain.technology

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Technology(
    name: String
) {
    @Id
    @Column(name = "technology_name", nullable = false)
    val name: String = name

    fun toTechnologyResponse(): TechnologyResponse {
        return TechnologyResponse(
            this.name
        )
    }


}