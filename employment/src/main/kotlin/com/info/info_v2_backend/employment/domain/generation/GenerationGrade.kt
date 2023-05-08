package com.info.info_v2_backend.employment.domain.generation

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class GenerationGrade(
    generation: Int,
    totalGradeStudent: Int
) {

    @Id
    @Column(name = "generation_id", nullable = false)
    val generation: Int = generation

    @Column(name = "total_grade_student", nullable = false)
    val totalGradeStudent: Int = totalGradeStudent

    @OneToMany(mappedBy = "generationGrade")
    val generationClassList: MutableList<GenerationClass> = ArrayList()

}
