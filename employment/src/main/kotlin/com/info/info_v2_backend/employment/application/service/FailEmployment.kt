package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.application.port.input.FailEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import com.info.info_v2_backend.employment.application.port.output.SaveEmploymentPort
import org.springframework.stereotype.Service

@Service
class FailEmployment(
    private val loadEmploymentPort: LoadEmploymentPort,
    private val saveEmploymentPort: SaveEmploymentPort
): FailEmploymentUsecase {

    override fun failEmployment(companyNumber: String, studentEmail: String) {
        val employment = loadEmploymentPort.loadEmploymentByCompanyAndStudent(
            companyNumber, studentEmail
        )?: throw BusinessException(
            "채용을 조회하는데 실패했습니다. -> $companyNumber, $studentEmail",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
        )
        employment.fail()

        saveEmploymentPort.saveEmployment(employment)
    }

}