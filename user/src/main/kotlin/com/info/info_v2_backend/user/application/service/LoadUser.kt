package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse
import com.info.info_v2_backend.user.application.port.input.LoadContactorUsecase
import com.info.info_v2_backend.user.application.port.input.LoadStudentUsecase
import com.info.info_v2_backend.user.application.port.output.LoadContactorPort
import com.info.info_v2_backend.user.application.port.output.LoadStudentPort
import org.springframework.stereotype.Service

@Service
class LoadUser(
    private val loadContactorPort: LoadContactorPort,
    private val loadStudentPort: LoadStudentPort
): LoadContactorUsecase, LoadStudentUsecase {
    override fun loadContactor(companyNumber: String): ContactorResponse {
        return (loadContactorPort.loadContactor(companyNumber)
            ?: throw BusinessException("Contactor를 조회하지 못했습니다. -> $companyNumber", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR))
            .toContactorResponse()
    }

    override fun loadStudent(studentEmail: String): StudentDto {
        return (loadStudentPort.loadStudent(studentEmail)
            ?: throw BusinessException("Student를 조회하지 못했습니다. -> $studentEmail", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR))
            .toStudentDto()

    }

    override fun loadStudentListByGeneration(generation: Int): List<StudentDto> {
        return loadStudentPort.loadStudentListByGeneration(generation)
    }


}