package com.info.info_v2_backend.statistics.persistance.entity

import javax.persistence.Embeddable

@Embeddable
class StatHiredTimeGraph(
    month: Int,
    date: Int,
    count: Int
) {
    var month: Int = month
        protected set
    var date: Int = date
        protected set
    var count: Int = count
        protected set

}