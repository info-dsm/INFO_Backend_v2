package com.info.info_v2_backend.file.domain.applies

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ResumeStudent(
    studentEmail: String
) {

    @Column(name = "resume_student_email", nullable = false)
    val studentEmail: String = studentEmail


}