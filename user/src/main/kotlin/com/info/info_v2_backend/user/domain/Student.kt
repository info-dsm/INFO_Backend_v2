package com.info.info_v2_backend.user.domain

import com.info.info_v2_backend.common.user.StudentDto
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Year
import javax.persistence.*


@Entity
@DiscriminatorValue("student")
@OnDelete(action = OnDeleteAction.CASCADE)
class Student(
    studentKey: String,
    name: String,
    email: String,
    password: String,
    githubLink: String?
): User(
    name,
    email,
    password,
    Role.STUDENT,
    null
) {
    val studentKey: String = studentKey

    val entranceYear: Int = Year.now().minusYears((studentKey.substring(0, 1).toLong()-1)).value

//    @OneToMany(mappedBy = "student")
//    var hiredStudentList: MutableList<HiredStudent> = ArrayList()
//        protected set
//
//    @OneToMany(mappedBy = "student")
//    var fieldTrainingList: MutableList<FieldTraining> = ArrayList()
//        protected set
//
//    @OneToMany(mappedBy = "student")
//    var applicantList: MutableList<Applicant> = ArrayList()
//        protected set

    @Column(name = "github_link")
    var githubLink: String? = githubLink
        protected set

    fun toStudentDto(): StudentDto {
        return StudentDto(
            this.studentKey,
            this.name,
            this.email,
            this.entranceYear,
            this.githubLink
        )
    }


}