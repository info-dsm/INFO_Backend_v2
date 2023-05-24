package com.info.info_v2_backend.employment.domain

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.common.employment.EmploymentStatus
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse
import com.info.info_v2_backend.employment.domain.company.EmploymentCompany
import com.info.info_v2_backend.employment.domain.company.EmploymentContactor
import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.student.EmployedStudent
import com.info.info_v2_backend.employment.domain.time.TimeEntity
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "employment")
class Employment(
    employedStudent: EmployedStudent,
    company: EmploymentCompany,
    contactor: EmploymentContactor,
    generationClass: GenerationClass
): TimeEntity() {

    @Id
    val id: String = UUID.randomUUID().toString()

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

    @ManyToOne(targetEntity = GenerationClass::class)
    @JoinColumn(name = "generation_class_num", nullable = false)
    @JoinColumn(name = "generation_grade", nullable = false)
    val generationClass: GenerationClass = generationClass

    fun confirm(){
        this.status = EmploymentStatus.RECRUITMENT_CONFIRMED
    }

    fun fail(){
        this.status = EmploymentStatus.RECRUITMENT_FAILED
    }

    fun toEmploymentResponse(): EmploymentDto {
        return EmploymentDto(
            this.id,
            this.student.toEmploymentStudentResponse(),
            this.company.toEmploymentCompanyResponse(),
            this.contactor.toEmploymentContactorResponse(),
            this.status
        )
    }

    fun toAnonymousEmploymentResponse(): AnonymousEmploymentListResponse.AnonymousEmploymentResponse{
        return AnonymousEmploymentListResponse.AnonymousEmploymentResponse(
            this.company.toAnonymousEmploymentCompanyResponse(),
            this.generationClass.classNum
        )
    }

    fun toAdminEmploymentResponse(): AnonymousEmploymentListResponse.AdminEmploymentResponse {
        return AnonymousEmploymentListResponse.AdminEmploymentResponse(
            this.company.toAnonymousEmploymentCompanyResponse(),
            this.generationClass.classNum,
            this.student.studentEmail
        )
    }

}
