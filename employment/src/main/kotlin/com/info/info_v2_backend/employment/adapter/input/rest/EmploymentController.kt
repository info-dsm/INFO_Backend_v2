package com.info.info_v2_backend.employment.adapter.input.rest

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EmploymentController(
    private val employStudentUsecase: EmployStudentUsecase,
    private val loadEmploymentUsecase: LoadEmploymentUsecase,
    private val confirmEmploymentUsecase: ConfirmEmploymentUsecase,
    private val failEmploymentUsecase: FailEmploymentUsecase
) {

    @PostMapping("/employ/{studentEmail}")
    @ResponseStatus(HttpStatus.CREATED)
    fun employApplies(
        @PathVariable studentEmail: String,
        @RequestParam noticeId: String
    ) {
        employStudentUsecase.employ(studentEmail, noticeId)
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
    fun confirmEmployment(
        @PathVariable companyNumber: String,
        @PathVariable studentEmail: String
    ) {
        confirmEmploymentUsecase.confirmEmployment(companyNumber, studentEmail)
    }

    @GetMapping("/{companyNumber}")
    fun getEmploymentList(
        @PathVariable companyNumber: String
    ): List<EmploymentResponse> {
        return loadEmploymentUsecase.loadCompanyEmploymentLine(companyNumber)
    }

}