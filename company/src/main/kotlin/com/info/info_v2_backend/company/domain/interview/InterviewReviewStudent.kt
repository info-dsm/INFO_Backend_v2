package com.info.info_v2_backend.company.domain.interview

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class InterviewReviewStudent(
    studentEmail: String,
    studentKey: String,
    studentName: String
) {

    @Column(name = "student_email", nullable = false, length = 50)
    val studentEmail: String = studentEmail

    @Column(name = "student_key", nullable = false, length = 4)
    val studentKey: String = studentKey

    @Column(name = "student_name", nullable = false, length = 10)
    val studentName: String = studentName

}
