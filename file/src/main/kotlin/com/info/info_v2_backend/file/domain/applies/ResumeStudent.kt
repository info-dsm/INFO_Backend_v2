package com.info.info_v2_backend.file.domain.applies

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ResumeStudent(
    studentEmail: String
) {

    @Column(name = "resume_student_email", nullable = false)
    val studentEmail: String = studentEmail

    override fun equals(other: Any?): Boolean {
        if (other is ResumeStudent) return other.studentEmail == this.studentEmail
        return super.equals(other)
    }

}