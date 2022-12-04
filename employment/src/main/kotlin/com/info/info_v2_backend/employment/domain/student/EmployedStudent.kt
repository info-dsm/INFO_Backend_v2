package com.info.info_v2_backend.employment.domain.student

import com.info.info_v2_backend.common.employment.EmploymentDto
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class EmployedStudent(
    studentEmail:String,
    name: String,
    generation: Int
) {
    @Column(name = "employed_student_email", nullable = false)
    var studentEmail: String = studentEmail
        protected set

    @Column(name = "employed_student_name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "employed_student_generation", nullable = false)
    var generation: Int = generation
        protected set

    constructor(
        studentEmail: String
    ) : this(studentEmail, "system", 1)

    fun toEmploymentStudentResponse(): EmploymentDto.EmploymentStudentResponse {
        return EmploymentDto.EmploymentStudentResponse(
            this.studentEmail,
            this.name,
            this.generation
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other is EmployedStudent) return this.studentEmail == other.studentEmail
        return super.equals(other)
    }

}