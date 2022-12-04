package com.info.info_v2_backend.notice.domain.technology

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "technology")
class Technology(
    name: String
) {
    @Id
    @Column(name = "technology_name", nullable = false)
    val name: String = name

    @OneToMany(mappedBy = "technology")
    val technologyUsageList: MutableList<TechnologyUsage> = ArrayList()

    fun toTechnologyResponse(): TechnologyResponse {
        return TechnologyResponse(
            this.name
        )
    }


}