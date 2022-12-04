package com.info.info_v2_backend.employment.adapter.input.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.application.port.input.ConfirmEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.input.EmployStudentUsecase
import com.info.info_v2_backend.employment.application.port.input.FailEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.input.LoadEmploymentUsecase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EmploymentController(
    private val employStudentUsecase: EmployStudentUsecase,
    private val loadEmploymentUsecase: LoadEmploymentUsecase,
    private val confirmEmploymentUsecase: ConfirmEmploymentUsecase,
    private val failEmploymentUsecase: FailEmploymentUsecase
) {

    @PostMapping("/{noticeId}/{studentEmail}")
    @ResponseStatus(HttpStatus.CREATED)
    fun employApplies(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String,
    ) {
        if (Auth.checkIsTeacher()) employStudentUsecase.employ(studentEmail, noticeId)
        else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @DeleteMapping("/{companyNumber}/{studentEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun failEmployment(
        @PathVariable companyNumber: String,
        @PathVariable studentEmail: String
    ) {
        failEmploymentUsecase.failEmployment(companyNumber, studentEmail)
    }

    @PutMapping("/{companyNumber}/{studentEmail}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun confirmEmployment(
        @PathVariable companyNumber: String,
        @PathVariable studentEmail: String
    ) {
        confirmEmploymentUsecase.confirmEmployment(companyNumber, studentEmail)
    }

    @GetMapping("/{companyNumber}")
    fun getEmploymentList(
        @PathVariable companyNumber: String,
    ): List<EmploymentDto> {
        if (Auth.checkIsTeacher()) return loadEmploymentUsecase.loadCompanyEmploymentLine(companyNumber)
        return loadEmploymentUsecase.loadCompanyConfirmedEmploymentLine(companyNumber)
    }

}