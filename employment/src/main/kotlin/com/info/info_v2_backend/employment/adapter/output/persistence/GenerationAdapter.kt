package com.info.info_v2_backend.employment.adapter.output.persistence

import com.info.info_v2_backend.employment.adapter.output.persistence.repository.GenerationClassRepository
import com.info.info_v2_backend.employment.adapter.output.persistence.repository.GenerationGradeRepository
import com.info.info_v2_backend.employment.application.port.output.generation.LoadGenerationPort
import com.info.info_v2_backend.employment.application.port.output.generation.SaveGenerationPort
import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.generation.GenerationGrade
import org.springframework.stereotype.Service

@Service
class GenerationAdapter(
    private val generationClassRepository: GenerationClassRepository,
    private val generationGradeRepository: GenerationGradeRepository
): LoadGenerationPort, SaveGenerationPort {

    override fun loadClass(classNum: Int, generation: Int): GenerationClass? {
        return generationClassRepository.findByClassNumAndGeneration(classNum, generation).orElse(null)
    }

    override fun loadGrade(generation: Int): GenerationGrade? {
        return generationGradeRepository.findById(generation).orElse(null)
    }

    override fun saveGenerationClass(generationClass: GenerationClass) {
        generationClassRepository.save(generationClass)
    }

    override fun saveGenerationGrade(generationGrade: GenerationGrade) {
        generationGradeRepository.save(generationGrade)
    }

}
