package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.application.port.input.EmployStudentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadUserPort
import com.info.info_v2_backend.employment.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.employment.application.port.output.generation.LoadGenerationPort
import com.info.info_v2_backend.employment.application.port.output.SaveEmploymentPort
import com.info.info_v2_backend.employment.domain.Employment
import com.info.info_v2_backend.employment.domain.company.EmploymentCompany
import com.info.info_v2_backend.employment.domain.company.EmploymentContactor
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import com.info.info_v2_backend.employment.domain.student.FIRST_GENERATION_YEAR
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class EmployStudent(
    private val loadUserPort: LoadUserPort,
    private val loadCompanyPort: LoadCompanyPort,
    private val saveEmploymentPort: SaveEmploymentPort,
    private val loadGenerationPort: LoadGenerationPort
): EmployStudentUsecase {

    override fun employ(studentEmail: String, companyNumber: String) {
        val student = loadUserPort.loadStudent(studentEmail)
            ?: throw BusinessException(
                errorCode = ErrorCode.NO_DATA_FOUND_ERROR
            )

        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException(
                "기업을 조회할 수 없습니다. -> ${companyNumber}",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        val employment = Employment(
            EmployedStudent(
                studentEmail,
                student.name,
                student.entranceYear
            ),
            EmploymentCompany(
                companyNumber,
                company.companyName,
                company.companyLogo
            ),
            EmploymentContactor(
                company.contactorEmail
            ),
            loadGenerationPort.loadClass(
                student.studentKey.substring(1, 2).toInt(), student.entranceYear - FIRST_GENERATION_YEAR + 1)
                ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
        )
        saveEmploymentPort.saveEmployment(employment)

    }

}
