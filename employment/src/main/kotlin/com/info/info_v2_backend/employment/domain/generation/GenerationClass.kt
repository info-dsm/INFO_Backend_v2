package com.info.info_v2_backend.employment.domain.generation

import com.fasterxml.jackson.annotation.JsonIgnore
import com.info.info_v2_backend.employment.domain.Employment
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@IdClass(GenerationClassIdClass::class)
@Table(name = "generation_class")
class GenerationClass(
    classNum: Int,
    generationGrade: GenerationGrade,
    totalClassStudent: Int,
    major: String,
    description: String,
    homeroomTeacherName: String
) {
    @Id
    @Column(name = "generation_class", nullable = false)
    val classNum: Int = classNum

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "generation_grade", nullable = false)
    val generationGrade: GenerationGrade = generationGrade

    @Column(name = "total_class_student", nullable = false)
    val totalClassStudent: Int = totalClassStudent

    @Column(name = "generation_class_major", nullable = false)
    val major: String = major

    @Column(name = "generation_class_description", nullable = false)
    val description: String = description

    @OneToMany(mappedBy = "generationClass")
    @JsonIgnore
    val employmentList: MutableList<Employment> = ArrayList()

    @Column(name = "homeroom_teacher_name", nullable = false, length = 10)
    val homeroomTeacherName: String = homeroomTeacherName
}
