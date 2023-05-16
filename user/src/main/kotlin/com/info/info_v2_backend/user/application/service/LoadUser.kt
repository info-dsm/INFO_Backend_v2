package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.common.user.ContactorDto
import com.info.info_v2_backend.common.user.TeacherDto
import com.info.info_v2_backend.user.application.port.input.LoadContactorUsecase
import com.info.info_v2_backend.user.application.port.input.LoadStudentUsecase
import com.info.info_v2_backend.user.application.port.input.LoadTeacherUsecase
import com.info.info_v2_backend.user.application.port.output.LoadContactorPort
import com.info.info_v2_backend.user.application.port.output.LoadStudentPort
import com.info.info_v2_backend.user.application.port.output.LoadTeacherPort
import com.info.info_v2_backend.user.application.port.output.UserFilePort
import org.springframework.stereotype.Service

@Service
class LoadUser(
    private val loadContactorPort: LoadContactorPort,
    private val loadStudentPort: LoadStudentPort,
    private val loadTeacherPort: LoadTeacherPort,
    private val userFilePort: UserFilePort
): LoadContactorUsecase, LoadStudentUsecase, LoadTeacherUsecase {
    override fun loadContactor(companyNumber: String): ContactorDto {
        return (loadContactorPort.loadContactor(companyNumber)
            ?: throw BusinessException("Contactor를 조회하지 못했습니다. -> $companyNumber", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR))
            .toContactorResponse()
    }

    override fun loadStudent(studentEmail: String): StudentDto {
        return (loadStudentPort.loadStudent(studentEmail)
            ?: throw BusinessException("Student를 조회하지 못했습니다. -> $studentEmail", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR))
            .toStudentDto(userFilePort.loadProfilePhoto(studentEmail)?.fileUrl)
    }

    override fun loadStudentListByGenerationAndClassNum(grade: Int, classNum: Int?): List<StudentDto> {
        return loadStudentPort.loadStudentListByGenerationAndClassNum(grade, classNum).map {
            it.toStudentDto(userFilePort.loadProfilePhoto(it.email)?.fileUrl)
        }
    }

    override fun loadTeacher(userEmail: String): TeacherDto {
        return loadTeacherPort.load(userEmail)
            ?.toTeacherDto(userFilePort.loadProfilePhoto(userEmail)?.fileUrl)
            ?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }

}
