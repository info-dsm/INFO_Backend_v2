package com.info.info_v2_backend.employment.adapter.input.rest

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.CreateGenerationGradeRequest
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse
import com.info.info_v2_backend.employment.application.port.input.*
import com.info.info_v2_backend.employment.domain.generation.GenerationGrade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EmploymentController(
    private val employStudentUsecase: EmployStudentUsecase,
    private val loadEmploymentUsecase: LoadEmploymentUsecase,
    private val confirmEmploymentUsecase: ConfirmEmploymentUsecase,
    private val failEmploymentUsecase: FailEmploymentUsecase,
    private val createGenerationUsecase: CreateGenerationUsecase
) {


    @GetMapping("/{companyNumber}")
    fun getEmploymentListByCompany(
        @PathVariable companyNumber: String,
    ): List<EmploymentDto> {
        if (Auth.checkIsSystem()) return loadEmploymentUsecase.loadCompanyConfirmedEmploymentLine(companyNumber)
        if (Auth.checkIsTeacher()) return loadEmploymentUsecase.loadCompanyEmploymentLine(companyNumber)
        Auth.companyNumber()?.filter { it.equals(companyNumber) }?.let {
            return loadEmploymentUsecase.loadCompanyEmploymentLine(companyNumber)
        }?: return loadEmploymentUsecase.loadCompanyConfirmedEmploymentLine(companyNumber)
    }

    @PostMapping("/{companyNumber}/{studentEmail}")
    @ResponseStatus(HttpStatus.CREATED)
    fun employApplies(
        @PathVariable companyNumber: String,
        @PathVariable studentEmail: String,
    ) {
        if (Auth.checkIsTeacher()) return employStudentUsecase.employ(studentEmail, companyNumber)
        Auth.companyNumber()?.let {
            return employStudentUsecase.employ(studentEmail, companyNumber)
        }?: throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    //Employment 삭제 API 필요

    @DeleteMapping("/{companyNumber}/{studentEmail}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun failEmployment(
        @PathVariable companyNumber: String,
        @PathVariable studentEmail: String
    ) {
        if (Auth.checkIsTeacher()) return failEmploymentUsecase.failEmployment(companyNumber, studentEmail)
        return failEmploymentUsecase.failEmployment(Auth.checkCompanyNumber(companyNumber), studentEmail)
    }

    @PutMapping("/{companyNumber}/{studentEmail}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun confirmEmployment(
        @PathVariable companyNumber: String,
        @PathVariable studentEmail: String
    ) {
        if (Auth.checkIsTeacher()) return confirmEmploymentUsecase.confirmEmployment(companyNumber, studentEmail)
        return confirmEmploymentUsecase.confirmEmployment(Auth.checkCompanyNumber(companyNumber), studentEmail)
    }

    @GetMapping("/{year}/class/{classNum}")
    fun getEmploymentListByClassNum(
        @PathVariable year: Int,
        @PathVariable classNum: Int
    ): AnonymousEmploymentListResponse {
        return loadEmploymentUsecase.loadAnonymousEmploymentListResponse(classNum, year)
    }

    @PatchMapping("/generation/class")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editGenerationClass(
        @RequestBody request: CreateGenerationGradeRequest.CreateGenerationClassRequest
    ){
        if (Auth.checkIsTeacher()) createGenerationUsecase.createGenerationClass(request)
        else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @PostMapping("/generation/grade")
    @ResponseStatus(HttpStatus.CREATED)
    fun createGenerationGrade(
        @RequestBody request: CreateGenerationGradeRequest
    ){
        if (Auth.checkIsTeacher()) createGenerationUsecase.createGenerationGrade(request)
        else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

}
