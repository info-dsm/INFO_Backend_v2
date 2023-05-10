package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.application.port.input.LoadGenerationUsecase
import com.info.info_v2_backend.employment.application.port.output.generation.LoadGenerationPort
import com.info.info_v2_backend.employment.domain.generation.GenerationGrade
import org.springframework.stereotype.Service

@Service
class LoadGeneration(
    private val loadGenerationPort: LoadGenerationPort
): LoadGenerationUsecase {

    override fun load(generation: Int): GenerationGrade {
        return loadGenerationPort.loadGrade(generation)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
    }


}
