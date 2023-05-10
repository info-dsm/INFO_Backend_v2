package com.info.info_v2_backend.employment.domain.generation

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class GenerationGrade(
    generation: Int
) {

    @Id
    @Column(name = "generation_id", nullable = false)
    val generation: Int = generation

    @OneToMany(mappedBy = "generationGrade")
    val generationClassList: MutableList<GenerationClass> = ArrayList()

}
