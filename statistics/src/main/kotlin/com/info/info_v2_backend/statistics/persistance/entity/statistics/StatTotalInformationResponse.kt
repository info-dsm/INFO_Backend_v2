package com.info.info_v2_backend.statistics.persistance.entity.statistics

import javax.persistence.Embeddable

@Embeddable
class StatTotalInformationResponse(
    count: Int,
    generation: Int,
    newCount: Int
) {
    var count: Int = count
        protected set

    var generation: Int = generation
        protected set

    var newCount: Int = newCount
        protected set
}