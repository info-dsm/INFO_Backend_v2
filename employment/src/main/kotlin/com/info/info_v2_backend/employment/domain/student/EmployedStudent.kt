package com.info.info_v2_backend.employment.domain.student

import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded


@Embeddable
class EmployedStudent(
    studentEmail:String,
    name: String,
    generation: Int
) {
    @Column(name = "employed_student_email", nullable = false)
    val studentEmail: String = studentEmail

    @Column(name = "employed_student_name", nullable = false)
    val name: String = name

    @Column(name = "employed_student_generation", nullable = false)
    val generation: Int = generation

    fun toEmploymentStudentResponse(): EmploymentResponse.EmploymentStudentResponse {
        return EmploymentResponse.EmploymentStudentResponse(
            this.studentEmail,
            this.name,
            this.generation
        )
    }

}