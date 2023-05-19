package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.CreateGenerationGradeRequest
import com.info.info_v2_backend.employment.application.port.input.CreateGenerationUsecase
import com.info.info_v2_backend.employment.application.port.output.generation.LoadGenerationPort
import com.info.info_v2_backend.employment.application.port.output.generation.SaveGenerationPort
import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.generation.GenerationGrade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateGeneration(
    private val saveGenerationPort: SaveGenerationPort,
    private val loadGenerationPort: LoadGenerationPort
): CreateGenerationUsecase {
    override fun createGenerationClass(request: CreateGenerationGradeRequest.CreateGenerationClassRequest) {
        val generationGrade = loadGenerationPort.loadGrade(request.generation)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)

        saveGenerationPort.saveGenerationClass(
            GenerationClass(
                request.classNum,
                generationGrade,
                request.totalClassStudent,
                request.major,
                request.description,
                request.homeroomTeacherName
            )
        )
    }

    override fun createGenerationGrade(request: CreateGenerationGradeRequest) {
        val generationGrade = saveGenerationPort.saveGenerationGrade(GenerationGrade(request.generation))
        request.classList.map {
            saveGenerationPort.saveGenerationClass(
                GenerationClass(
                    it.classNum,
                    generationGrade,
                    it.totalClassStudent,
                    it.major,
                    it.description,
                    it.homeroomTeacherName
                )
            )
        }
    }


}
