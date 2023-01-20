package com.info.info_v2_backend.statistics.persistance.entity

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id


@Entity
class StatInformation (
    @Id
    val year: Int = LocalDate.now().year,
    val total: StatTotalInformationResponse,
    val studentList: List<StatStudentInformationResponse>,
    val hiredTimeGraph: List<StatHiredTimeGraph>
) {

    data class StatTotalInformationResponse(
        val count: Int,
        val generation: Int,
        val newCount: Int
    )

    data class StatStudentInformationResponse(
        val classNum: String,
        val company: StatCompanyInformationResponse
    )

    data class StatCompanyInformationResponse(
        val logo: String,
        val name: String
    )

    data class StatHiredTimeGraph(
        val month: Int,
        val date: Int,
        val count: Int
    )

}