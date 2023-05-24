package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.EveryGenerationClassInformationResponse
import com.info.info_v2_backend.employment.application.port.input.LoadEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import com.info.info_v2_backend.employment.application.port.output.generation.LoadGenerationPort
import com.info.info_v2_backend.employment.domain.Employment
import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import com.info.info_v2_backend.employment.domain.student.FIRST_GENERATION_YEAR
import org.springframework.stereotype.Service

@Service
class LoadEmployment(
    private val loadEmploymentPort: LoadEmploymentPort,
    private val loadGenerationPort: LoadGenerationPort
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

    override fun loadAdminEmploymentListResponse(classNum: Int, year: Int): AnonymousEmploymentListResponse {
        val employmentList = getEmploymentList(classNum, year)
        val generationClass = loadGenerationPort.loadClass(classNum, year - FIRST_GENERATION_YEAR - 1)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)

        return AnonymousEmploymentListResponse(
            classNum,
            generationClass,
            generationClass.totalClassStudent,
            employmentList.size,
            generationClass.generationGrade.generationClassList.map(GenerationClass::totalClassStudent).sum(),
            generationClass.generationGrade.generationClassList.map {
                    c ->
                c.employmentList.map {
                        employment ->
                    employment.student.studentEmail
                }
            }.flatten().distinctBy { it }.size,
            employmentList.map {
                it.toAdminEmploymentResponse()
            }
        )
    }

    override fun loadAnonymousEmploymentListResponse(classNum: Int, year: Int): AnonymousEmploymentListResponse {
        val employmentList = getEmploymentList(classNum, year)
        val generationClass = loadGenerationPort.loadClass(classNum, year - FIRST_GENERATION_YEAR - 1)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)

        return AnonymousEmploymentListResponse(
            classNum,
            generationClass,
            generationClass.totalClassStudent,
            employmentList.size,
            generationClass.generationGrade.generationClassList.map(GenerationClass::totalClassStudent).sum(),
            generationClass.generationGrade.generationClassList.map {
                c ->
                c.employmentList.map {
                    employment ->
                    employment.student.studentEmail
                }
            }.flatten().distinctBy { it }.size,
            employmentList.map {
                it.toAnonymousEmploymentResponse()
            }
        )
    }


    private fun getEmploymentList(classNum: Int, year: Int): List<Employment> {
        return arrayListOf(loadEmploymentPort.loadEmploymentByClassNumAndYear(classNum, year)).flatten().sortedByDescending {
            it.createdAt
        }.distinctBy {
            it.student.studentEmail
        }
    }




    override fun loadEveryGenerationClassInformationResponse(year: Int): EveryGenerationClassInformationResponse {
        val generationGrade = loadGenerationPort.loadGrade(year - FIRST_GENERATION_YEAR - 1)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
        return EveryGenerationClassInformationResponse(
            generationGrade.generationClassList.map {
                it.totalClassStudent
            }.sum(),
            generationGrade.generationClassList.map {
                it.employmentList.size
            }.sum(),
            generationGrade.generationClassList.map {
                EveryGenerationClassInformationResponse.GenerationClassInformationResponse(
                    it.classNum,
                    it,
                    it.totalClassStudent,
                    it.employmentList.size
                )
            }
        )
    }
}
