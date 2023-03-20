package com.info.info_v2_backend.employment.domain

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.common.employment.EmploymentStatus
import com.info.info_v2_backend.employment.domain.company.EmploymentCompany
import com.info.info_v2_backend.employment.domain.company.EmploymentContactor
import com.info.info_v2_backend.employment.domain.notice.EmploymentNotice
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import com.info.info_v2_backend.employment.domain.time.TimeEntity
import javax.persistence.*

@Entity
class Employment(
    id: String,
    notice: EmploymentNotice,
    employedStudent: EmployedStudent,
    company: EmploymentCompany,
    contactor: EmploymentContactor
): TimeEntity() {

    @Id
    val id: String = id

    @Embedded
    var notice: EmploymentNotice = notice
        protected set

    @Embedded
    var student: EmployedStudent = employedStudent
        protected set

    @Embedded
    var company: EmploymentCompany = company
        protected set

    @Embedded
    var contactor: EmploymentContactor = contactor
        protected set

    @Column(name = "employment_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var status: EmploymentStatus = EmploymentStatus.INTERN
        protected set

    fun confirm(){
        this.status = EmploymentStatus.RECRUITMENT_CONFIRMED
    }

    fun fail(){
        this.status = EmploymentStatus.RECRUITMENT_FAILED
    }

    fun toEmploymentResponse(): EmploymentDto {
        return EmploymentDto(
            this.id,
            this.notice.toEmploymentNoticeResponse(),
            this.student.toEmploymentStudentResponse(),
            this.company.toEmploymentCompanyResponse(),
            this.contactor.toEmploymentContactorResponse(),
            this.status
        )
    }

}