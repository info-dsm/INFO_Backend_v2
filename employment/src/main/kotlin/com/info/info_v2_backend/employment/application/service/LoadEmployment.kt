package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse
import com.info.info_v2_backend.employment.application.port.input.LoadEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import org.springframework.stereotype.Service

@Service
class LoadEmployment(
    private val loadEmploymentPort: LoadEmploymentPort
): LoadEmploymentUsecase {

    override fun loadCompanyEmploymentLine(companyNumber: String): List<EmploymentDto> {
        return loadEmploymentPort.loadEmploymentByCompany(companyNumber).map {
            it.toEmploymentResponse()
        }
    }

    override fun loadCompanyConfirmedEmploymentLine(companyNumber: String): List<EmploymentDto> {
        return loadEmploymentPort.loadConfirmedEmploymentByCompany(companyNumber).map {
            it.toEmploymentResponse()
        }
    }

    override fun loadAnonymousEmploymentListResponse(classNum: Int, year: Int): AnonymousEmploymentListResponse {
        val employedStudentList = arrayListOf(loadEmploymentPort.loadEmploymentByClassNumAndYear(classNum, year).takeIf {
            it.isNotEmpty()
        }?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)).flatten().sortedByDescending {
            it.createdAt
        }.distinctBy {
            it.student.studentEmail
        }

        return AnonymousEmploymentListResponse(
            classNum,
            employedStudentList.first().generationClass.totalClassStudent,
            employedStudentList.size,
            employedStudentList.first().generationClass.generationGrade.totalGradeStudent,
            employedStudentList.first().generationClass.generationGrade.generationClassList.map {
                generationClass ->
                generationClass.employmentList.map {
                    employment ->
                    employment.student.studentEmail
                }
            }.flatten().distinctBy { it }.size,
            employedStudentList.map {
                it.toAnonymousEmploymentResponse()
            }
        )
    }
}
