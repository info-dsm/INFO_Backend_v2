package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.application.port.input.EmployStudentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadAppliesStudentPort
import com.info.info_v2_backend.employment.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.employment.application.port.output.LoadNoticePort
import com.info.info_v2_backend.employment.application.port.output.SaveEmploymentPort
import com.info.info_v2_backend.employment.domain.Employment
import com.info.info_v2_backend.employment.domain.company.EmploymentCompany
import com.info.info_v2_backend.employment.domain.company.EmploymentContactor
import com.info.info_v2_backend.employment.domain.notice.EmploymentNotice
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class EmployStudent(
    private val loadAppliesStudentPort: LoadAppliesStudentPort,
    private val loadNoticePort: LoadNoticePort,
    private val loadCompanyPort: LoadCompanyPort,
    private val saveEmploymentPort: SaveEmploymentPort
): EmployStudentUsecase {

    override fun employ(studentEmail: String, noticeId: String) {
        val applies = loadAppliesStudentPort.loadAppliesStudent(noticeId, studentEmail)
            ?: throw BusinessException(
                "지원자를 조회할 수 없습니다. -> $studentEmail",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회할 수 없습니다. -> $noticeId",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        val company = loadCompanyPort.loadCompany(notice.companyNumber)
            ?: throw BusinessException(
                "기업을 조회할 수 없습니다. -> ${notice.companyNumber}",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        val employmentId = UUID.randomUUID().toString()
        val employment = Employment(
            employmentId,
            EmploymentNotice(
                notice.noticeId
            ),
            EmployedStudent(
                studentEmail,
                applies.applicant.name,
                applies.applicant.generation
            ),
            EmploymentCompany(
                notice.companyNumber,
            ),
            EmploymentContactor(
                company.contactorEmail
            )
        )

        saveEmploymentPort.saveEmployment(employment)

    }


}