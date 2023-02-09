package com.info.info_v2_backend.statistics.persistance.entity

import java.time.LocalDate
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id


@Entity
class StatInformation (
    total: StatTotalInformationResponse,
) {
    @Id
    val year: Int = LocalDate.now().year

    @Embedded
    var total: StatTotalInformationResponse = total
        protected set

    @ElementCollection
    var studentList: MutableList<StatStudentInformationResponse> = ArrayList()
        protected set

    @ElementCollection
    var hiredTimeGraph: MutableList<StatHiredTimeGraph> = ArrayList()
        protected set

}