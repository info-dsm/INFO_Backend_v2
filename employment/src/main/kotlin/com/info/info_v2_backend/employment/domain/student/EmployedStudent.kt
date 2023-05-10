package com.info.info_v2_backend.employment.domain.student

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


const val FIRST_GENERATION_YEAR = 2015
@Embeddable
class EmployedStudent(
    studentEmail:String,
    name: String,
    entranceYear: Int,
) {
    @Column(name = "employed_student_email", nullable = false)
    var studentEmail: String = studentEmail
        protected set

    @Column(name = "employed_student_name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "employed_student_entrance_year", nullable = false)
    var entranceYear: Int = entranceYear
        protected set

    fun toEmploymentStudentResponse(): EmploymentDto.EmploymentStudentResponse {
        return EmploymentDto.EmploymentStudentResponse(
            this.studentEmail,
            this.name,
            this.entranceYear - FIRST_GENERATION_YEAR + 1
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other is EmployedStudent) return this.studentEmail == other.studentEmail
        return super.equals(other)
    }

}
