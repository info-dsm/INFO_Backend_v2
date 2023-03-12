package com.info.info_v2_backend.applies.adapter.input.rest

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.application.port.input.*
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class AppliesController(
    private val applyUsecase: ApplyAppliesUsecase,
    private val cancelApplyUsecase: CancelApplyUsecase,
    private val loadAppliesUsecase: LoadAppliesUsecase,
    private val approveAppliesUsecase: ApproveAppliesUsecase,
    private val rejectAppliesUsecase: RejectAppliesUsecase
) {

    @PostMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun apply(
        @PathVariable noticeId: String,
        @RequestBody request: GenerateFileListRequest
    ): PresignedUrlListResponse {
        return applyUsecase.apply(noticeId, request, Auth.getUserEmail()?: throw BusinessException(null, ErrorCode.TOKEN_NEED_ERROR))
    }

    @DeleteMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cancelApply(
        @PathVariable noticeId: String
    ) {
        return cancelApplyUsecase.cancelApply(noticeId, Auth.getUserEmail()?: throw BusinessException(null, ErrorCode.TOKEN_NEED_ERROR))
    }

    @GetMapping("/{companyNumber}/{noticeId}")
    fun getNoticeAppliesList(
        @PathVariable companyNumber: String,
        @PathVariable noticeId: String,
        @RequestParam(required = false) status: AppliesStatus?
    ): List<AppliesResponse> {
        status?.let {
            if (Auth.checkIsTeacher()) {
                return loadAppliesUsecase.loadAppliesListByStatus(
                    companyNumber,
                    noticeId,
                    status
                )
            } else if (status == AppliesStatus.APPROVE) {
                return loadAppliesUsecase.loadAppliesListByStatus(
                    Auth.checkCompanyNumber(
                        companyNumber
                    ), noticeId, status
                )
            } else throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
        }?: let {
            if (!Auth.checkIsTeacher()) throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
            return loadAppliesUsecase.loadAppliesListByStatus(
                companyNumber,
                noticeId,
                null
            )
        }
    }

    @GetMapping
    fun getTotalAppliesList(
        @RequestParam status: AppliesStatus
    ): List<AppliesResponse> {
        if (Auth.checkIsTeacher()) return loadAppliesUsecase.loadEveryAppliesListByStatus(status)
        else throw BusinessException(
            "이 작업은 선생님만 수행할 수 있습니다.",
            ErrorCode.NO_AUTHORIZATION_ERROR
        )
    }

    @PutMapping("/{noticeId}/{studentEmail}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun approveApplies(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ) {
        if (!Auth.checkIsTeacher())
            throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
        return approveAppliesUsecase.approve(noticeId, studentEmail)
    }


    @DeleteMapping("/{noticeId}/{studentEmail}")
    fun rejectApplies(
        @PathVariable noticeId: String,
        @PathVariable studentEmail: String
    ) {
        if (!Auth.checkIsTeacher())
            throw BusinessException(
                "이 작업은 선생님만 수행할 수 있습니다.",
                ErrorCode.NO_AUTHORIZATION_ERROR
            )
        return rejectAppliesUsecase.reject(noticeId, studentEmail)
    }

    @GetMapping("/student")
    fun getStudentAppliesList(
        @RequestParam studentEmail: String?
    ): List<AppliesResponse> {
        studentEmail?.let {
            if (Auth.checkIsTeacher()) return loadAppliesUsecase.loadAppliesListByStudentEmail(studentEmail)
            else throw BusinessException(null, ErrorCode.NO_AUTHORIZATION_ERROR)
        }?: return loadAppliesUsecase.loadAppliesListByStudentEmail(Auth.getUserEmail()?: throw BusinessException(null, ErrorCode.TOKEN_NEED_ERROR))
    }


    //internal
    @GetMapping("/{noticeId}")
    fun getApplies(
        @PathVariable noticeId: String,
        @RequestParam studentEmail: String
    ): AppliesDto? {
        return loadAppliesUsecase.loadApplies(noticeId, studentEmail)
    }

}